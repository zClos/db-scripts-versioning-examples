package config.db;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@ComponentScan("config.db")
@PropertySource("classpath:db/migration/flyway.properties")
public class FlywayConf {

    @Autowired
    private Environment environment;
    @Autowired
    private DataSource dataSource;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations(environment.getProperty("flyway.directory"));
        flyway.setSchemas(environment.getProperty("flyway.schema"));
        flyway.setTable(environment.getProperty("flyway.table"));
        return flyway;
    }
}
