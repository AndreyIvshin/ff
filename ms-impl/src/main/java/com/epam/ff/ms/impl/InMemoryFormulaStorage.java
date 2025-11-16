package com.epam.ff.ms.impl;

import com.epam.ff.ms.core.exception.ModelNotFoundException;
import com.epam.ff.ms.core.model.Formula;
import com.epam.ff.ms.core.service.FindModel;
import com.epam.ff.ms.core.service.RemoveModel;
import com.epam.ff.ms.core.service.SaveModel;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.function.Supplier;

@AllArgsConstructor
public class InMemoryFormulaStorage implements SaveModel<Formula>, FindModel<String, Formula>, RemoveModel<String> {
    private final HashMap<String, Formula> map;
    private final Supplier<String> idGenerator;

    @Override
    public Formula saveModel(Formula formula) {
        var id = formula.id();
        if (id == null) {
            id = idGenerator.get();
            formula = ImmutableFormula.builder().from(formula).id(id).build();
        }
        map.put(id, formula);
        return formula;
    }

    @Override
    public Formula findModel(final String id) throws ModelNotFoundException {
        final var formula = map.get(id);
        if (formula == null) throw new ModelNotFoundException("No formula with id: " + id);
        return formula;
    }

    @Override
    public void removeModel(final String id) throws ModelNotFoundException {
        final var formula = map.get(id);
        if (formula == null) throw new ModelNotFoundException("No formula with id: " + id);
        map.remove(id);
    }
}
