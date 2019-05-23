package fr.recia.grr.batch.synchronisation.repository.dao;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Grr_Area Repository
 */

@Repository
public interface IRoomRepositoryDAO extends JpaRepository<GrrRoom,Integer> {

}
