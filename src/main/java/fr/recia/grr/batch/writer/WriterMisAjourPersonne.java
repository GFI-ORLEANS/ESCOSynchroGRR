package fr.recia.grr.batch.writer;


import fr.recia.grr.batch.synchronisation.entity.dao.GrrUtilisateurs;
import fr.recia.grr.batch.synchronisation.repository.dao.IUtilisateursRepositoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WriterMisAjourPersonne implements ItemWriter<GrrUtilisateurs> {
	private static final Logger log = LoggerFactory.getLogger(WriterMisAjourPersonne.class);


	/*
	 * ===============================================
	 * Propriétés de la classe
	 * ===============================================
	 */

	@Autowired
	private IUtilisateursRepositoryDAO personnesServiceDAO;

	/*
	 * ===============================================
	 * modification dans la base recia
	 * ===============================================
	 */

	@Override
	public void write(List<? extends GrrUtilisateurs> personnes) throws Exception {
		log.info("Debut du Writer MisAjourPersonne");
 		for (GrrUtilisateurs pers : personnes) {
			log.info("Ecriture en base GrrUtilisateurs : ".concat(pers.getLogin()));
			personnesServiceDAO.saveAndFlush(pers);
		}
	}

}