package fr.recia.grr.batch.synchronisation.repository.dao;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;


/**
 * GrrEntry Repository
 */
@Repository
public interface IEntryRepositoryDAO extends JpaRepository<GrrEntry,Integer> {


    /**
     *
     * @param dateRequis
     * @return les reservation anciennes depuis N jours
     */
    @Query("select  r from GrrEntry r   where  r.timestamp <= :dateRequis ")
    Collection<GrrEntry> findReservationAcienneNjour(@Param("dateRequis") LocalDateTime dateRequis);

    /**
     *
     * @param dateRequis
     * @return les reservation anciennes depuis N jours
     */
    @Modifying
    @Query("delete from GrrEntry r   where  r.timestamp <= :dateRequis ")
    int deleteReservationAcienneNjour(@Param("dateRequis") LocalDateTime dateRequis);





}
