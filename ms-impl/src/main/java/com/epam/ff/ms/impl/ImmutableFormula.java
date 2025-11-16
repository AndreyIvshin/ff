package com.epam.ff.ms.impl;

import com.epam.ff.ms.core.model.Formula;

public record ImmutableFormula(String id, String name, String definition) implements Formula {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private String definition;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder definition(String definition) {
            this.definition = definition;
            return this;
        }

        public Builder from(final Formula formula) {
            this.id = formula.id();
            this.name = formula.name();
            this.definition = formula.definition();
            return this;
        }

        public ImmutableFormula build() {
            return new ImmutableFormula(this.id, this.name, this.definition);
        }
    }
}
