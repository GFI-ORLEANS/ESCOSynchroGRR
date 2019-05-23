package fr.recia.grr.batch.dao;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrUtilisateurs;
import fr.recia.grr.batch.synchronisation.repository.dao.IUtilisateursRepositoryDAO;
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
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilisateursServiceDAOTest {
    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */
    @Autowired
    private IUtilisateursRepositoryDAO utilisateursServiceDAO;

    /*
     * ===============================================
     * Methodes de test
     * ===============================================
     */

    @Test
    @Sql("/multiEtablissement_init.sql")
    public void testGetAllUsers(){
        List<GrrUtilisateurs>  users=utilisateursServiceDAO.findAll();
        Assert.assertNotNull(users);
        Assert.assertEquals(users.size(),4);

    }

    @Test
    @Sql("/multiEtablissement_init.sql")
    public void testGetUserByLoginKO(){
        Optional<GrrUtilisateurs> user=utilisateursServiceDAO.findByLogin("F080d5pe");
//        Assert.assertNull(user.get());
        Assert.assertEquals(Optional.empty(),user);

    }

    @Test
    @Sql("/multiEtablissement_init.sql")
    public void testFindPersonneNonConnecter60Jour(){
        LocalDateTime dateActuel= LocalDateTime.now();
        LocalDateTime dateRequis=dateActuel.minus(60, ChronoUnit.DAYS);
        Collection<GrrUtilisateurs> pers=utilisateursServiceDAO.findUserConnecterNJour(dateRequis);
        Assert.assertEquals(pers.size(),1);
    }

}
