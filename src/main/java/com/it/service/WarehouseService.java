package com.it.service;

import com.it.pojo.Warehouse;

import java.util.List;

public interface WarehouseService {
    public List<Warehouse> findWarehouse();

    public Warehouse findWarehouseByproductIdAndName(Warehouse warehouse);

    public Integer updateProduct(Warehouse warehouse);

    public Integer addWarehouse(Warehouse warehouse);

    public Warehouse findWarehouseById(Warehouse warehouse);

    public List<Warehouse> warehouseSearch(Warehouse warehouse);
}
