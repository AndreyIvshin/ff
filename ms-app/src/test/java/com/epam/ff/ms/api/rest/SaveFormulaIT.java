package com.epam.ff.ms.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Map;

import static java.net.http.HttpRequest.newBuilder;
import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SaveFormulaIT {

    @LocalServerPort
    int port;

    @Autowired
    ObjectMapper jackson;

    HttpClient http = HttpClient.newHttpClient();

    @Test
    void put201() throws IOException, InterruptedException {
        final var response = http.send(
            newBuilder(URI.create("http://localhost:%s/api/v1/formula".formatted(port)))
                .PUT(BodyPublishers.ofString("{\"name\":\"epam20x\",\"definition\":\"EPAM.N * 20\"}"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build(),
            ofString()
        );

        assertEquals(201, response.statusCode());
        assertEquals(// language=JSON
            "{\"id\":\"4\",\"name\":\"epam20x\",\"definition\":\"EPAM.N * 20\"}",
            response.body()
        );
    }

    @Test
    void put200() throws IOException, InterruptedException {
        final var response = http.send(
            newBuilder(URI.create("http://localhost:%s/api/v1/formula".formatted(port)))
                .PUT(BodyPublishers.ofString("{\"id\":\"4\",\"name\":\"epam30x\",\"definition\":\"EPAM.N * 30\"}"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build(),
            ofString()
        );

        assertEquals(200, response.statusCode());
        assertEquals(// language=JSON
            "{\"id\":\"4\",\"name\":\"epam30x\",\"definition\":\"EPAM.N * 30\"}",
            response.body()
        );
    }

    @Test
    void putInvalidJson() throws IOException, InterruptedException {
        final var response = http.send(
            newBuilder(URI.create("http://localhost:%s/api/v1/formula".formatted(port)))
                .PUT(BodyPublishers.ofString("{\"id\":\"4\",\"title\":\"epam30x\",\"definition\":\"EPAM.N * 30\"}"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build(),
            ofString()
        );

        assertEquals(400, response.statusCode());
        final var expectedBody = jackson.readValue(
            "{" +
            "\"timestamp\":1763892465058," +
            "\"status\":400," +
            "\"error\":\"Unrecognized field: [title]\"," +
            "\"path\":\"/api/v1/formula\"" +
            "}", Map.class
        );
        final var actualBody = jackson.readValue(response.body(), Map.class);
        expectedBody.remove("timestamp");
        actualBody.remove("timestamp");
        assertEquals(expectedBody, actualBody);
    }
}
