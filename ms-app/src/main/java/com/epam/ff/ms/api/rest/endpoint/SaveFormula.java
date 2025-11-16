package com.epam.ff.ms.api.rest.endpoint;

import com.epam.ff.ms.core.model.Formula;
import com.epam.ff.ms.core.service.SaveModel;
import com.epam.ff.ms.impl.JsonFormula;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SaveFormula {
    private final SaveModel<Formula> service;

    @PutMapping("api/v1/formula")
    public ResponseEntity<Formula> put(final @RequestBody JsonFormula jsonFormula) throws Exception {
        final var formula = service.saveModel(jsonFormula);
        return ResponseEntity
            .status(jsonFormula.id() == null ? 201 : 200)
            .body(new JsonFormula(formula));
    }
}
