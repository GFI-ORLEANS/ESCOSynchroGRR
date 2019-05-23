package fr.recia.grr.batch.synchronisation.entity.dao;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "grr_log")
@IdClass(LogId.class)
public class GrrLog implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Id
    @Column(name="START")
    private LocalDateTime start;

    @Id
    @Column(name = "SESSION_ID")
    private String sessionID;

    @Column(name = "REMOTE_ADDR")
    private String remoteADDR;

    @Column(name = "USER_AGENT")
    private String userAgent;

    @Column(name = "REFERER")
    private String refferer;


    @Column(columnDefinition = "enum(0,1)",name = "AUTOCLOSE")
    private Boolean autoClose;

    @Column(name = "END")
    private LocalDateTime end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGIN")
    private GrrUtilisateurs user;

    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */

    /**
     * Constructeur par defaut.
     */
    public GrrLog() {
    }



    /**
     * Constructeur avec tous les parametres.
     */
    public GrrLog(LocalDateTime start, String sessionID, String remoteADDR, String userAgent, String refferer, Boolean autoClose, LocalDateTime end, GrrUtilisateurs user) {
        this.start = start;
        this.sessionID = sessionID;
        this.remoteADDR = remoteADDR;
        this.userAgent = userAgent;
        this.refferer = refferer;
        this.autoClose = autoClose;
        this.end = end;
        this.user = user;
    }

    /*
     * ===============================================
     * Getter / Setter de la classe
     * ===============================================
     */

    /**
     * Getter de la proprieté start
     * @return la proprieté start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Setter de la proprieté start
     * @param start
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Getter de la proprieté sessionID
     * @return la proprieté sessionID
     */
    public String getSessionID() {
        return sessionID;
    }


    /**
     * Setter de la proprieté sessionID
     * @param sessionID
     */
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    /**
     * Getter de la proprieté remoteADDR
     * @return la proprieté remoteADDR
     */
    public String getRemoteADDR() {
        return remoteADDR;
    }

    /**
     * Setter de la proprieté remoteADDR
     * @param remoteADDR
     */
    public void setRemoteADDR(String remoteADDR) {
        this.remoteADDR = remoteADDR;
    }

    /**
     * Getter de la proprieté userAgent
     * @return la proprieté userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Setter de la proprieté userAgent
     * @param userAgent
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * Getter de la proprieté refferer
     * @return la proprieté refferer
     */
    public String getRefferer() {
        return refferer;
    }

    /**
     * Setter de la proprieté refferer
     * @param refferer
     */
    public void setRefferer(String refferer) {
        this.refferer = refferer;
    }

    /**
     * Getter de la proprieté autoClose
     * @return la proprieté autoClose
     */
    public Boolean getAutoClose() {
        return autoClose;
    }


    /**
     * Setter de la proprieté autoClose
     * @param autoClose
     */
    public void setAutoClose(Boolean autoClose) {
        this.autoClose = autoClose;
    }

    /**
     * Getter de la proprieté end
     * @return la proprieté end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Setter de la proprieté end
     * @param end
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Getter de la proprieté user
     * @return la proprieté user
     */
    public GrrUtilisateurs getUser() {
        return user;
    }

    /**
     * Setter de la proprieté user
     * @param user
     */
    public void setUser(GrrUtilisateurs user) {
        this.user = user;
    }





    /*
     * ===============================================
     * toString de la classe
     * ===============================================
     */

    @Override
    public String toString() {
        return "GrrLog{" +
                "end=" + end +
                ", user=" + user +
                '}';
    }
}
