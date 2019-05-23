package fr.recia.grr.batch.synchronisation.repository.dao;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrEtablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Grr_etablissement Repository
 */

@Repository
public interface IEtablissementRepositoryDAO extends JpaRepository<GrrEtablissement,Integer> {

    /**
     * @param code
     * @return Grr_etablissement par son code dans la base de donnée
     */
    Optional<GrrEtablissement> findByCode(String code);

}
