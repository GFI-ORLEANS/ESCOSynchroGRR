package fr.recia.grr.batch.synchronisation.entity.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class LogId implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    private LocalDateTime start;
    private String sessionID;

    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */

    public LogId() {
    }

    public LogId(LocalDateTime start, String sessionID) {
        this.start = start;
        this.sessionID = sessionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogId logId = (LogId) o;
        return Objects.equals(start, logId.start) &&
                Objects.equals(sessionID, logId.sessionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, sessionID);
    }
}
