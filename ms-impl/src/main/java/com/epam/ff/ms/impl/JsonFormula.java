package com.epam.ff.ms.impl;

import com.epam.ff.ms.core.model.Formula;
import com.fasterxml.jackson.annotation.JsonProperty;

public record JsonFormula(
    @JsonProperty("id") String id,
    @JsonProperty("name") String name,
    @JsonProperty("definition") String definition
) implements Formula {
    public JsonFormula(final Formula formula) {
        this(formula.id(), formula.name(), formula.definition());
    }
}
