package fr.recia.grr.batch.synchronisation.entity.dao;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "grr_entry")
public class GrrEntry implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private GrrRoom room_id;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repeat_id")
    private GrrRepeat reservationRepeat;

    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */

    public GrrEntry() {
    }

    /*
     * ===============================================
     * Getter / Setter de la classe
     * ===============================================
     */

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
     * Getter de la proprieté timestamp
     * @return la proprieté timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Setter de la proprieté timestamp
     * @param timestamp
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Getter de la proprieté reservationRepeat
     * @return la proprieté reservationRepeat
     */
    public GrrRepeat getReservationRepeat() {
        return reservationRepeat;
    }


    /**
     * Setter de la proprieté reservationRepeat
     * @param reservationRepeat
     */
    public void setReservationRepeat(GrrRepeat reservationRepeat) {
        this.reservationRepeat = reservationRepeat;
    }

    public GrrRoom getRoom_id() {
        return room_id;
    }

    public void setRoom_id(GrrRoom room_id) {
        this.room_id = room_id;
    }


}
