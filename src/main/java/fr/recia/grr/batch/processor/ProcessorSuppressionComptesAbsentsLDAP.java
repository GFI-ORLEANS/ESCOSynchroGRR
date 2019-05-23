package fr.recia.grr.batch.processor;


import fr.recia.grr.batch.synchronisation.entity.ldap.ODMPersonne;
import fr.recia.grr.batch.synchronisation.repository.ldap.IPersonneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProcessorSuppressionComptesAbsentsLDAP implements ItemProcessor<String,String> {

	private static final Logger log = LoggerFactory.getLogger(ProcessorSuppressionComptesAbsentsLDAP.class);

	/*
	 * ===============================================
	 * Propriétés de la classe
	 * ===============================================
	 */

	@Autowired
	private IPersonneRepository personneRepository;


	/*
	 * ===============================================
	 * Suppression de la personne non present dans ldap
	 * ===============================================
	 */



	@Override
	public String process(String loginPersonne) throws Exception {
		log.info("Debut du Processor SuppressionComptesAbsentsLDAP");
		log.info("Recherche si {} est present dans le ldap",loginPersonne);

		Optional<ODMPersonne> existPersonne = personneRepository.findByUid(loginPersonne);
		if (!existPersonne.isPresent()){
			return loginPersonne;
		}else {
			return null;
		}

	}

}