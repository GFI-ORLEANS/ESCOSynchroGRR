package fr.recia.grr.batch.synchronisation.repository.dao;


import fr.recia.grr.batch.synchronisation.entity.dao.GrrSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * GrrSite Repository
 */

@Repository
public interface ISiteRepositoryDAO extends JpaRepository<GrrSite,Integer> {

    /**
     * @param sitecode
     * @return GrrSite par son code dans la base de donnée
     */
    GrrSite findAllBySitecode(String sitecode);
    GrrSite findAllBySitecodeAndSitename(String sitecode, String sitename);


}
