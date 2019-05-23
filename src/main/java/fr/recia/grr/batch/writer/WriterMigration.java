package fr.recia.grr.batch.writer;

import fr.recia.grr.batch.migration.entity.GrrSite;
import fr.recia.grr.batch.migration.mapper.GrrSiteMapper;
import fr.recia.grr.batch.synchronisation.entity.dao.GrrEtablissement;
import fr.recia.grr.batch.synchronisation.repository.dao.IEtablissementRepositoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(transactionManager = "transactionManager")
public class WriterMigration   implements ItemWriter<GrrSite> {

    private static final Logger log = LoggerFactory.getLogger(WriterMigration.class);

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;

    @Autowired
    private IEtablissementRepositoryDAO etablissementRepositoryDAO;

    @Value("${migration.cible.codeetablissement}")
    private String codeetablissement;


    @Override
    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public void write(List<? extends GrrSite> list)  {
        entityManager.joinTransaction();

        Optional<GrrEtablissement> etablissementbycode = etablissementRepositoryDAO.findByCode(codeetablissement);
        etablissementbycode.ifPresent(grrEtablissement -> {

        int size = grrEtablissement.getGrr_j_etablissement_site().size();

        list.forEach(grrSite -> {
            grrSite.setId(null);
            grrSite.getGrr_j_site_area().forEach(grrArea -> {
                grrArea.setId(null);
                grrArea.getGrrRooms().forEach(grrRoom -> {
                    grrRoom.setId(null);
                    grrRoom.setArea_id(null);
                });
                grrArea.getGrrOverloads().forEach(grrOverload -> {
                    grrOverload.setId(null);
                    grrOverload.setId_area(null);
                });
                grrArea.getGrr_j_area_typeArea().forEach(grrTypeArea -> grrTypeArea.setId(null));
                grrArea.getGrrAreaPeriodes().forEach(grrAreaPeriode -> grrAreaPeriode.setId(null));
            });

            fr.recia.grr.batch.synchronisation.entity.dao.GrrSite areaToGrrSiteMulti = GrrSiteMapper.INSTANCE.areaToGrrSiteMulti( grrSite );

            areaToGrrSiteMulti.getGrr_j_site_area().forEach(grrArea -> grrArea.getGrrRooms().forEach(grrRoom -> grrRoom.setArea_id(grrArea)));
            areaToGrrSiteMulti.getGrr_j_site_area().forEach(grrArea -> grrArea.getGrrOverloads().forEach(grrOverload -> grrOverload.setId_area(grrArea)));
            areaToGrrSiteMulti.getGrr_j_site_area().forEach(grrArea -> grrArea.getGrrAreaPeriodes().forEach(grrAreaPeriode -> grrAreaPeriode.setId_area(grrArea)));

            areaToGrrSiteMulti.getGrr_j_site_area().forEach(grrArea -> grrArea.getGrr_j_area_typeArea().forEach(grrTypeArea -> grrTypeArea.getGrr_j_type_area_etablissement().add(grrEtablissement)));

            grrEtablissement.getGrr_j_etablissement_site().add(areaToGrrSiteMulti);
            log.info("Ajout du site : "+grrSite.getSitecode()+" dans l'établissement :"+codeetablissement);
            grrEtablissement.setAdresse("Test adresse");
            GrrEtablissement grrEtablissement1 = etablissementRepositoryDAO.saveAndFlush(grrEtablissement);
            });


        });


    }
}
