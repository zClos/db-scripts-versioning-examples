package config.db;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@ComponentScan("config.db")
@PropertySource("classpath:db/liquibase/liquibase.properties")
public class LiquibaseConf {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private Environment environment;

    @Bean
    public SpringLiquibase springLiquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog(environment.getProperty("liquibase.changeLogFile"));
        springLiquibase.setContexts("test, production");
        return springLiquibase;
    }
}
