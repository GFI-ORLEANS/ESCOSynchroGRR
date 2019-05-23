package fr.recia.grr.batch.synchronisation.repository.dao;


import fr.recia.grr.batch.synchronisation.entity.dao.GrrLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * GrrLog Repository
 */

@Repository
public interface ILogRepositoryDAO extends JpaRepository<GrrLog,String> {


    /**
     *
     * @param dateRequis
     * @return Cette étape a pour but la suppression dans la base GRR des enregistrements de log datant de plus de « n » années (valeur paramétrée)
     */
    @Modifying
    @Query("delete from GrrLog g where g.start < :dateRequis")
    int deletefrom(@Param("dateRequis") LocalDateTime dateRequis);




}
