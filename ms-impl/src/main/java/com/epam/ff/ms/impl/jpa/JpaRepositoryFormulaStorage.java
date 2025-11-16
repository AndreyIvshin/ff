package com.epam.ff.ms.impl.jpa;

import com.epam.ff.ms.core.exception.ModelNotFoundException;
import com.epam.ff.ms.core.model.Formula;
import com.epam.ff.ms.core.service.FindModel;
import com.epam.ff.ms.core.service.RemoveModel;
import com.epam.ff.ms.core.service.SaveModel;
import com.epam.ff.ms.impl.ImmutableFormula;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Supplier;

@AllArgsConstructor
public class JpaRepositoryFormulaStorage implements SaveModel<Formula>, FindModel<String, Formula>, RemoveModel<String> {
    private final JpaRepository<JpaFormula, String> repository;
    private final Supplier<String> idGenerator;

    @Override
    public Formula saveModel(Formula formula) throws Exception {
        var id = formula.id();
        if (id == null) {
            id = idGenerator.get();
            formula = ImmutableFormula.builder().from(formula).id(id).build();
        }
        final var jpaFormula = repository.save(new JpaFormula(formula));
        return toImmutable(jpaFormula);
    }

    @Override
    public Formula findModel(final String id) throws ModelNotFoundException, Exception {
        return repository.findById(id)
            .map(this::toImmutable)
            .orElseThrow(() -> new ModelNotFoundException("No formula with id: " + id));
    }

    @Override
    public void removeModel(final String id) throws ModelNotFoundException, Exception {
        final var formula = repository.findById(id)
            .orElseThrow(() -> new ModelNotFoundException("No formula with id: " + id));
        repository.deleteById(formula.id());
    }

    private ImmutableFormula toImmutable(final JpaFormula jpaFormula) {
        return ImmutableFormula.builder().from(jpaFormula).build();
    }
}
