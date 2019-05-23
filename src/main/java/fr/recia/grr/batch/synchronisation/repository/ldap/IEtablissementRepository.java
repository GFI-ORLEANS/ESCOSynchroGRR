package fr.recia.grr.batch.synchronisation.repository.ldap;

import fr.recia.grr.batch.synchronisation.entity.ldap.ODMStructure;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * ODMStructure Repository
 */


@Repository
public interface IEtablissementRepository extends LdapRepository<ODMStructure> {



    /**
     * @param code
     * @return ODMStructure dans ldap
     */
    Optional<ODMStructure> findByCode(String code);
    Optional<ODMStructure> findByNomLong(String code);

    default Optional<ODMStructure> findByCodeAll(String ldt) {
        Optional<ODMStructure> byCode = Optional.ofNullable(findByCode(ldt).orElse(findByNomLong(ldt).orElse(null)));
 
        return (Optional<ODMStructure>) byCode;
    }



    default List<ODMStructure> findRecent(String queryFilter, String ldt) {
        LdapQuery query = query().filter(queryFilter, ldt);
        return (List<ODMStructure>) findAll(query);
    }


}
