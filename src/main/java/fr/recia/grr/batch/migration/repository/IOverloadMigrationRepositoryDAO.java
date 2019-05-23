package fr.recia.grr.batch.migration.repository;

import fr.recia.grr.batch.migration.entity.GrrOverload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Grr_Area Repository
 */

@Repository
public interface IOverloadMigrationRepositoryDAO extends JpaRepository<GrrOverload,Integer> {

}
