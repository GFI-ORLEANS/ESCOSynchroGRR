package fr.recia.grr.batch.ldap;

import fr.recia.grr.batch.synchronisation.entity.ldap.ODMPersonne;
import fr.recia.grr.batch.synchronisation.entity.ldap.ODMStructure;
import fr.recia.grr.batch.synchronisation.repository.ldap.IEtablissementRepository;
import fr.recia.grr.batch.synchronisation.repository.ldap.IPersonneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest

public class SpringLdapIntegrationTest {

    @Autowired
    private IPersonneRepository personneRepository;

    @Autowired
    private IEtablissementRepository etablissementRepository;

    @Test
    public void testPersonFindByUid() {
        Optional<ODMPersonne> optPersonne = personneRepository.findByUid("F1000uh7");
        assertTrue(optPersonne.isPresent());
        assertEquals(optPersonne.map(ODMPersonne::getNom), Optional.of("BUKISA"));
    }

    @Value("${filter.ldap.structures}")
    private String queryStructures;


    @Value("${filter.ldap.people}")
    private String queryStructuresPerson;


    @Test
    public void testStructGetRecent() {
        List<ODMStructure> liste = etablissementRepository.findRecent(queryStructures, "20150219000000Z");
        assertThat(liste).isNotEmpty();
        assertThat(liste).size().isEqualTo(5);
    }

    @Test
    public void testPersonGetRecent() {
        List<ODMPersonne> liste = personneRepository.findPersonnesMigration(queryStructuresPerson, "20150219000000Z");
        assertThat(liste).isNotEmpty();
        assertThat(liste).size().isEqualTo(9);
    }

    @Test
    public void testStructureFindByOu() {
        Optional<ODMStructure> optStructure = etablissementRepository.findByCodeAll("GIP-RECIA");
        assertTrue(optStructure.isPresent());
        assertEquals(optStructure.map(ODMStructure::getNomCourt), Optional.of("GIP-RECIA"));
    }

}