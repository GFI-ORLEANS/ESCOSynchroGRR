package fr.recia.grr.batch.reader;

import fr.recia.grr.batch.synchronisation.entity.ldap.ODMPersonne;
import fr.recia.grr.batch.synchronisation.repository.ldap.IPersonneRepository;
import fr.recia.grr.utils.DateBatch;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Iterator;


public class ReaderDAOAllPersonnes implements ItemReader<ODMPersonne> {


	/*
	 * ===============================================
	 * Propriétés de la classe
	 * ===============================================
	 */

	@Autowired
	private IPersonneRepository personneRepository;
	private Iterator<ODMPersonne> personnesData;
	private DateBatch dateBatch;

	public ReaderDAOAllPersonnes(DateBatch dateBatch) {

		this.dateBatch = dateBatch;
	}

	/*
	 * ===============================================
	 * intialiser la liste des utilisateurs dans la base de donné
	 * ===============================================
	 */
	@Value("${filter.ldap.people}")
	private String queryStructuresPerson;

	@PostConstruct
	private void initialize(){
		personnesData=personneRepository.findPersonnesMigration(queryStructuresPerson,dateBatch.getLastBatch()).iterator();
	}

	/*
	 * ===============================================
	 * lire chaque utilisateur et arreter dés que la fonction return null
	 * ===============================================
	 */

	@Override
	public ODMPersonne read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {

		ODMPersonne nextPersonne=null;
		if(personnesData.hasNext()){
			nextPersonne= personnesData.next();
		}
		return nextPersonne;
	}

}