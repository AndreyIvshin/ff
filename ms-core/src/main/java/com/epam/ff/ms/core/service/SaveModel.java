package com.epam.ff.ms.core.service;

@FunctionalInterface
public interface SaveModel<MODEL> {
    MODEL saveModel(MODEL model) throws Exception;
}
