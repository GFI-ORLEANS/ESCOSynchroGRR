package fr.recia.grr.batch.dao;


import fr.recia.grr.batch.synchronisation.entity.dao.GrrEtablissement;
import fr.recia.grr.batch.synchronisation.repository.dao.IEtablissementRepositoryDAO;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EtablissementServiceDAOTest {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */
    @Autowired
    private IEtablissementRepositoryDAO etablissementServiceDAO;

    /*
     * ===============================================
     * Methodes de test
     * ===============================================
     */

    @Test
    @Sql("/multiEtablissement_init.sql")
    public void test1GetAllEtablissements(){
        List<GrrEtablissement> etablissements=etablissementServiceDAO.findAll();
        Assert.assertNotNull(etablissements);
        Assert.assertEquals(etablissements.size(),3);
    }

    @Test
    @Sql("/multiEtablissement_init.sql")
    public void test2GetetablissementByCodeOK(){
        Optional<GrrEtablissement> etab=etablissementServiceDAO.findByCode("0450822X");
        Assert.assertNotNull(etab);
    }

    @Test
    @Sql("/multiEtablissement_init.sql")
    public void test3GetetablissementByCodeNotKO(){
        Optional<GrrEtablissement> etab=etablissementServiceDAO.findByCode("0180007A");
         Assert.assertNotEquals(Optional.empty(),etab);

    }

    @Test
    @Sql("/multiEtablissement_init.sql")
    public void test4UpdateEtablissement(){
        GrrEtablissement newetab=new GrrEtablissement(369,"0450z412","iut orléans","45100 Orléans","45100","Orléans","France");
        etablissementServiceDAO.saveAndFlush(newetab);
        Assert.assertNotNull(etablissementServiceDAO.findByCode(newetab.getCode()));
    }

}
