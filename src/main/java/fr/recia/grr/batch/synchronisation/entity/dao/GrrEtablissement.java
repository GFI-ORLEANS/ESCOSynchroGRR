package fr.recia.grr.batch.synchronisation.entity.dao;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "grr_etablissement")
public class GrrEtablissement implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;


    @Column(name = "shortname")
    private String shortname;

    @Column(name = "name")
    private String name;

    @Column(name = "codepostal")
    private String codepostal;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "ville")
    private String ville;


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name ="grr_j_etablissement_site",
            joinColumns = @JoinColumn(name = "id_etablissement"),
            inverseJoinColumns = @JoinColumn(name = "id_site"))
    private List<GrrSite> grr_j_etablissement_site;

    @ManyToMany(mappedBy = "grr_j_useradmin_etablissement")
    private Set<GrrUtilisateurs> grr_j_etablisssment_useradmin;

    @ManyToMany(mappedBy = "grr_j_user_etablissement")
    private Set<GrrUtilisateurs> grr_j_etablissement_user;


    @OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name="code_etablissement_secondaire", referencedColumnName="code")
    })
    private Set<GrrEtablissementRegroupement> etablissements_principal;

    @OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name="code_etablissement_principal", referencedColumnName="code")
    })
    private Set<GrrEtablissementRegroupement> etablissements_secondaire;


    @OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumns({
            @JoinColumn(name="id_etablissement")
    })
    private Set<GrrJEtablissementCalendar> grrJEtablissementCalendars;


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name ="grr_j_etablissement_type_area",
            joinColumns = @JoinColumn(name = "id_type_area"),
            inverseJoinColumns = @JoinColumn(name = "id_etablissement"))
    private Set<GrrTypeArea> grr_j_etablissement_type_area;


    public void removeAllEtablissements_principal() {
        etablissements_principal.forEach(order -> order.setCode_etablissement_principal(null));
        this.etablissements_principal.clear();
    }
    public void removeAllEtablissements_secondaire() {
        etablissements_secondaire.forEach(order -> order.setCode_etablissement_secondaire(null));
        this.etablissements_secondaire.clear();
    }

    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */

    /**
     * Constructeur par defaut.
     */
    public GrrEtablissement() {
        grr_j_etablissement_site = new ArrayList<>();
        grr_j_etablisssment_useradmin = new HashSet<>();
        grr_j_etablissement_user = new HashSet<>();
        etablissements_principal = new HashSet<>();
        etablissements_secondaire = new HashSet<>();
        grrJEtablissementCalendars = new HashSet<>();
        grr_j_etablissement_type_area = new HashSet<>();
    }

    /**
     * Constructeur avec tous les parametres.
     */
    public GrrEtablissement(Integer id, String code, String shortname, String name, String codepostal, String adresse, String ville) {
        this.id = id;
        this.code = code;
        this.shortname = shortname;
        this.name = name;
        this.codepostal = codepostal;
        this.adresse = adresse;
        this.ville = ville;
    }

    /*
     * ===============================================
     * Getter / Setter de la classe
     * ===============================================
     */


    public Set<GrrEtablissementRegroupement> getEtablissements_principal() {
        return etablissements_principal;
    }

    public void setEtablissements_principal(Set<GrrEtablissementRegroupement> etablissements_principal) {
        this.etablissements_principal = etablissements_principal;
    }

    public Set<GrrEtablissementRegroupement> getEtablissements_secondaire() {
        return etablissements_secondaire;
    }

    public void setEtablissements_secondaire(Set<GrrEtablissementRegroupement> etablissements_secondaire) {
        this.etablissements_secondaire = etablissements_secondaire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<GrrSite> getGrr_j_etablissement_site() {
        return grr_j_etablissement_site;
    }

    public void setGrr_j_etablissement_site(List<GrrSite> grr_j_etablissement_site) {
        this.grr_j_etablissement_site = grr_j_etablissement_site;
    }

    public Set<GrrUtilisateurs> getGrr_j_etablisssment_useradmin() {
        return grr_j_etablisssment_useradmin;
    }

    public void setGrr_j_etablisssment_useradmin(Set<GrrUtilisateurs> grr_j_etablisssment_useradmin) {
        this.grr_j_etablisssment_useradmin = grr_j_etablisssment_useradmin;
    }

    public Set<GrrUtilisateurs> getGrr_j_etablissement_user() {
        return grr_j_etablissement_user;
    }

    public void setGrr_j_etablissement_user(Set<GrrUtilisateurs> grr_j_etablissement_user) {
        this.grr_j_etablissement_user = grr_j_etablissement_user;
    }


    @Override
    public String toString() {
        return "GrrEtablissement{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", shortname='" + shortname + '\'' +
                ", name='" + name + '\'' +
                ", codepostal='" + codepostal + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }

    public Set<GrrTypeArea> getGrr_j_etablissement_type_area() {
        return grr_j_etablissement_type_area;
    }

    public void setGrr_j_etablissement_type_area(Set<GrrTypeArea> grr_j_etablissement_type_area) {
        this.grr_j_etablissement_type_area = grr_j_etablissement_type_area;
    }

    public Set<GrrJEtablissementCalendar> getGrrJEtablissementCalendars() {
        return grrJEtablissementCalendars;
    }

    public void setGrrJEtablissementCalendars(Set<GrrJEtablissementCalendar> grrJEtablissementCalendars) {
        this.grrJEtablissementCalendars = grrJEtablissementCalendars;
    }
}

