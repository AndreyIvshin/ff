package com.epam.ff.ms.api.rest.endpoint;

import com.epam.ff.ms.core.model.Formula;
import com.epam.ff.ms.core.service.FindModel;
import com.epam.ff.ms.impl.JsonFormula;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FindFormula {
    private final FindModel<String, Formula> service;

    @GetMapping("api/v1/formula/{id}")
    public ResponseEntity<Formula> get(final @PathVariable("id") String id) throws Exception {
        final var formula = service.findModel(id);
        return ResponseEntity.ok(new JsonFormula(formula));
    }
}
