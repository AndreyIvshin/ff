package com.epam.ff.ms.core.service;

import com.epam.ff.ms.core.exception.ModelNotFoundException;

@FunctionalInterface
public interface RemoveModel<ID> {
    void removeModel(ID id) throws ModelNotFoundException, Exception;
}
