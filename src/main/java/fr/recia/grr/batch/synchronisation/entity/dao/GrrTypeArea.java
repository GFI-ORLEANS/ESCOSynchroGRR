package fr.recia.grr.batch.synchronisation.entity.dao;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "grr_type_area")
public class GrrTypeArea implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type_name")
    private String type_name;



    @Column(name = "order_display")
    private Short order_display;


    @Column(name = "couleur")
    private Short couleurhexa;

    @Column(name = "type_letter",
            columnDefinition="char(2)")
    private String type_letter;

    @Column(name = "disponible")
    private String disponible;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name ="grr_j_type_area",
            joinColumns = @JoinColumn(name = "id_type"),
            inverseJoinColumns = @JoinColumn(name = "id_area"))
    private Collection<GrrArea> grr_j_type_area;

    @ManyToMany(mappedBy = "grr_j_etablissement_type_area",cascade=CascadeType.ALL)
    private Collection<GrrEtablissement> grr_j_type_area_etablissement;




    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */

    /**
     * Constructeur par defaut.
     */
    public GrrTypeArea() {
        grr_j_type_area_etablissement = new ArrayList<>();
        grr_j_type_area = new ArrayList<>();

    }



    /**
     * Constructeur avec tous les parametres.
     */
    public GrrTypeArea(Integer id, String type_name, Short order_display, Short couleurhexa, String type_letter, String disponible) {
        this.id = id;
        this.type_name = type_name;
        this.order_display = order_display;
        this.couleurhexa = couleurhexa;
        this.type_letter = type_letter;
        this.disponible = disponible;
    }


    /*
     * ===============================================
     * Getter / Setter de la classe
     * ===============================================
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Short getOrder_display() {
        return order_display;
    }

    public void setOrder_display(Short order_display) {
        this.order_display = order_display;
    }

    public Short getCouleurhexa() {
        return couleurhexa;
    }

    public void setCouleurhexa(Short couleurhexa) {
        this.couleurhexa = couleurhexa;
    }

    public String getType_letter() {
        return type_letter;
    }

    public void setType_letter(String type_letter) {
        this.type_letter = type_letter;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public Collection<GrrArea> getGrr_j_type_area() {
        return grr_j_type_area;
    }

    public void setGrr_j_type_area(Collection<GrrArea> grr_j_type_area) {
        this.grr_j_type_area = grr_j_type_area;
    }

    public Collection<GrrEtablissement> getGrr_j_type_area_etablissement() {
        return grr_j_type_area_etablissement;
    }

    public void setGrr_j_type_area_etablissement(Collection<GrrEtablissement> grr_j_type_area_etablissement) {
        this.grr_j_type_area_etablissement = grr_j_type_area_etablissement;
    }

    /*
     * ===============================================
     * toString de la classe
     * ===============================================
     */



}
