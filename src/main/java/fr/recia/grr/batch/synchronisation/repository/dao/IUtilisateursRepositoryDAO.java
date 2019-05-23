package fr.recia.grr.batch.synchronisation.repository.dao;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrUtilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;


/**
 * GrrUtilisateurs Repository
 */
@Repository
public interface IUtilisateursRepositoryDAO extends JpaRepository<GrrUtilisateurs,String> {


    /**
     * @param s
     * @return GrrUtilisateurs par son login dans la Base de donnée
     */
    Optional<GrrUtilisateurs> findByLogin(String s);






    /**
     *
     * @param dateRequis
     * @return les personnes non connecté depuis N jours sans doublons
     */
    @Query("select  p from GrrUtilisateurs p  inner join p.log log where  p.source= 'ext' and ( log.start < :dateRequis or log.start is null ) ")
    Set<GrrUtilisateurs> findUserConnecterNJour(@Param("dateRequis") LocalDateTime dateRequis);


    @Query("select  p from GrrUtilisateurs p join p.log log")
    Set<GrrUtilisateurs> allUsersConnecterPlus1fois();


}
