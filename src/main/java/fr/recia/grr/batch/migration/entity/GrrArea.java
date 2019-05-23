package fr.recia.grr.batch.migration.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grr_area")
public class GrrArea implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "access" ,columnDefinition="char(1)")
    private String access;

    @Column(name = "order_display")
    private Short order_display;

    @Column(name = "ip_adr")
    private String ip_adr;

    @Column(name = "morningstarts_area")
    private Short morningstarts_area;

    @Column(name = "eveningends_area")
    private Short eveningends_area;

    @Column(name = "duree_max_resa_area")
    private Integer duree_max_resa_area;

    @Column(name = "resolution_area")
    private Integer resolution_area;

    @Column(name = "eveningends_minutes_area")
    private Short eveningends_minutes_area;

    @Column(name = "weekstarts_area")
    private Short weekstarts_area;

    @Column(name = "twentyfourhour_format_area")
    private Short twentyfourhour_format_area;

    @Column(name = "calendar_default_values",columnDefinition="char(1)")
    private String calendar_default_values;

    @Column(name = "enable_periods",columnDefinition="char(1)")
    private String enable_periods;

    @Column(name = "display_days")
    private String display_days;

    @Column(name = "id_type_par_defaut")
    private Integer id_type_par_defaut;

    @Column(name = "duree_par_defaut_reservation_area")
    private Integer duree_par_defaut_reservation_area;

    @Column(name = "max_booking")
    private Short max_booking;


    @ManyToMany(mappedBy = "grr_j_site_area", fetch=FetchType.EAGER)
    private Set<GrrSite> grr_j_area_site;

    @ManyToMany(mappedBy = "grr_j_type_area", fetch=FetchType.EAGER)
    private Set<GrrTypeArea> grr_j_area_typeArea;


    @OneToMany(
            mappedBy = "area_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch=FetchType.EAGER
    )
    private Set<GrrRoom> grrRooms = new HashSet<>();

    @OneToMany(
            mappedBy = "id_area",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch=FetchType.EAGER
    )
    private Set<GrrOverload> grrOverloads = new HashSet<>();



    @OneToMany(
            mappedBy = "id_area",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch=FetchType.EAGER
    )
    private Set<GrrAreaPeriode> grrAreaPeriodes = new HashSet<>();



    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */

    /**
     * Constructeur par defaut.
     */
    public GrrArea() {
        grr_j_area_site = new HashSet<>();
    }

    /**
     * Constructeur avec tous les parametres.
     */

    public GrrArea(Integer id, String areaName, String access, Short order_display, String ip_adr, Short morningstarts_area, Short eveningends_area, Integer duree_max_resa_area, Integer resolution_area, Short eveningends_minutes_area, Short weekstarts_area, Short twentyfourhour_format_area, String calendar_default_values, String enable_periods, String display_days, Integer id_type_par_defaut, Integer duree_par_defaut_reservation_area, Short max_booking) {
        this.id = id;
        this.areaName = areaName;
        this.access = access;
        this.order_display = order_display;
        this.ip_adr = ip_adr;
        this.morningstarts_area = morningstarts_area;
        this.eveningends_area = eveningends_area;
        this.duree_max_resa_area = duree_max_resa_area;
        this.resolution_area = resolution_area;
        this.eveningends_minutes_area = eveningends_minutes_area;
        this.weekstarts_area = weekstarts_area;
        this.twentyfourhour_format_area = twentyfourhour_format_area;
        this.calendar_default_values = calendar_default_values;
        this.enable_periods = enable_periods;
        this.display_days = display_days;
        this.id_type_par_defaut = id_type_par_defaut;
        this.duree_par_defaut_reservation_area = duree_par_defaut_reservation_area;
        this.max_booking = max_booking;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public Short getOrder_display() {
        return order_display;
    }

    public void setOrder_display(Short order_display) {
        this.order_display = order_display;
    }

    public String getIp_adr() {
        return ip_adr;
    }

    public void setIp_adr(String ip_adr) {
        this.ip_adr = ip_adr;
    }

    public Short getMorningstarts_area() {
        return morningstarts_area;
    }

    public void setMorningstarts_area(Short morningstarts_area) {
        this.morningstarts_area = morningstarts_area;
    }

    public Short getEveningends_area() {
        return eveningends_area;
    }

    public void setEveningends_area(Short eveningends_area) {
        this.eveningends_area = eveningends_area;
    }

    public Integer getDuree_max_resa_area() {
        return duree_max_resa_area;
    }

    public void setDuree_max_resa_area(Integer duree_max_resa_area) {
        this.duree_max_resa_area = duree_max_resa_area;
    }

    public Integer getResolution_area() {
        return resolution_area;
    }

    public void setResolution_area(Integer resolution_area) {
        this.resolution_area = resolution_area;
    }

    public Short getEveningends_minutes_area() {
        return eveningends_minutes_area;
    }

    public void setEveningends_minutes_area(Short eveningends_minutes_area) {
        this.eveningends_minutes_area = eveningends_minutes_area;
    }

    public Short getWeekstarts_area() {
        return weekstarts_area;
    }

    public void setWeekstarts_area(Short weekstarts_area) {
        this.weekstarts_area = weekstarts_area;
    }

    public Short getTwentyfourhour_format_area() {
        return twentyfourhour_format_area;
    }

    public void setTwentyfourhour_format_area(Short twentyfourhour_format_area) {
        this.twentyfourhour_format_area = twentyfourhour_format_area;
    }

    public String getCalendar_default_values() {
        return calendar_default_values;
    }

    public void setCalendar_default_values(String calendar_default_values) {
        this.calendar_default_values = calendar_default_values;
    }

    public String getEnable_periods() {
        return enable_periods;
    }

    public void setEnable_periods(String enable_periods) {
        this.enable_periods = enable_periods;
    }

    public String getDisplay_days() {
        return display_days;
    }

    public void setDisplay_days(String display_days) {
        this.display_days = display_days;
    }

    public Integer getId_type_par_defaut() {
        return id_type_par_defaut;
    }

    public void setId_type_par_defaut(Integer id_type_par_defaut) {
        this.id_type_par_defaut = id_type_par_defaut;
    }

    public Integer getDuree_par_defaut_reservation_area() {
        return duree_par_defaut_reservation_area;
    }

    public void setDuree_par_defaut_reservation_area(Integer duree_par_defaut_reservation_area) {
        this.duree_par_defaut_reservation_area = duree_par_defaut_reservation_area;
    }

    public Short getMax_booking() {
        return max_booking;
    }

    public void setMax_booking(Short max_booking) {
        this.max_booking = max_booking;
    }

    public Collection<GrrSite> getGrr_j_area_site() {
        return grr_j_area_site;
    }

    public void setGrr_j_area_site(HashSet<GrrSite> grr_j_area_site) {
        this.grr_j_area_site = grr_j_area_site;
    }

    public Collection<GrrTypeArea> getGrr_j_area_typeArea() {
        return grr_j_area_typeArea;
    }

    public void setGrr_j_area_typeArea(HashSet<GrrTypeArea> grr_j_area_typeArea) {
        this.grr_j_area_typeArea = grr_j_area_typeArea;
    }

    /*
     * ===============================================
     * toString de la classe
     * ===============================================
     */


    @Override
    public String toString() {
        return "GrrArea{" +
                "id=" + id +
                ", areaName='" + areaName + '\'' +
                ", access='" + access + '\'' +
                '}';
    }

    public void setGrr_j_area_site(Set<GrrSite> grr_j_area_site) {
        this.grr_j_area_site = grr_j_area_site;
    }

    public void setGrr_j_area_typeArea(Set<GrrTypeArea> grr_j_area_typeArea) {
        this.grr_j_area_typeArea = grr_j_area_typeArea;
    }

    public Set<GrrRoom> getGrrRooms() {
        return grrRooms;
    }

    public void setGrrRooms(Set<GrrRoom> grrRooms) {
        this.grrRooms = grrRooms;
    }

    public Set<GrrOverload> getGrrOverloads() {
        return grrOverloads;
    }

    public void setGrrOverloads(Set<GrrOverload> grrOverloads) {
        this.grrOverloads = grrOverloads;
    }

    public Set<GrrAreaPeriode> getGrrAreaPeriodes() {
        return grrAreaPeriodes;
    }

    public void setGrrAreaPeriodes(Set<GrrAreaPeriode> grrAreaPeriodes) {
        this.grrAreaPeriodes = grrAreaPeriodes;
    }
}
