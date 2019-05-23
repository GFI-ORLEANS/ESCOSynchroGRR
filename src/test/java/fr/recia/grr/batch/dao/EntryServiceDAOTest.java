package fr.recia.grr.batch.dao;


import fr.recia.grr.batch.synchronisation.entity.dao.GrrEntry;
import fr.recia.grr.batch.synchronisation.repository.dao.IEntryRepositoryDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntryServiceDAOTest {
    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */
    @Autowired
    private IEntryRepositoryDAO entryServiceDAO;

    /*
     * ===============================================
     * Methodes de test
     * ===============================================
     */
    @Test
    @Sql("/multiEtablissement_init.sql")
    public void testFindReservationancienne60Jour(){
        LocalDateTime dateActuel= LocalDateTime.now();
        LocalDateTime dateRequis=dateActuel.minus(60, ChronoUnit.DAYS);

        Collection<GrrEntry> pers=entryServiceDAO.findReservationAcienneNjour(dateRequis);
        Assert.assertEquals(pers.size(),2);
    }

}
