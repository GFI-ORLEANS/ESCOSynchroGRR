package fr.recia.grr.batch.listener;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;


@Component
public class ExecutionListenerJob implements JobExecutionListener {

    private static final Log log = LogFactory.getLog(ExecutionListenerJob.class);


    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Statut du job : " + jobExecution.getStatus());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Statut du job : " + jobExecution.getStatus());
        log.info("Date de la creation : " + jobExecution.getCreateTime());
        log.info("Date de debut : " + jobExecution.getStartTime());
        log.info("Date de fin : " + jobExecution.getEndTime());
        log.info("Date de la derniere modification : " + jobExecution.getLastUpdated());

    }
}
