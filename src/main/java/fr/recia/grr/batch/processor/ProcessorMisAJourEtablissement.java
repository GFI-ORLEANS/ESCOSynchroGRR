package fr.recia.grr.batch.processor;

import fr.recia.grr.batch.config.BatchSyncroException;
import fr.recia.grr.batch.synchronisation.entity.dao.GrrArea;
import fr.recia.grr.batch.synchronisation.entity.dao.GrrEtablissement;
import fr.recia.grr.batch.synchronisation.entity.dao.GrrEtablissementRegroupement;
import fr.recia.grr.batch.synchronisation.entity.dao.GrrSite;
import fr.recia.grr.batch.synchronisation.entity.ldap.ODMStructure;
import fr.recia.grr.batch.synchronisation.repository.dao.IEtablissementRegroupementRepositoryDAO;
import fr.recia.grr.batch.synchronisation.repository.dao.IEtablissementRepositoryDAO;
import fr.recia.grr.utils.RegroupementEtablissementLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProcessorMisAJourEtablissement implements ItemProcessor<ODMStructure, GrrEtablissement> {
    private static final Logger log = LoggerFactory.getLogger(ProcessorMisAJourEtablissement.class);

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Autowired
    private IEtablissementRepositoryDAO etablissementServiceDAO;

    @Autowired
    private IEtablissementRegroupementRepositoryDAO etablissementRegroupementRepositoryDAO;

    @Value("${createSiteOnCreateEtab:#{null}}")
    private Boolean createSiteOnCreateEtab;


    @Value("${typeDeDomaineParDefaut}")
    private String typeDeDomaineParDefaut;

    @Value("${nomDomainesParDefaut}")
    private String nomDomainesParDefaut;

    @Autowired
    private ResourceLoader resourceLoader;

    /*
     * ===============================================
     * synchronisation des données de ldap avec la base de donnée
     * ===============================================
     */

    private void validate(ODMStructure odmStructure) throws BatchSyncroException {
        if (odmStructure.getNomCourt()==null){
            throw new BatchSyncroException("odmStructure.getCode est null");
        }
        if (odmStructure.getNomLong()==null){
            throw new BatchSyncroException("odmStructure.getNomLong est null");
        }
        if (odmStructure.getSitename()==null){
            throw new BatchSyncroException("odmStructure.getSitename est null");
        }
    }

    @Override
    public GrrEtablissement process(ODMStructure odmStructure) throws IOException,BatchSyncroException {
        log.info("Debut Process pour l'établissement : ".concat(odmStructure.getCode()));
        validate(odmStructure);
        Optional<GrrEtablissement> etabDB=etablissementServiceDAO.findByCode(odmStructure.getCode());



        etabDB.ifPresentOrElse(
                value -> log.info("Mise a jour de l'établissement ID : ".concat(String.valueOf(value.getId()))),
                () -> log.info("Création de l'établissement")
        );
        GrrEtablissement grrEtablissement = updateEtablissement(etabDB.orElse(new GrrEtablissement()), odmStructure);
             /*
        Si le paramètre du batch createSiteOnCreateEtab est égal à « true », il faut créer les enregistrements « Site » et « Area »
             */
        if(createSiteOnCreateEtab && grrEtablissement.getGrr_j_etablissement_site().isEmpty()){
            log.info("createSiteOnCreateEtab actif && aucun site lié : Création d'un site ");

            GrrSite site = createSite(new GrrSite(), odmStructure);

            /*
    	Pour chaque domaine par défaut (Erreur : source de la référence non trouvée), mettre à jour l’ « area » correspondante dans la table grr_area
             */
            String[] domaines = nomDomainesParDefaut.split("\\$");
            for (String domaine : domaines) {
                site.getGrr_j_site_area().add(createArea(new GrrArea(),domaine));
                log.info("Ajout de l'area au site : ".concat(domaine));
            }
            ArrayList<GrrSite> grrSites = new ArrayList<>();
            grrSites.add(site);
            grrEtablissement.setGrr_j_etablissement_site(grrSites);
        }

        /*
        •	Retirer de la table grr_etablissement_regroupement les enregistrements concernant l’établissement.
        */
        log.info("Traitement des regroupements  Principal : ".concat(String.valueOf(grrEtablissement.getEtablissements_principal().size())).concat(" Secondaires : ").concat(String.valueOf(grrEtablissement.getEtablissements_secondaire().size())));

      //  grrEtablissement.removeAllEtablissements_principal();
       // grrEtablissement.removeAllEtablissements_secondaire();
        etablissementRegroupementRepositoryDAO.deleteByCode_etablissement_principal(grrEtablissement.getCode());
        etablissementRegroupementRepositoryDAO.deleteByCode_etablissement_secondaire(grrEtablissement.getCode());
        grrEtablissement.getEtablissements_secondaire().clear();
        grrEtablissement.getEtablissements_principal().clear();
        /*
            Si l’établissement possède un établissement principal (RG-11), créer le regroupement entre ces 2 établissements dans la table grr_etablissement_regroupement         */
        Optional<String> codePrincipal = RegroupementEtablissementLoader.loadPrincipal(resourceLoader, grrEtablissement.getCode());
        codePrincipal.ifPresent(s -> grrEtablissement.getEtablissements_principal().add(new GrrEtablissementRegroupement(s,grrEtablissement.getCode())));
        codePrincipal.ifPresent(s -> log.info("l’établissement possède un établissement principal, Creation d'un regroupement - Code etablissement principal : " .concat(s)));

        List<String> codeSecondare = RegroupementEtablissementLoader.loadSecondaires(resourceLoader, grrEtablissement.getCode());
        codeSecondare.forEach(s -> grrEtablissement.getEtablissements_secondaire().add(new GrrEtablissementRegroupement(grrEtablissement.getCode(),s)));
        codeSecondare.forEach(s -> log.info("l’établissement possède un établissement secondaire, Creation d'un regroupement - Code etablissement secondaire : " .concat(s)));

        log.info("Fin Traitement des regroupements  Principal : ".concat(String.valueOf(grrEtablissement.getEtablissements_principal().size())).concat(" Secondaires : ").concat(String.valueOf(grrEtablissement.getEtablissements_secondaire().size())));

        return grrEtablissement;
    }


    private GrrArea createArea(GrrArea grrArea, String domaine) {
        grrArea.setAreaName(domaine);
        grrArea.setAccess(typeDeDomaineParDefaut);
        grrArea.setIp_adr("");
        grrArea.setMorningstarts_area((short) 8);
        grrArea.setEveningends_area((short) 19);
        grrArea.setResolution_area(900);
        grrArea.setDuree_par_defaut_reservation_area(900);
        grrArea.setCalendar_default_values("y");
        grrArea.setDuree_max_resa_area(-1);
        grrArea.setEnable_periods("n");
        grrArea.setDisplay_days("yyyyyyy");
        grrArea.setEveningends_minutes_area((short) 0);
        grrArea.setId_type_par_defaut(-1);
        grrArea.setMax_booking((short) -1);
        grrArea.setOrder_display((short) 0);
        grrArea.setTwentyfourhour_format_area((short) 0);
        grrArea.setWeekstarts_area((short) 0);
        return grrArea;
    }

    private GrrSite createSite(GrrSite grrSite, ODMStructure odmStructure) {
        grrSite.setSitecode(odmStructure.getSitecode());
        grrSite.setSitename(sanitizeName(odmStructure.getSitename(),50));
        return grrSite;
    }

    private String sanitizeName(String name, int lenght){
        return name.substring(0,(name.length() < lenght)? name.length():lenght);
    }

    private GrrEtablissement updateEtablissement(GrrEtablissement obj, ODMStructure odmStructure) {
        obj.setCode(odmStructure.getCode());
        obj.setShortname(sanitizeName(odmStructure.getNomCourt(),30));
        obj.setName(sanitizeName(odmStructure.getSitename(),50));
        obj.setAdresse(odmStructure.getAdresse());
        obj.setVille(odmStructure.getVille());
        obj.setCodepostal(odmStructure.getCodePostal());
        return obj;
    }
}
