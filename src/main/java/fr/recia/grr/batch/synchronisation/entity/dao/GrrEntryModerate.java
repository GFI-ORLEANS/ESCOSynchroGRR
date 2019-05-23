package fr.recia.grr.batch.synchronisation.entity.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "grr_entry_moderate")
public class GrrEntryModerate extends GrrEntry implements Serializable {



    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private GrrRoom room_id;


    /*
     * ===============================================
     * Constructeurs de la classe
     * ===============================================
     */


    public GrrEntryModerate() {
        super();
    }


    /*
     * ===============================================
     * Getter / Setter de la classe
     * ===============================================
     */

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public GrrRoom getRoom_id() {
        return room_id;
    }

    @Override
    public void setRoom_id(GrrRoom room_id) {
        this.room_id = room_id;
    }


}
