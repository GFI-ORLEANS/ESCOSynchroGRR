package fr.recia.grr.batch.migration.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "grr_overload")
public class GrrOverload implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Id
    @Column(name = "id")
    private Integer id;



    private Integer id_area;

    @Column(name = "fieldname")
    private String fieldname;

    @Column(name = "fieldtype")
    private String fieldtype;

    @Column(name = "fieldlist",columnDefinition = "TEXT")
    private String fieldlist;


    @Column(name = "obligatoire",columnDefinition="char(1)")
    private String obligatoire;

    @Column(name = "affichage",columnDefinition="char(1)")
    private String affichage;

    @Column(name = "confidentiel",columnDefinition="char(1)")
    private String confidentiel;

    @Column(name = "overload_mail",columnDefinition="char(1)")
    private String overload_mail;



    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */

    /**
     * Constructeur par defaut.
     */
    public GrrOverload() {

    }

    public GrrOverload(Integer id, Integer id_area, String fieldname, String fieldtype, String fieldlist, String obligatoire, String affichage, String confidentiel, String overload_mail) {
        this.id = id;
        this.id_area = id_area;
        this.fieldname = fieldname;
        this.fieldtype = fieldtype;
        this.fieldlist = fieldlist;
        this.obligatoire = obligatoire;
        this.affichage = affichage;
        this.confidentiel = confidentiel;
        this.overload_mail = overload_mail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_area() {
        return id_area;
    }

    public void setId_area(Integer id_area) {
        this.id_area = id_area;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getFieldtype() {
        return fieldtype;
    }

    public void setFieldtype(String fieldtype) {
        this.fieldtype = fieldtype;
    }

    public String getFieldlist() {
        return fieldlist;
    }

    public void setFieldlist(String fieldlist) {
        this.fieldlist = fieldlist;
    }

    public String getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(String obligatoire) {
        this.obligatoire = obligatoire;
    }

    public String getAffichage() {
        return affichage;
    }

    public void setAffichage(String affichage) {
        this.affichage = affichage;
    }

    public String getConfidentiel() {
        return confidentiel;
    }

    public void setConfidentiel(String confidentiel) {
        this.confidentiel = confidentiel;
    }

    public String getOverload_mail() {
        return overload_mail;
    }

    public void setOverload_mail(String overload_mail) {
        this.overload_mail = overload_mail;
    }

    /**
     * Constructeur avec tous les parametres.
     */



    /*
     * ===============================================
     * Getter / Setter de la classe
     * ===============================================
     */


    /*
     * ===============================================
     * toString de la classe
     * ===============================================
     */



}
