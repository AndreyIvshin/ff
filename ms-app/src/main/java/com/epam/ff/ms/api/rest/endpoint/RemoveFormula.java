package com.epam.ff.ms.api.rest.endpoint;

import com.epam.ff.ms.core.service.RemoveModel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RemoveFormula {
    private final RemoveModel<String> service;

    @DeleteMapping("api/v1/formula/{id}")
    public ResponseEntity<Void> delete(final @PathVariable("id") String id) throws Exception {
        service.removeModel(id);
        return ResponseEntity.noContent().build();
    }
}
