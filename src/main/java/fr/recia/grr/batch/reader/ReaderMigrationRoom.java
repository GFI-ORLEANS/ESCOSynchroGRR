package fr.recia.grr.batch.reader;

import fr.recia.grr.batch.migration.entity.GrrSite;
import fr.recia.grr.batch.migration.repository.ISiteMigrationRepositoryDAO;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Iterator;

public class ReaderMigrationRoom  implements ItemReader<GrrSite> {

    @Autowired
    private ISiteMigrationRepositoryDAO service;

    private Iterator<GrrSite> areaIterator;

    @PostConstruct
    private void initialize(){
        areaIterator=service.findAll().iterator();
    }


    @Override
    public GrrSite read()  {

        GrrSite nextArea=null;
        if(areaIterator.hasNext()){
            nextArea= areaIterator.next();
        }
        return nextArea;
    }
}
