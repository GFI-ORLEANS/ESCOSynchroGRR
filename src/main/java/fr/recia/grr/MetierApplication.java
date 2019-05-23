package fr.recia.grr;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableBatchProcessing
@PropertySources({
        @PropertySource(value = "classpath:regroupementEtablissements.properties", ignoreResourceNotFound = false),
        @PropertySource(value = "classpath:dateDerniereMiseAJour.properties", ignoreResourceNotFound = false)
})
public class MetierApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetierApplication.class, args);
    }
}