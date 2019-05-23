package fr.recia.grr.batch.synchronisation.entity.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "grr_j_etablissement_calendar")
public class GrrJEtablissementCalendar implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */


    @Column(name = "DAY")
    @Id
    private Integer day;

    @Column(name = "id_etablissement")
    @Id
    private Integer id_etablissement;



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
    public GrrJEtablissementCalendar() {

    }


    public GrrJEtablissementCalendar(Integer day, Integer id_etablissement) {
        this.day = day;
        this.id_etablissement = id_etablissement;
    }

    /**
     * Constructeur avec tous les parametres.
     */



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

    public Integer getId_etablissement() {
        return id_etablissement;
    }

    public void setId_etablissement(Integer id_etablissement) {
        this.id_etablissement = id_etablissement;
    }

    /*
     * ===============================================
     * toString de la classe
     * ===============================================
     */



}
