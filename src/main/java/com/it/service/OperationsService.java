package com.it.service;

import com.it.pojo.Operations;
import com.it.pojo.Warehouse;

import java.util.List;

public interface OperationsService {
    public List<Operations> findOperations();

    public Integer addOperations(Operations operations);

    public List<Operations> operationsSearch(Operations operations);
}
