package com.epam.ff.ms.impl.jpa;

import com.epam.ff.ms.core.model.Formula;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "formula")
public class JpaFormula implements Formula {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "definition")
    private String definition;

    public JpaFormula(final Formula formula) {
        this.id = formula.id();
        this.name = formula.name();
        this.definition = formula.definition();
    }

    @Override
    public String id() {
        return getId();
    }

    @Override
    public String name() {
        return getName();
    }

    @Override
    public String definition() {
        return getDefinition();
    }
}
