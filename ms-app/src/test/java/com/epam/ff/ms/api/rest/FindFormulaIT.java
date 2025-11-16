package com.epam.ff.ms.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.Map;

import static java.net.http.HttpRequest.newBuilder;
import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FindFormulaIT {

    @LocalServerPort
    int port;

    @Autowired
    ObjectMapper jackson;

    HttpClient http = HttpClient.newHttpClient();

    @Test
    void get() throws IOException, InterruptedException {
        final var response = http.send(
            newBuilder(URI.create("http://localhost:%s/api/v1/formula/1".formatted(port)))
                .GET()
                .header("Accept", "application/json")
                .build(),
            ofString()
        );

        assertEquals(200, response.statusCode());
        assertEquals(// language=JSON
            "{\"id\":\"1\",\"name\":\"epam10x\",\"definition\":\"EPAM.N * 10\"}",
            response.body()
        );
    }

    @Test
    void notFound() throws IOException, InterruptedException {
        final var response = http.send(
            newBuilder(URI.create("http://localhost:%s/api/v1/formula/100".formatted(port)))
                .GET()
                .header("Accept", "application/json")
                .build(),
            ofString()
        );

        assertEquals(404, response.statusCode());
        final var expectedBody = jackson.readValue(
            "{" +
            "\"timestamp\":1763892465058," +
            "\"status\":404," +
            "\"error\":\"No formula with id: 100\"," +
            "\"path\":\"/api/v1/formula/100\"" +
            "}", Map.class
        );
        final var actualBody = jackson.readValue(response.body(), Map.class);
        expectedBody.remove("timestamp");
        actualBody.remove("timestamp");
        assertEquals(expectedBody, actualBody);
    }
}