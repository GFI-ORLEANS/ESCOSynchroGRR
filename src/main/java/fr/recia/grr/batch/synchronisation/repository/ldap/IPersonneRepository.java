package fr.recia.grr.batch.synchronisation.repository.ldap;

import fr.recia.grr.batch.synchronisation.entity.ldap.ODMPersonne;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * ODMPersonne Repository
 */


@Repository
public interface IPersonneRepository extends LdapRepository<ODMPersonne> {


    /**
     * @param uid
     * @return GrrUtilisateurs par son uid dans ldap
     */
    Optional<ODMPersonne> findByUid(String uid);

    default List<ODMPersonne> findPersonnesMigration(String queryFilter, String ldt) {
        LdapQuery query = query().filter(queryFilter, ldt);
        return (List<ODMPersonne>) findAll(query);
    }

 }
