package fr.recia.grr.batch.migration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Profile("test")
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "migrationEntityManagerFactory", basePackages = {"fr.recia.grr.batch.migration.repository"})
public class ConfigurationMigrationTest {

	@Bean(name= "migrationDataSourceProperties")
	@ConfigurationProperties("migration.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "dataSourceMigration")
	@ConfigurationProperties("migration.datasource.configuration")
	public DataSource dataSource(@Qualifier("migrationDataSourceProperties") DataSourceProperties properties) {
		
		return new EmbeddedDatabaseBuilder().
	            setType(EmbeddedDatabaseType.H2).
	            setName("DB2").
	            addScript("migration_ddl.sql").
	            addScript("migration_init.sql").
	            build();
	}	
	

	@Bean(name = "migrationEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(@Qualifier("dataSourceMigration") DataSource dataSource) {
         LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
         entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
         entityManagerFactoryBean.setDataSource(dataSource);
         entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
         entityManagerFactoryBean.setPackagesToScan("fr.recia.grr.batch.migration.entity");
         return entityManagerFactoryBean;
     }	
	
	@Bean(name = "migrationTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("migrationEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}	
	
	private HibernateJpaVendorAdapter vendorAdaptor() {
         HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
         vendorAdapter.setShowSql(true);
         return vendorAdapter;
    }
	 
}
