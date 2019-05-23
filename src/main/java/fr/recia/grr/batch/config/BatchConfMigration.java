package fr.recia.grr.batch.config;

import fr.recia.grr.batch.listener.ExecutionListenerStep;
import fr.recia.grr.batch.migration.entity.GrrSite;
import fr.recia.grr.batch.reader.ReaderMigrationRoom;
import fr.recia.grr.batch.writer.WriterMigration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile({"BatchMigration"})
public class BatchConfMigration extends DefaultBatchConfigurer {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    /*
     * ===============================================
     * Configuration du batch
     * ===============================================
     */

    @Bean
    public Job config(Step migrationRoom){
        return jobBuilderFactory.get("configMigration")
                .incrementer(new RunIdIncrementer())
                .start(migrationRoom)
                .build();
    }
    /*
     * ===============================================
     * Step 1: Migration room
     * ===============================================
     */


    @Bean
    public ReaderMigrationRoom migrationRoomReader(){
        return new ReaderMigrationRoom();
    }

    @Bean
    public WriterMigration writerMigration(){
        return new WriterMigration();
    }



    @Bean
    public Step migrationRoom(ExecutionListenerStep listener){

        return stepBuilderFactory.get("migrationRoom")
                .<GrrSite, GrrSite> chunk(1)
                .reader(migrationRoomReader())
                .writer(writerMigration())
                .listener(listener)
                .build();
    }


}

