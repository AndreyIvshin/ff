package com.epam.ff.ms.core.service;

import com.epam.ff.ms.core.exception.ModelNotFoundException;

@FunctionalInterface
public interface FindModel<ID, MODEL> {
    MODEL findModel(ID id) throws ModelNotFoundException, Exception;
}
