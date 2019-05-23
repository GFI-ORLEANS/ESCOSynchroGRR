package fr.recia.grr.batch.synchronisation.entity.dao;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="grr_utilisateurs")
public class GrrUtilisateurs implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "password")
    private String password="";

    @Column(name = "email")
    private String email;

    @Column(name = "statut")
    private String statut;

    @Column(name = "etat")
    private String etat;

    @Column(name = "default_site")
    private Short default_site=0;

    @Column(name = "default_area")
    private Short default_area=0;

    @Column(name = "default_etablissement")
    private Short default_etablissement;

    @Column(name = "default_room")
    private Short default_room = 0;

    @Column(name = "default_style")
    private String default_style= "";

    @Column(name = "default_list_type")
    private String default_list_type="";

    @Column(name = "default_language",columnDefinition="char(3)")
    private String default_language ="";

    @Column(name = "source")
    private String source;

    @OneToMany(mappedBy = "user")
    private Set<GrrLog> log;

    @ManyToMany
    @JoinTable(name ="grr_j_user_area",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "id_area"))
    private Set<GrrArea> grr_j_user_area;

    @ManyToMany
    @JoinTable(name ="grr_j_user_room",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "id_room"))
    private Set<GrrRoom> grr_j_user_room;


    @ManyToMany
    @JoinTable(name ="grr_j_mailuser_room",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "id_room"))
    private Set<GrrRoom> grr_j_mailuser_room;

    @ManyToMany
    @JoinTable(name ="grr_j_useradmin_area",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "id_area"))
    private Set<GrrArea> grr_j_useradmin_area;

    @ManyToMany
    @JoinTable(name ="grr_j_useradmin_site",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "id_site"))
    private Set<GrrSite> grr_j_useradmin_site;

    @ManyToMany
    @JoinTable(name ="grr_j_useradmin_etablissement",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "id_etablissement"))
    private Set<GrrEtablissement> grr_j_useradmin_etablissement;

    @ManyToMany
    @JoinTable(name ="grr_j_user_etablissement",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "id_etablissement"))
    private Set<GrrEtablissement> grr_j_user_etablissement;


    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */

    /**
     * Constructeur par defaut.
     */
    public GrrUtilisateurs() {
        grr_j_user_etablissement = new HashSet<>();
        grr_j_useradmin_etablissement = new HashSet<>();
        grr_j_useradmin_site = new HashSet<>();
        grr_j_useradmin_area = new HashSet<>();
        grr_j_user_area = new HashSet<>();
        this.setDefault_site((short) 0);
        this.setDefault_area((short) 0);
        this.setDefault_room((short) 0);

    }

    /**
     * Constructeur avec tous les parametres.
     */
    public GrrUtilisateurs(String login, String nom, String prenom, String password, String email, String statut, String etat, Short default_site, Short default_area, Short default_etablissement, Short default_room, String default_style, String default_list_type, String default_language, String source) {
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.statut = statut;
        this.etat = etat;
        this.default_site = default_site;
        this.default_area = default_area;
        this.default_etablissement = default_etablissement;
        this.default_room = default_room;
        this.default_style = default_style;
        this.default_list_type = default_list_type;
        this.default_language = default_language;
        this.source = source;
    }

    public GrrUtilisateurs(String uid) {
        grr_j_user_etablissement = new HashSet<>();
        grr_j_useradmin_etablissement = new HashSet<>();
        grr_j_useradmin_site = new HashSet<>();
        grr_j_useradmin_area = new HashSet<>();
        grr_j_user_area = new HashSet<>();
        this.setDefault_site((short) 0);
        this.setDefault_area((short) 0);
        this.setDefault_room((short) 0);

        this.login = uid;

    }
    /*
     * ===============================================
     * Getter / Setter de la classe
     * ===============================================
     */

    /**
     * Getter de la proprieté login
     * @return la proprieté login
     */
    public String getLogin() {
        return login;
    }


    /**
     * Setter de la proprieté login
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }


    /**
     * Getter de la proprieté nom
     * @return la proprieté nom
     */
    public String getNom() {
        return nom;
    }


    /**
     * Setter de la proprieté nom
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * Getter de la proprieté prenom
     * @return la proprieté prenom
     */
    public String getPrenom() {
        return prenom;
    }


    /**
     * Setter de la proprieté prenom
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    /**
     * Getter de la proprieté password
     * @return la proprieté password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Setter de la proprieté password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Getter de la proprieté email
     * @return la proprieté email
     */
    public String getEmail() {
        return email;
    }


    /**
     * Setter de la proprieté email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Getter de la proprieté statut
     * @return la proprieté statut
     */
    public String getStatut() {
        return statut;
    }


    /**
     * Setter de la proprieté statut
     * @param statut
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }


    /**
     * Getter de la proprieté etat
     * @return la proprieté etat
     */
    public String getEtat() {
        return etat;
    }



    /**
     * Setter de la proprieté etat
     * @param etat
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }


    /**
     * Getter de la proprieté default_site
     * @return la proprieté default_site
     */
    public Short getDefault_site() {
        return default_site;
    }



    /**
     * Setter de la proprieté default_site
     * @param default_site
     */
    public void setDefault_site(Short default_site) {
        this.default_site = default_site;
    }


    /**
     * Getter de la proprieté default_area
     * @return la proprieté default_area
     */
    public Short getDefault_area() {
        return default_area;
    }



    /**
     * Setter de la proprieté default_area
     * @param default_area
     */
    public void setDefault_area(Short default_area) {
        this.default_area = default_area;
    }


    /**
     * Getter de la proprieté default_room
     * @return la proprieté default_room
     */
    public Short getDefault_room() {
        return default_room;
    }


    /**
     * Setter de la proprieté default_room
     * @param default_room
     */
    public void setDefault_room(Short default_room) {
        this.default_room = default_room;
    }


    /**
     * Getter de la proprieté default_style
     * @return la proprieté default_style
     */
    public String getDefault_style() {
        return default_style;
    }


    /**
     * Setter de la proprieté default_style
     * @param default_style
     */
    public void setDefault_style(String default_style) {
        this.default_style = default_style;
    }


    /**
     * Getter de la proprieté default_list_type
     * @return la proprieté default_list_type
     */
    public String getDefault_list_type() {
        return default_list_type;
    }


    /**
     * Setter de la proprieté default_list_type
     * @param default_list_type
     */
    public void setDefault_list_type(String default_list_type) {
        this.default_list_type = default_list_type;
    }


    /**
     * Getter de la proprieté default_language
     * @return la proprieté default_language
     */
    public String getDefault_language() {
        return default_language;
    }


    /**
     * Setter de la proprieté default_language
     * @param default_language
     */
    public void setDefault_language(String default_language) {
        this.default_language = default_language;
    }


    /**
     * Getter de la proprieté source
     * @return la proprieté source
     */
    public String getSource() {
        return source;
    }


    /**
     * Setter de la proprieté source
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }


    /**
     * Getter de la proprieté default_etablissement
     * @return la proprieté default_etablissement
     */
    public Short getDefault_etablissement() {
        return default_etablissement;
    }

    /**
     * Setter de la proprieté default_etablissement
     * @param default_etablissement
     */
    public void setDefault_etablissement(Short default_etablissement) {
        this.default_etablissement = default_etablissement;
    }

    /**
     * Getter de la proprieté log
     * @return la proprieté log
     */
    public Set<GrrLog> getLog() {
        return log;
    }

    /**
     * Setter de la proprieté log
     * @param log
     */
    public void setLog(Set<GrrLog> log) {
        this.log = log;
    }

    public Set<GrrArea> getGrr_j_user_area() {
        return grr_j_user_area;
    }

    public void setGrr_j_user_area(Set<GrrArea> grr_j_user_area) {
        this.grr_j_user_area = grr_j_user_area;
    }

    public Set<GrrArea> getGrr_j_useradmin_area() {
        return grr_j_useradmin_area;
    }

    public void setGrr_j_useradmin_area(Set<GrrArea> grr_j_useradmin_area) {
        this.grr_j_useradmin_area = grr_j_useradmin_area;
    }

    public Set<GrrRoom> getGrr_j_mailuser_room() {
        return grr_j_mailuser_room;
    }

    public void setGrr_j_mailuser_room(Set<GrrRoom> grr_j_mailuser_room) {
        this.grr_j_mailuser_room = grr_j_mailuser_room;
    }

    public Set<GrrSite> getGrr_j_useradmin_site() {
        return grr_j_useradmin_site;
    }

    public Set<GrrRoom> getGrr_j_user_room() {
        return grr_j_user_room;
    }

    public void setGrr_j_user_room(Set<GrrRoom> grr_j_user_room) {
        this.grr_j_user_room = grr_j_user_room;
    }

    public void setGrr_j_useradmin_site(Set<GrrSite> grr_j_useradmin_site) {
        this.grr_j_useradmin_site = grr_j_useradmin_site;
    }

    public Set<GrrEtablissement> getGrr_j_useradmin_etablissement() {
        return grr_j_useradmin_etablissement;
    }

    public void setGrr_j_useradmin_etablissement(Set<GrrEtablissement> grr_j_useradmin_etablissement) {
        this.grr_j_useradmin_etablissement = grr_j_useradmin_etablissement;
    }

    public Set<GrrEtablissement> getGrr_j_user_etablissement() {
        return grr_j_user_etablissement;
    }

    public void setGrr_j_user_etablissement(Set<GrrEtablissement> grr_j_user_etablissement) {
        this.grr_j_user_etablissement = grr_j_user_etablissement;
    }

    /*
     * ===============================================
     * toString de la classe
     * ===============================================
     */
    @Override
    public String toString() {
        return "GrrUtilisateurs{" +
                "login='" + login + '\'' +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", etat='" + etat + '\'' +
                '}';
    }




}
