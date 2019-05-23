package fr.recia.grr.batch.synchronisation.entity.dao;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Table(name = "grr_repeat")
public class GrrRepeat extends GrrEntry implements Serializable {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @OneToMany(mappedBy = "reservationRepeat")
    private Collection<GrrEntry> reservation;

    /**
     * Constructeur par defaut.
     */
    public GrrRepeat() {
        super();
    }

    /*
     * ===============================================
     * Getter / Setter de la classe
     * ===============================================
     */

    /**
     * Getter de la proprieté reservation
     * @return la proprieté reservation
     */
    public Collection<GrrEntry> getReservation() {
        return reservation;
    }

    /**
     * Setter de la proprieté reservation
     * @param reservation
     */
    public void setReservation(Collection<GrrEntry> reservation) {
        this.reservation = reservation;
    }
}
