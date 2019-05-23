package fr.recia.grr.batch.processor;

import fr.recia.grr.batch.config.BatchSyncroException;
import fr.recia.grr.batch.synchronisation.entity.dao.GrrEtablissement;
import fr.recia.grr.batch.synchronisation.entity.dao.GrrUtilisateurs;
import fr.recia.grr.batch.synchronisation.entity.ldap.ODMPersonne;
import fr.recia.grr.batch.synchronisation.repository.dao.IEtablissementRepositoryDAO;
import fr.recia.grr.batch.synchronisation.repository.dao.IUtilisateursRepositoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessorMisAJourPersonne implements ItemProcessor<ODMPersonne, GrrUtilisateurs> {
    private static final Logger log = LoggerFactory.getLogger(ProcessorMisAJourPersonne.class);

    /*
     * ===============================================
     * Propriétés de la classe
     * ===============================================
     */

    @Autowired
    private IUtilisateursRepositoryDAO utilisateursRepositoryDAO;

    @Autowired
    private IEtablissementRepositoryDAO etablissementServiceDAO;


    @Value("${statutCodeEtablissement}")
    private String statutCodeEtablissement;

    @Value("${statutUtilisateur}")
    private String statutUtilisateur;

    @Value("${statutAdministrateur}")
    private String statutAdministrateur;

    @Value("${etablissementPrincipal2}")
    private String etablissementPrincipal2;

    @Value("${statutAdminEtablissement}")
    private String statutAdminEtablissement;


    @Value("${etablissementPrincipal}")
    private String etablissementPrincipal;

    @Value("${emailParDefaut}")
    private String emailParDefaut;

    /*
     * ===============================================
     * synchronisation des données de ldap avec la base de donnée
     * ===============================================
     */
    private void validate(ODMPersonne odmPersonne) throws BatchSyncroException {
        if (odmPersonne.getUid()==null){
            throw new BatchSyncroException("odmPersonne.getCode est null");
        }
        if (odmPersonne.getEmail()==null && (emailParDefaut ==null || emailParDefaut.isEmpty()) ){
            throw new BatchSyncroException("odmPersonne.getEmail et emailParDefaut sont null");
        }
        if (odmPersonne.getNom()==null){
            throw new BatchSyncroException("odmPersonne.getNom est null");
        }
        if (odmPersonne.getPrenom()==null){
            throw new BatchSyncroException("odmPersonne.getPrenom est null");
        }

    }
    @Override
    public GrrUtilisateurs process(ODMPersonne odmPpersonne) throws IOException,BatchSyncroException {
        log.info("Debut Process pour l'utilisateur : ".concat(odmPpersonne.getUid()));
        validate(odmPpersonne);
        Optional<GrrUtilisateurs> user = utilisateursRepositoryDAO.findById(odmPpersonne.getUid());
        //RG-4	Etablissement principal de l’utilisateur
        Pattern p5 = Pattern.compile(etablissementPrincipal);
        for (String s:odmPpersonne.getIsMemberOf()) {
            Matcher matcher = p5.matcher(s);
            while (matcher.find()) {
                odmPpersonne.setDefaultEtablissement(matcher.group(1));
                log.info("Detection d'un établissement par defaut : ".concat(matcher.group(1)));
            }
        }
        if (odmPpersonne.getDefaultEtablissement()==null && odmPpersonne.getDefaultEtablissement2()!=null){
            log.info("ESCOUAIRattachement  est null : Utilisation de  ESCOUAICourant ");
            odmPpersonne.setDefaultEtablissement(odmPpersonne.getDefaultEtablissement2());
        }
        if (odmPpersonne.getDefaultEtablissement()==null && odmPpersonne.getDefaultEtablissement2()==null){
            log.info("ESCOUAIRattachement et ESCOUAICourant est null : Utilisation de la regex ".concat(etablissementPrincipal2));
            //RG-4	Etablissement principal de l’utilisateur
            Pattern p6 = Pattern.compile(etablissementPrincipal2);
            for (String s:odmPpersonne.getIsMemberOf()) {
                Matcher matcher = p6.matcher(s);
                while (matcher.find()) {
                    if (odmPpersonne.getDefaultEtablissement()==null){
                        odmPpersonne.setDefaultEtablissement(matcher.group(1));
                        log.info("Detection d'un établissement par defaut dans la regex : ".concat(matcher.group(1)));
                    }
                }
                if (odmPpersonne.getDefaultEtablissement()!=null){
                    break;
                }
            }
        }




        if (odmPpersonne.getDefaultEtablissement()==null){
            throw new BatchSyncroException("odmPersonne.getDefaultEtablissement est null");
        }
        user.ifPresentOrElse(
                value -> log.info("Mise a jour de l'utilisateur ID : ".concat(String.valueOf(value.getLogin()))),
                () -> log.info("Création de l'utilisateur")
        );
        GrrUtilisateurs grrUtilisateurs = updateUtilisateur(user.orElse(new GrrUtilisateurs(odmPpersonne.getUid())), odmPpersonne);

        if(grrUtilisateurs.getDefault_etablissement()==null){
            throw new BatchSyncroException("Le code etablissement detecté pour l'utilisateur ".concat(odmPpersonne.getUid()).concat(" est introuvable en base - Code : ").concat(odmPpersonne.getDefaultEtablissement()));
         }

        /*
        •	Si l’utilisateur possède le statut utilisateur (RG-5),
        les tables de jointure grr_j_user_etablissement et grr_j_useradmin_etablissement doivent être mise à jour de la manière suivante
         */
        Pattern p = Pattern.compile(statutAdministrateur);
        Pattern p2 = Pattern.compile(statutUtilisateur);
        Pattern p3 = Pattern.compile(statutCodeEtablissement);
        Pattern p4 = Pattern.compile(statutAdminEtablissement);

        boolean gotEtabl = false;
        boolean isAdmin = false;

        for (String s:odmPpersonne.getIsMemberOf()) {
            if (p.matcher(s).matches()) {
                isAdmin=true;
                log.info("Detection d'un statut admin :  admin:GRR:central ");
                grrUtilisateurs.setStatut("isAdmin");
            }
        }
        /*
            Retirer des tables grr_j_user_etablissement et grr_j_useradmin_etablissement les enregistrements concernant l’utilisateur.
           */
        if(!isAdmin){
            log.info("L'utilisateur ne possede pas le statut admin");
            grrUtilisateurs.getGrr_j_useradmin_etablissement().clear();
            grrUtilisateurs.getGrr_j_user_etablissement().clear();
        }

        for (String s:odmPpersonne.getIsMemberOf()) {
            boolean isAdminEtablissement = false;
            boolean isUtilisateur = false;
            String codeEtablissement = null;


            if (p4.matcher(s).matches()) {
                log.info("Detection d'un statut utilisateur :  admin:GRR:local ");
                isAdminEtablissement = true;
            }
            if (p2.matcher(s).matches()) {
                log.info("Detection d'un statut utilisateur : applications:GRR  ");
                isUtilisateur =true;
            }
            Matcher matcher = p3.matcher(s);
            while (matcher.find()) {
                codeEtablissement= matcher.group(2);
                log.info("Detection d'un code établissement : ".concat(matcher.group(2)));
            }


            if(codeEtablissement !=null){
                gotEtabl=true;

                Optional<GrrEtablissement> grrEtablissement = etablissementServiceDAO.findByCode(codeEtablissement);
                boolean finalIsAdminEtablissement = isAdminEtablissement;
                boolean finalIsUtilisateur = isUtilisateur;
                 String finalCodeEtablissement = codeEtablissement;
                grrEtablissement.ifPresentOrElse(value -> {

                // Recuperer letablissmeent principal - Effectuer également l’opération avec l’établissement principal associé à l’établissement en cours s’il existe (RG-7).
                    Set<GrrEtablissement> principal = new HashSet<>();
                    value.getEtablissements_principal().forEach(grrEtablissementRegroupement ->  etablissementServiceDAO.findByCode(grrEtablissementRegroupement.getCode_etablissement_principal()).ifPresent(principal::add));

                    principal.forEach(grrEtablissement1 -> log.info("Detection d'un établissement principal - Code: ".concat(grrEtablissement1.getCode())));

                     /*
                grr_j_useradmin_etablissement
                 */
                    if(finalIsAdminEtablissement){
                        log.info("Ajout a useradmin_etablissement ");
                        grrUtilisateurs.getGrr_j_useradmin_etablissement().add(value);
                        grrUtilisateurs.getGrr_j_useradmin_etablissement().addAll(principal);
                    }
                /*
                grr_j_user_etablissement
                */
                    if(finalIsUtilisateur){
                        log.info("Ajout a user_etablissement");
                        grrUtilisateurs.getGrr_j_user_etablissement().add(value);
                        grrUtilisateurs.getGrr_j_user_etablissement().addAll(principal);
                    }
                },() -> {
                    log.error("Le code etablissement detecté est introuvable en base - Code : ".concat(finalCodeEtablissement));
                });
            }
        }

        if(!gotEtabl) {
            log.error("Aucun code etablissement detecté Login : ".concat(odmPpersonne.getUid()));
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        grrUtilisateurs.getGrr_j_useradmin_etablissement().forEach(grrEtablissement -> stringBuilder.append(grrEtablissement.getCode().concat(" , ")));
        log.info(String.format("Listes des etablissement admin {%d%s", grrUtilisateurs.getGrr_j_useradmin_etablissement().size(), "} : ".concat(stringBuilder.toString())));
        StringBuilder stringBuilder2 = new StringBuilder();
        grrUtilisateurs.getGrr_j_user_etablissement().forEach(grrEtablissement -> stringBuilder2.append(grrEtablissement.getCode().concat(" , ")));
        log.info(String.format("Listes des etablissement  {%d%s", grrUtilisateurs.getGrr_j_user_etablissement().size(), "} : ".concat(stringBuilder2.toString())));

        log.info("Fin Process pour l'utilisateur : ".concat(odmPpersonne.getUid()));
        return grrUtilisateurs;
    }

    private GrrUtilisateurs updateUtilisateur(GrrUtilisateurs user, ODMPersonne odmPpersonne) {
        user.setNom(odmPpersonne.getNom());
        user.setPrenom(odmPpersonne.getPrenom());
        user.setEmail(odmPpersonne.getEmail() == null ? emailParDefaut : odmPpersonne.getEmail());
        user.setStatut("utilisateur");
        user.setEtat("actif");
        Optional<GrrEtablissement> grrEtablissement = etablissementServiceDAO.findByCode(odmPpersonne.getDefaultEtablissement());
        grrEtablissement.ifPresent(grrEtablissement1 -> user.setDefault_etablissement((grrEtablissement1.getId().shortValue())));
        user.setSource("ext");
        return user;
    }
}
