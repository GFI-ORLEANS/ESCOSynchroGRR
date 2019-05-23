package fr.recia.grr.batch.synchronisation.entity.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "grr_calendar")
public class GrrCalendar  implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */


    @Column(name = "DAY")
    @Id
    private Integer day;


//    @ManyToMany(mappedBy = "grr_j_etablissement_calendar")
//    private Collection<GrrEtablissement> grr_j_calendar_etablissement;



    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */

    /**
     * Constructeur par defaut.
     */
    public GrrCalendar() {

    }




    /**
     * Constructeur avec tous les parametres.
     */

    public GrrCalendar(Integer day) {
        this.day = day;
    }

    /*
     * ===============================================
     * Getter / Setter de la classe
     * ===============================================
     */

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    /*
     * ===============================================
     * toString de la classe
     * ===============================================
     */



}
