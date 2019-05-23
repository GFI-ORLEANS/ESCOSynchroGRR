package fr.recia.grr.utils;

import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.*;

public class RegroupementEtablissementLoader {
    public static Properties load(ResourceLoader resourceLoader) throws IOException {
         return PropertiesLoaderUtils.loadProperties(resourceLoader.getResource("classpath:regroupementEtablissements.properties"));
    }

    private RegroupementEtablissementLoader() {
    }

    public static Optional<String> loadPrincipal(ResourceLoader resourceLoader, String codeSecondaire) throws IOException {
        Properties load = load(resourceLoader);
        String uaiPrincipal = load.getProperty(codeSecondaire+".principal");
        return Optional.ofNullable(uaiPrincipal);
    }
    public static List<String> loadSecondaires(ResourceLoader resourceLoader, String codePrincipal) throws IOException {
        ArrayList<String> secondaires = new ArrayList<>();
        Properties load = load(resourceLoader);
        for (Map.Entry<Object, Object> entry : load.entrySet()) {
            if(codePrincipal.equals(entry.getValue())){
                secondaires.add(entry.getKey().toString().replace(".principal",""));
            }
        }
        return secondaires;
    }
}
