package com.epam.ff.ms.config;

import com.epam.ff.ms.impl.ImmutableFormula;
import com.epam.ff.ms.impl.InMemoryFormulaStorage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper jackson() {
        return new ObjectMapper();
    }

    @Bean
    public InMemoryFormulaStorage inMemoryFormulaStorage() {
        final var map = Map.of(
            "-1", ImmutableFormula.builder().id("-1").name("broken").definition("ABC / 0").build(),
            "1", ImmutableFormula.builder().id("1").name("epam10x").definition("EPAM.N * 10").build(),
            "2", ImmutableFormula.builder().id("2").name("lseg5x").definition("LSEG.L * 5").build(),
            "3", ImmutableFormula.builder().id("3").name("google2x").definition("GOOG.L * 2").build()
        );
        final var counter = new AtomicLong(3);
        return new InMemoryFormulaStorage(new HashMap<>(map), () -> String.valueOf(counter.incrementAndGet()));
    }

    @Bean
    @Profile("!jpa")
    public DataSource dataSource() {
        return JpaConfig.h2();
    }
}
