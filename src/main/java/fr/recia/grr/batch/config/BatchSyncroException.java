package fr.recia.grr.batch.config;

 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BatchSyncroException extends Exception {
    private static final Logger log = LoggerFactory.getLogger(BatchSyncroException.class);

    public BatchSyncroException(String message) {
        super(message);
        log.error(message);
    }
}
