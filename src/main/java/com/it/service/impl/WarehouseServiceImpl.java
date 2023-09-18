package com.it.service.impl;

import com.it.dao.WarehouseMapper;
import com.it.pojo.Warehouse;
import com.it.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public List<Warehouse> findWarehouse() {
        return warehouseMapper.findWarehouse();
    }

    @Override
    public Warehouse findWarehouseByproductIdAndName(Warehouse warehouse) {
        return warehouseMapper.findWarehouseByproductIdAndName(warehouse);
    }

    @Override
    public Integer updateProduct(Warehouse warehouse) {
        return warehouseMapper.updateProduct(warehouse);
    }

    @Override
    public Integer addWarehouse(Warehouse warehouse) {
        return warehouseMapper.addWarehouse(warehouse);
    }

    @Override
    public Warehouse findWarehouseById(Warehouse warehouse) {
        return warehouseMapper.findWarehouseById(warehouse);
    }

    @Override
    public List<Warehouse> warehouseSearch(Warehouse warehouse) {
        return warehouseMapper.warehouseSearch(warehouse);
    }
}
