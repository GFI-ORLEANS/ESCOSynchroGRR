package fr.recia.grr.batch.reader;

import fr.recia.grr.batch.synchronisation.entity.ldap.ODMStructure;
import fr.recia.grr.batch.synchronisation.repository.ldap.IEtablissementRepository;
import fr.recia.grr.utils.DateBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Iterator;


public class ReaderDAOAllEtablissement implements ItemReader<ODMStructure> {
	private static final Logger log = LoggerFactory.getLogger(ReaderDAOAllEtablissement.class);

	/*
	 * ===============================================
	 * Propriétés de la classe
	 * ===============================================
	 */

	private Iterator<ODMStructure> etablissementsData;

	@Autowired
	private IEtablissementRepository etablissementRepository;

	private DateBatch dateBatch;


	@Value("${filter.ldap.structures}")
	private String queryStructures;

	public ReaderDAOAllEtablissement(DateBatch dateBatch) {
		this.dateBatch = dateBatch;
	}

	/*
	 * ===============================================
	 * intialiser la liste des etablisements dans la base de donné
	 * ===============================================
	 */
	@PostConstruct
	private void initialize(){
		etablissementsData=etablissementRepository.findRecent(queryStructures,dateBatch.getLastBatch()).iterator();
	}

	/*
	 * ===============================================
	 * lire chaque etablissement et arreter dés que la fonction return null
	 * ===============================================
	 */

	@Override
	public ODMStructure read()  {

		ODMStructure nextEtablissement=null;
		if(etablissementsData.hasNext()){
			nextEtablissement= etablissementsData.next();
		}
		return nextEtablissement;
	}

}