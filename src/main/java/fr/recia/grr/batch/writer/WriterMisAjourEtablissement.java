package fr.recia.grr.batch.writer;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrEtablissement;
import fr.recia.grr.batch.synchronisation.repository.dao.IEtablissementRepositoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WriterMisAjourEtablissement implements ItemWriter<GrrEtablissement> {
	private static final Logger log = LoggerFactory.getLogger(WriterMisAjourEtablissement.class);

	/*
	 * ===============================================
	 * Propriétés de la classe
	 * ===============================================
	 */

	@Autowired
	private IEtablissementRepositoryDAO etablissementRepositoryDAO;

	/*
	 * ===============================================
	 * modification dans la base recia
	 * ===============================================
	 */


	@Override
	public void write(List<? extends GrrEtablissement> list) throws Exception {
		log.info("Debut du Writer MisAjourEtablissement");

		for (GrrEtablissement grrEtablissement : list) {
			log.info("Ecriture en base GrrEtablissement : ".concat(grrEtablissement.getCode()));
			etablissementRepositoryDAO.saveAndFlush(grrEtablissement);
		}
	}
}