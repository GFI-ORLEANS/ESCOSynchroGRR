package fr.recia.grr.batch.migration.repository;

import fr.recia.grr.batch.migration.entity.GrrArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Grr_Area Repository
 */

@Repository
public interface IAreaMigrationRepositoryDAO extends JpaRepository<GrrArea,Integer> {

}
