package fr.recia.grr.batch.tasklet;

import fr.recia.grr.batch.synchronisation.repository.dao.ILogRepositoryDAO;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ViderLogTasklet implements Tasklet {


    @Autowired
    private ILogRepositoryDAO logRepositoryDAO;

    @Value("${anneeViderLog}")
    private int anneeViderLog;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        LocalDateTime dateActuel= LocalDateTime.now();
        LocalDateTime dateRequis=dateActuel.minus(anneeViderLog, ChronoUnit.YEARS);
        int i = logRepositoryDAO.deletefrom(dateRequis);
        chunkContext.getStepContext().getStepExecution().setWriteCount(i);
        return RepeatStatus.FINISHED;
    }
}
