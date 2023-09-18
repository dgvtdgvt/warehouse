package com.it.service.impl;

import com.it.dao.OperationsMapper;
import com.it.pojo.Operations;
import com.it.pojo.Warehouse;
import com.it.service.OperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationsServiceImpl implements OperationsService {
    @Autowired
    private OperationsMapper operationsMapper;

    @Override
    public List<Operations> findOperations() {
        return operationsMapper.findOperations();
    }

    @Override
    public Integer addOperations(Operations operations) {
        return operationsMapper.addOperations(operations);
    }

    @Override
    public List<Operations> operationsSearch(Operations operations) {
        return operationsMapper.operationsSearch(operations);
    }
}
