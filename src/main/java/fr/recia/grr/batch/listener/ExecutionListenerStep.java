package fr.recia.grr.batch.listener;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;


@Component
public class ExecutionListenerStep implements StepExecutionListener {

    private static final Log log = LogFactory.getLog(ExecutionListenerStep.class);


    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("-------------------------------------");
        log.info("Execution de l'etape " + stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Nombre de lecture : " + stepExecution.getReadCount() );
        log.info("Nombre d'ecriture : " + stepExecution.getWriteCount() );
        log.info("Nombre de skip    : " + stepExecution.getSkipCount() );
        log.info("Status de l'etape : " + stepExecution.getStatus().getBatchStatus());

        return null;
    }
}
