package com.zemosolabs.{{cookiecutter.package_name}};

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.net.URL;

@TestConfiguration
@EnableAutoConfiguration
public class TodoTestApplication {

    @Bean
    public DataSource configureDataSource(
            @Value("${spring.datasource.username}") final String username,
            @Value("${spring.datasource.password}") final String password
    ) {
        URL createScript = this.getClass().getClassLoader().getResource("test-db-creation.sql");
        var postgresContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:9.6.12"))
                .withUsername(username)
                .withPassword(password);
        if (createScript != null) {
            postgresContainer.withInitScript("test-db-creation.sql");
        }
        postgresContainer.start();
        var dataSource = DataSourceBuilder.create()
                .url(postgresContainer.getJdbcUrl())
                .username(postgresContainer.getUsername())
                .password(postgresContainer.getPassword())
                .driverClassName("org.postgresql.Driver")
                .build();
        return dataSource;
    }
}