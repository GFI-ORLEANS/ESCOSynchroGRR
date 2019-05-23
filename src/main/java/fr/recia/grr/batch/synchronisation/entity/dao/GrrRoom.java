package fr.recia.grr.batch.synchronisation.entity.dao;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "grr_room")
public class GrrRoom  implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "area_id" )
    private GrrArea area_id;

    @Column(name = "room_name" )
    private String room_name;

    @Column(name = "description")
    private String description;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "max_booking")
    private Short max_booking;

    @Column(name = "statut_room",columnDefinition="char(1)")
    private String statut_room;

    @Column(name = "show_fic_room",columnDefinition="char(1)")
    private String show_fic_room;

    @Column(name = "picture_room")
    private String picture_room;

    @Column(name = "comment_room", columnDefinition = "TEXT")
    private String comment_room;

    @Column(name = "show_comment",columnDefinition="char(1)")
    private String show_comment;

    @Column(name = "delais_max_resa_room")
    private Short delais_max_resa_room;

    @Column(name = "delais_min_resa_room")
    private Short delais_min_resa_room;

    @Column(name = "allow_action_in_past",columnDefinition="char(1)")
    private String allow_action_in_past;

    @Column(name = "dont_allow_modify",columnDefinition="char(1)")
    private String dont_allow_modify;

    @Column(name = "order_display")
    private Short order_display;

    @Column(name = "delais_option_reservation")
    private Short delais_option_reservation;

    @Column(name = "type_affichage_reser")
    private Short type_affichage_reser;

    @Column(name = "moderate",columnDefinition = "BIT")
    private Short moderate;

    @Column(name = "qui_peut_reserver_pour",columnDefinition="char(1)")
    private String qui_peut_reserver_pour;

    @Column(name = "active_ressource_empruntee",columnDefinition="char(1)")
    private String active_ressource_empruntee;


    @Column(name = "who_can_see")
    private Short who_can_see;

    @OneToMany(
            mappedBy = "room_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<GrrEntry> grrEntries = new ArrayList<>();

    @OneToMany(
            mappedBy = "room_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<GrrEntryModerate> grrEntryModerates = new ArrayList<>();






    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */

    /**
     * Constructeur par defaut.
     */
    public GrrRoom() {

    }

    /**
     * Constructeur avec tous les parametres.
     */
    public GrrRoom(Integer id, GrrArea area_id, String room_name, String description, Integer capacity, Short max_booking, String statut_room, String show_fic_room, String picture_room, String comment_room, String show_comment, Short delais_max_resa_room, Short delais_min_resa_room, String allow_action_in_past, String dont_allow_modify, Short order_display, Short delais_option_reservation, Short type_affichage_reser, Short moderate, String qui_peut_reserver_pour, String active_ressource_empruntee, Short who_can_see) {
        this.id = id;
        this.area_id = area_id;
        this.room_name = room_name;
        this.description = description;
        this.capacity = capacity;
        this.max_booking = max_booking;
        this.statut_room = statut_room;
        this.show_fic_room = show_fic_room;
        this.picture_room = picture_room;
        this.comment_room = comment_room;
        this.show_comment = show_comment;
        this.delais_max_resa_room = delais_max_resa_room;
        this.delais_min_resa_room = delais_min_resa_room;
        this.allow_action_in_past = allow_action_in_past;
        this.dont_allow_modify = dont_allow_modify;
        this.order_display = order_display;
        this.delais_option_reservation = delais_option_reservation;
        this.type_affichage_reser = type_affichage_reser;
        this.moderate = moderate;
        this.qui_peut_reserver_pour = qui_peut_reserver_pour;
        this.active_ressource_empruntee = active_ressource_empruntee;

        this.who_can_see = who_can_see;
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

    public GrrArea getArea_id() {
        return area_id;
    }

    public void setArea_id(GrrArea area_id) {
        this.area_id = area_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Short getMax_booking() {
        return max_booking;
    }

    public void setMax_booking(Short max_booking) {
        this.max_booking = max_booking;
    }

    public String getStatut_room() {
        return statut_room;
    }

    public void setStatut_room(String statut_room) {
        this.statut_room = statut_room;
    }

    public String getShow_fic_room() {
        return show_fic_room;
    }

    public void setShow_fic_room(String show_fic_room) {
        this.show_fic_room = show_fic_room;
    }

    public String getPicture_room() {
        return picture_room;
    }

    public void setPicture_room(String picture_room) {
        this.picture_room = picture_room;
    }

    public String getComment_room() {
        return comment_room;
    }

    public void setComment_room(String comment_room) {
        this.comment_room = comment_room;
    }

    public String getShow_comment() {
        return show_comment;
    }

    public void setShow_comment(String show_comment) {
        this.show_comment = show_comment;
    }

    public Short getDelais_max_resa_room() {
        return delais_max_resa_room;
    }

    public void setDelais_max_resa_room(Short delais_max_resa_room) {
        this.delais_max_resa_room = delais_max_resa_room;
    }

    public Short getDelais_min_resa_room() {
        return delais_min_resa_room;
    }

    public void setDelais_min_resa_room(Short delais_min_resa_room) {
        this.delais_min_resa_room = delais_min_resa_room;
    }

    public String getAllow_action_in_past() {
        return allow_action_in_past;
    }

    public void setAllow_action_in_past(String allow_action_in_past) {
        this.allow_action_in_past = allow_action_in_past;
    }

    public String getDont_allow_modify() {
        return dont_allow_modify;
    }

    public void setDont_allow_modify(String dont_allow_modify) {
        this.dont_allow_modify = dont_allow_modify;
    }

    public Short getOrder_display() {
        return order_display;
    }

    public void setOrder_display(Short order_display) {
        this.order_display = order_display;
    }

    public Short getDelais_option_reservation() {
        return delais_option_reservation;
    }

    public void setDelais_option_reservation(Short delais_option_reservation) {
        this.delais_option_reservation = delais_option_reservation;
    }

    public Short getType_affichage_reser() {
        return type_affichage_reser;
    }

    public void setType_affichage_reser(Short type_affichage_reser) {
        this.type_affichage_reser = type_affichage_reser;
    }

    public Short getModerate() {
        return moderate;
    }

    public void setModerate(Short moderate) {
        this.moderate = moderate;
    }

    public String getQui_peut_reserver_pour() {
        return qui_peut_reserver_pour;
    }

    public void setQui_peut_reserver_pour(String qui_peut_reserver_pour) {
        this.qui_peut_reserver_pour = qui_peut_reserver_pour;
    }

    public String getActive_ressource_empruntee() {
        return active_ressource_empruntee;
    }

    public void setActive_ressource_empruntee(String active_ressource_empruntee) {
        this.active_ressource_empruntee = active_ressource_empruntee;
    }


    public Short getWho_can_see() {
        return who_can_see;
    }

    public void setWho_can_see(Short who_can_see) {
        this.who_can_see = who_can_see;
    }

    public List<GrrEntry> getGrrEntries() {
        return grrEntries;
    }

    public void setGrrEntries(List<GrrEntry> grrEntries) {
        this.grrEntries = grrEntries;
    }

    public List<GrrEntryModerate> getGrrEntryModerates() {
        return grrEntryModerates;
    }

    public void setGrrEntryModerates(List<GrrEntryModerate> grrEntryModerates) {
        this.grrEntryModerates = grrEntryModerates;
    }

    /*
     * ===============================================
     * toString de la classe
     * ===============================================
     */


}
