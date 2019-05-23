package fr.recia.grr.batch.synchronisation.entity.dao;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "grr_site")
public class GrrSite implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sitecode")
    private String sitecode;

    @Column(name = "sitename")
    private String sitename;

    @Column(name = "adresse_ligne1")
    private String adresse_ligne1;

    @Column(name = "adresse_ligne2")
    private String adresse_ligne2;

    @Column(name = "adresse_ligne3")
    private String adresse_ligne3;

    @Column(name = "cp")
    private String codepostal;

    @Column(name = "ville")
    private String ville;

    @Column(name = "pays")
    private String pays;

    @Column(name = "tel")
    private String tel;

    @Column(name = "fax")
    private String fax;

    @ManyToMany(mappedBy ="grr_j_useradmin_etablissement",cascade=CascadeType.ALL )
    private Set<GrrUtilisateurs> grr_j_site_useradmin;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name ="grr_j_site_area",
        joinColumns = @JoinColumn(name = "id_site"),
        inverseJoinColumns = @JoinColumn(name = "id_area"))
    private Set<GrrArea> grr_j_site_area;

    @ManyToMany(mappedBy ="grr_j_etablissement_site" )
    private Set<GrrEtablissement> grr_j_site_etablissement;



    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */

    /**
     * Constructeur par defaut.
     */
    public GrrSite() {
        grr_j_site_etablissement = new HashSet<>();
        grr_j_site_area = new HashSet<>();
        grr_j_site_useradmin = new HashSet<>();
    }

    /**
     * Constructeur avec tous les parametres.
     */

    public GrrSite(Integer id, String sitecode, String sitename, String adresse_ligne1, String adresse_lign2, String adresse_ligne3, String codepostal, String ville, String pays, String tel, String fax) {
        this.id = id;
        this.sitecode = sitecode;
        this.sitename = sitename;
        this.adresse_ligne1 = adresse_ligne1;
        this.adresse_ligne2 = adresse_lign2;
        this.adresse_ligne3 = adresse_ligne3;
        this.codepostal = codepostal;
        this.ville = ville;
        this.pays = pays;
        this.tel = tel;
        this.fax = fax;
    }

    /*
     * ===============================================
     * Getter / Setter de la classe
     * ===============================================
     */

    public Set<GrrUtilisateurs> getGrr_j_site_useradmin() {
        return grr_j_site_useradmin;
    }

    public void setGrr_j_site_useradmin(Set<GrrUtilisateurs> grr_j_site_useradmin) {
        this.grr_j_site_useradmin = grr_j_site_useradmin;
    }

    public Set<GrrArea> getGrr_j_site_area() {
        return grr_j_site_area;
    }

    public void setGrr_j_site_area(Set<GrrArea> grr_j_site_area) {
        this.grr_j_site_area = grr_j_site_area;
    }

    public Set<GrrEtablissement> getGrr_j_site_etablissement() {
        return grr_j_site_etablissement;
    }

    public void setGrr_j_site_etablissement(Set<GrrEtablissement> grr_j_site_etablissement) {
        this.grr_j_site_etablissement = grr_j_site_etablissement;
    }

    /**
     * Getter de la proprieté id
     * @return la proprieté id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter de la proprieté id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter de la proprieté sitecode
     * @return la proprieté sitecode
     */
    public String getSitecode() {
        return sitecode;
    }

    /**
     * Setter de la proprieté sitecode
     * @param sitecode
     */
    public void setSitecode(String sitecode) {
        this.sitecode = sitecode;
    }

    /**
     * Getter de la proprieté sitename
     * @return la proprieté sitename
     */
    public String getSitename() {
        return sitename;
    }

    /**
     * Setter de la proprieté sitename
     * @param sitename
     */
    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    /**
     * Getter de la proprieté adresse_ligne1
     * @return la proprieté adresse_ligne1
     */
    public String getAdresse_ligne1() {
        return adresse_ligne1;
    }

    /**
     * Setter de la proprieté adresse_ligne1
     * @param adresse_ligne1
     */
    public void setAdresse_ligne1(String adresse_ligne1) {
        this.adresse_ligne1 = adresse_ligne1;
    }

    /**
     * Getter de la proprieté adresse_ligne2
     * @return la proprieté adresse_ligne2
     */
    public String getAdresse_ligne2() {
        return adresse_ligne2;
    }

    /**
     * Setter de la proprieté adresse_ligne2
     * @param adresse_ligne2
     */
    public void setAdresse_ligne2(String adresse_ligne2) {
        this.adresse_ligne2 = adresse_ligne2;
    }

    /**
     * Getter de la proprieté adresse_ligne3
     * @return la proprieté adresse_ligne3
     */
    public String getAdresse_ligne3() {
        return adresse_ligne3;
    }

    /**
     * Setter de la proprieté adresse_ligne3
     * @param adresse_ligne3
     */
    public void setAdresse_ligne3(String adresse_ligne3) {
        this.adresse_ligne3 = adresse_ligne3;
    }

    /**
     * Getter de la proprieté codepostal
     * @return la proprieté codepostal
     */
    public String getCodepostal() {
        return codepostal;
    }

    /**
     * Setter de la proprieté codepostal
     * @param codepostal
     */
    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }

    /**
     * Getter de la proprieté ville
     * @return la proprieté ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Setter de la proprieté ville
     * @param ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Getter de la proprieté pays
     * @return la proprieté pays
     */
    public String getPays() {
        return pays;
    }

    /**
     * Setter de la proprieté pays
     * @param pays
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    /**
     * Getter de la proprieté tel
     * @return la proprieté tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * Setter de la proprieté tel
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Getter de la proprieté fax
     * @return la proprieté fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * Setter de la proprieté fax
     * @param fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }



    /*
     * ===============================================
     * toString de la classe
     * ===============================================
     */
    @Override
    public String toString() {
        return "GrrSite{" +
                "id=" + id +
                ", sitecode='" + sitecode + '\'' +
                ", sitename='" + sitename + '\'' +
                ", adresse_ligne1='" + adresse_ligne1 + '\'' +
                ", adresse_ligne2='" + adresse_ligne2 + '\'' +
                ", adresse_ligne3='" + adresse_ligne3 + '\'' +
                ", codepostal='" + codepostal + '\'' +
                '}';
    }
}

