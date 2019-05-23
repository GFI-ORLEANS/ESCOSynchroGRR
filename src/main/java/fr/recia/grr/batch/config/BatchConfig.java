package fr.recia.grr.batch.config;

import fr.recia.grr.batch.listener.ExecutionListenerJob;
import fr.recia.grr.batch.listener.ExecutionListenerStep;
import fr.recia.grr.batch.processor.ProcessorMisAJourEtablissement;
import fr.recia.grr.batch.processor.ProcessorMisAJourPersonne;
import fr.recia.grr.batch.processor.ProcessorSuppressionComptesAbsentsLDAP;
import fr.recia.grr.batch.reader.ReaderDAOAllEtablissement;
import fr.recia.grr.batch.reader.ReaderDAOAllPersonnes;
import fr.recia.grr.batch.synchronisation.entity.dao.GrrEtablissement;
import fr.recia.grr.batch.synchronisation.entity.dao.GrrUtilisateurs;
import fr.recia.grr.batch.synchronisation.entity.ldap.ODMPersonne;
import fr.recia.grr.batch.synchronisation.entity.ldap.ODMStructure;
import fr.recia.grr.batch.synchronisation.mapper.GrrUtilisateurRowMapper;
import fr.recia.grr.batch.tasklet.ReservationAncienneTasklet;
import fr.recia.grr.batch.tasklet.ViderLogTasklet;
import fr.recia.grr.batch.writer.WriterMisAjourEtablissement;
import fr.recia.grr.batch.writer.WriterMisAjourPersonne;
import fr.recia.grr.batch.writer.WriterSuppressionComptesAbsentsLDAP;
import fr.recia.grr.utils.DateBatch;
import fr.recia.grr.utils.DateDerniereMiseAJourLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@Profile("!BatchMigration")
@Configuration
public class BatchConfig {
    private static final Logger log = LoggerFactory.getLogger(BatchConfig.class);

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */



    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ResourceLoader resourceLoader;
    private DateBatch dateBatch;

    /*
     * ===============================================
     * Configuration du batch
     * ===============================================
     */



    @PostConstruct
    private void postInitialize()  {
        Optional<String> s = null;
        try {
            s = DateDerniereMiseAJourLoader.loadDate(resourceLoader);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        log.info("Date de derniere Execution : {}", s.orElse(null));
        //date=20190219000000Z
        SimpleDateFormat currentDate = new SimpleDateFormat("yyyyMMddHHmmssX");
        currentDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        String format = currentDate.format(new Date());

        log.info("Date de nouvelle execution Execution : {}", format);
        dateBatch = new DateBatch(s.orElse(null), format);
    }

   @Bean
    public Job config(ExecutionListenerJob listener,
                      Step misAjourEtablissement,
                      Step misAjourPersonnes,
                      Step suppressionComptesAbsentsLDAP,
                      Step suppressionReservationAnciennes,
                      Step nettoyageLog,
                      Step endBatch)  {


        return jobBuilderFactory.get("Syncro")
                .incrementer(new RunIdIncrementer())
                .listener(listener).start(misAjourEtablissement).on(FlowExecutionStatus.FAILED.getName()).end()
                .next(misAjourPersonnes).on(FlowExecutionStatus.FAILED.getName()).end()
                .next(suppressionComptesAbsentsLDAP).on(FlowExecutionStatus.FAILED.getName()).end()
                .next(suppressionReservationAnciennes).on(FlowExecutionStatus.FAILED.getName()).end()
                .next(nettoyageLog).on(FlowExecutionStatus.FAILED.getName()).end()
                .next(endBatch)
                .build().build();
    }

    /*
     * ===============================================
     * Step 1: Mis a jour des etablissements
     * ===============================================
     */
    @Bean
    public ReaderDAOAllEtablissement misAJourEtablissementReader(){
        return new ReaderDAOAllEtablissement(dateBatch);
    }

    @Bean
    public ProcessorMisAJourEtablissement misAJourEtablissementProcessor(){
        return new ProcessorMisAJourEtablissement();
    }

    @Bean
    public WriterMisAjourEtablissement misAJourEtablissementWriter(){
        return new WriterMisAjourEtablissement();
    }
    @Bean
    public Step misAjourEtablissement(ExecutionListenerStep listener){

        return stepBuilderFactory.get("misAjourEtablissement")
                .<ODMStructure, GrrEtablissement> chunk(1)
                .faultTolerant()
                .skip(BatchSyncroException.class)
                .skipLimit(100000)
                .reader(misAJourEtablissementReader())
                .processor( misAJourEtablissementProcessor())
                .writer(misAJourEtablissementWriter())
                .listener(listener)
                .build();
    }


    /*
     * ===============================================
     * Step 2: Mis a jour des utilisateurs et des administrateurs
     * ===============================================
     */

    @Bean
    public ReaderDAOAllPersonnes misAJourPersonnesReader(){
        return new ReaderDAOAllPersonnes(dateBatch);
    }

    @Bean
    public ProcessorMisAJourPersonne misAJourPersonnesProcessor(){
        return new ProcessorMisAJourPersonne();
    }

    @Bean
    public WriterMisAjourPersonne misAJourPersonnesWriter(){
        return new WriterMisAjourPersonne();
    }

    @Bean
    public Step misAjourPersonnes(ExecutionListenerStep listener){

        return stepBuilderFactory.get("misAjourPersonnes")
                .<ODMPersonne, GrrUtilisateurs> chunk(1)
                .faultTolerant()
                .skip(BatchSyncroException.class)
                .skipLimit(100000)
                .reader(misAJourPersonnesReader())
                .processor( misAJourPersonnesProcessor())
                .writer(misAJourPersonnesWriter())
                .listener(listener)
                .build();
    }

    /*
     * ===============================================
     * Step 3: Suppression des comptes absents du ldap
     * ===============================================
     */
    @Value("${derniereConnexionDepuisNJour}")
    private int jourRequisConnexion;

    @Autowired
    @Qualifier( "dataSource")
    private DataSource dataSource;

    @Value("${statusAdmin}")
    private String statusAdmin;



    @Bean
    public JdbcCursorItemReader<String> suppressionComptesNjoursReader(){

        LocalDateTime dateActuel= LocalDateTime.now();
        LocalDateTime dateRequis=dateActuel.minus(jourRequisConnexion, ChronoUnit.DAYS);

        JdbcCursorItemReader<String> itemReader = new JdbcCursorItemReader<>();
        itemReader.setDataSource(dataSource);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select distinct  grr_utilisateurs.login from grr_utilisateurs ");
        stringBuilder.append("left outer join (select lg.login , max(lg.start) as start from grr_log lg group by login) as log on log.LOGIN = grr_utilisateurs.login ");
        stringBuilder.append("where (log.start is null OR log.start <  '");
        stringBuilder.append(dateRequis);
        stringBuilder.append("') ");
        stringBuilder.append("and grr_utilisateurs.source = 'ext' ");
        stringBuilder.append("and grr_utilisateurs.statut != '");
        stringBuilder.append(statusAdmin);
        stringBuilder.append("'");
        itemReader.setSql(stringBuilder.toString());
        itemReader.setRowMapper(new GrrUtilisateurRowMapper());
        return   itemReader;

    }


    @Bean
    public WriterSuppressionComptesAbsentsLDAP suppressionComptesAbsentsLDAPWriter(){
        return new WriterSuppressionComptesAbsentsLDAP();
    }
    @Bean
    public ProcessorSuppressionComptesAbsentsLDAP processorSuppressionComptesAbsentsLDAP(){
        return new ProcessorSuppressionComptesAbsentsLDAP();
    }

    @Bean
    public Step suppressionComptesAbsentsLDAP(ExecutionListenerStep listener){

        return stepBuilderFactory.get("suppressionComptesAbsentsLDAP")
                .<String, String> chunk(1)
                .reader(suppressionComptesNjoursReader())
                .processor(processorSuppressionComptesAbsentsLDAP())
                .writer(suppressionComptesAbsentsLDAPWriter())
                .listener(listener)
                .build();
    }



    /*
     * ===============================================
     * Step 4: Suppression des reservations trops anciennes
     * ===============================================
     */


    @Bean
    public ReservationAncienneTasklet reservationAncienneTasklet(){
        return new ReservationAncienneTasklet();
    }

    @Bean
    public Step suppressionReservationAnciennes(ExecutionListenerStep listener){

        return stepBuilderFactory.get("suppressionReservationAnciennes")
                .tasklet(reservationAncienneTasklet())
                .listener(listener)
                .build();
    }


    /*
     * ===============================================
     * Step 5: Vider logs
     * ===============================================
     */

    @Bean
    public ViderLogTasklet viderLogTasklet(){
        return new ViderLogTasklet();
    }


    @Bean
    public Step nettoyageLog(ExecutionListenerStep listener){
        return stepBuilderFactory.get("nettoyageLog")
                .tasklet(viderLogTasklet())
                .listener(listener)
                .build();
    }



    @Bean
    public Step endBatch(ExecutionListenerStep listener){
        return stepBuilderFactory.get("endBatch")
                .tasklet(endBatchTxt())
                .listener(listener)
                .build();
    }

    private Tasklet endBatchTxt() {
        return (stepContribution, chunkContext) -> {
            log.info("Mise a jour de la date de derniere mise a jour : {} " ,dateBatch.getCurrentBatch());
            DateDerniereMiseAJourLoader.write(resourceLoader, dateBatch.getCurrentBatch());
            return RepeatStatus.FINISHED;
        };
    }
}
