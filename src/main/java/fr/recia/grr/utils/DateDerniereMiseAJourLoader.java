package fr.recia.grr.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Properties;

public class DateDerniereMiseAJourLoader {
    public static Resource load(ResourceLoader resourceLoader) {

         return resourceLoader.getResource("classpath:dateDerniereMiseAJour.properties");
    }

    private DateDerniereMiseAJourLoader() {
    }

    public static Optional<String> loadDate(ResourceLoader resourceLoader) throws IOException {
        Resource  props= load(resourceLoader);
        Properties load = PropertiesLoaderUtils.loadProperties(props);

        String uaiPrincipal = load.getProperty("date");

        return Optional.ofNullable(uaiPrincipal);
    }

    public static void write(ResourceLoader resourceLoader, String currentBatch) throws IOException, URISyntaxException {
        Resource load = load(resourceLoader);

        Properties props = new Properties();
        props.setProperty("date",currentBatch);

        props.store(new FileOutputStream( new File(load.getURI().getPath())), "Variable dateDerniereMiseAJour - La date qui sera insérée à la fin de l'exécution du script sera celle du début de l'exécution du script (avant l'interrogation LDAP).");

    }
}
