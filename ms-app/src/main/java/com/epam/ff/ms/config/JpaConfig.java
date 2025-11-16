package com.epam.ff.ms.config;

import com.epam.ff.ms.impl.jpa.JpaFormula;
import com.epam.ff.ms.impl.jpa.JpaRepositoryFormulaStorage;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicLong;

@Profile("jpa")
@Configuration
@ComponentScan(basePackages = "com.epam.ff.ms.impl.jpa")
public class JpaConfig {
    @Bean
    @Primary
    public JpaRepositoryFormulaStorage jpaRepositoryFormulaStorage(final JpaRepository<JpaFormula, String> repository) {
        final var counter = new AtomicLong(3);
        return new JpaRepositoryFormulaStorage(repository, () -> String.valueOf(counter.incrementAndGet()));
    }

    public static DataSource h2() {
        final var ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }
}
