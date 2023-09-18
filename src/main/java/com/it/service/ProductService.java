package com.it.service;

import com.it.pojo.Product;

import java.util.List;

public interface ProductService {
    public List<Product> selectProduct();

    public Product findProductById(Product product);

    public Integer editProduct(Product product);

    public Integer addProduct(Product product);

    public List<Product> productSearch(Product product);
}
