package fr.recia.grr.batch.migration.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "grr_area_periodes")
@IdClass(GrrAreaPeriodePK.class)
public class GrrAreaPeriode implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Id
    @Column(name = "id_area")
    private Integer id_area;

    @Id
    @Column(name = "num_periode")
    private Short num_periode;

    @Column(name = "nom_periode")
    private String nom_periode;



    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */

    /**
     * Constructeur par defaut.
     */
    public GrrAreaPeriode() {

    }



    /**
     * Constructeur avec tous les parametres.
     */

    public GrrAreaPeriode(Integer id, Short num_periode, String nom_periode) {
        this.id_area = id;
        this.num_periode = num_periode;
        this.nom_periode = nom_periode;
    }

    /*
     * ===============================================
     * Getter / Setter de la classe
     * ===============================================
     */


    public Integer getId_area() {
        return id_area;
    }

    public void setId_area(Integer id_area) {
        this.id_area = id_area;
    }

    public Integer getId() {
        return id_area;
    }

    public void setId(Integer id) {
        this.id_area = id;
    }

    public Short getNum_periode() {
        return num_periode;
    }

    public void setNum_periode(Short num_periode) {
        this.num_periode = num_periode;
    }

    public String getNom_periode() {
        return nom_periode;
    }

    public void setNom_periode(String nom_periode) {
        this.nom_periode = nom_periode;
    }

    /*
     * ===============================================
     * toString de la classe
     * ===============================================
     */



}

class GrrAreaPeriodePK implements Serializable{
    private Integer id_area;
    private Short num_periode;
}