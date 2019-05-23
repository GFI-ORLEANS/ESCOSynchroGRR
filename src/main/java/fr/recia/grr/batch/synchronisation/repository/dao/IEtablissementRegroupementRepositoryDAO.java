package fr.recia.grr.batch.synchronisation.repository.dao;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrEtablissementRegroupement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Grr_etablissement Repository
 */

@Repository
public interface IEtablissementRegroupementRepositoryDAO extends JpaRepository<GrrEtablissementRegroupement,Integer> {



    String deleteByCode_etablissement_secondaire = "delete from GrrEtablissementRegroupement p where p.code_etablissement_secondaire = :code";

    @Modifying
    @Query(deleteByCode_etablissement_secondaire)
    void deleteByCode_etablissement_secondaire(@Param("code") String code);



    String deleteByCode_etablissement_principal = "delete from GrrEtablissementRegroupement p where p.code_etablissement_principal = :code";

    @Modifying
    @Query(deleteByCode_etablissement_principal)
    void deleteByCode_etablissement_principal(@Param("code") String code);


}
