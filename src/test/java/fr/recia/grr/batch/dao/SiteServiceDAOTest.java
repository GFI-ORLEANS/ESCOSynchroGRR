package fr.recia.grr.batch.dao;


import fr.recia.grr.batch.synchronisation.entity.dao.GrrSite;
import fr.recia.grr.batch.synchronisation.repository.dao.ISiteRepositoryDAO;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SiteServiceDAOTest {

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */
    @Autowired
    private ISiteRepositoryDAO siteServiceDAO;


    /*
     * ===============================================
     * Methodes de test
     * ===============================================
     */


    @Test
    @Sql("/multiEtablissement_init.sql")
    public void test1UpdateSite(){
        GrrSite newetab=new GrrSite(369,"0450z412","iut orléans","45100 Orléans","","","45100","Orléans","France","063525698","065898785");
        siteServiceDAO.saveAndFlush(newetab);
        Assert.assertNotNull(siteServiceDAO.findAllBySitecode(newetab.getSitecode()));
    }

    @Test
    @Sql("/multiEtablissement_init.sql")
    public void test2GetsiteByCodeOK(){
        GrrSite etab=siteServiceDAO.findAllBySitecode("0370102A");
        Assert.assertNotNull(etab);
    }


    @Test
    @Sql("/multiEtablissement_init.sql")
    public void test3GetsiteByCodeNotKO(){
        GrrSite etab=siteServiceDAO.findAllBySitecode("04568987");
        Assert.assertNull(etab);

    }

    @Test
    @Sql("/multiEtablissement_init.sql")
    public void test4GetAllSits(){
        List<GrrSite> sits=siteServiceDAO.findAll();
        Assert.assertNotNull(sits);
        Assert.assertEquals(sits.size(),2);
    }

}
