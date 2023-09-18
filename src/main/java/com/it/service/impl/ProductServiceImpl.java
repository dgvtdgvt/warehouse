package com.it.service.impl;

import com.it.dao.ProductMapper;
import com.it.pojo.Product;
import com.it.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> selectProduct() {
        return productMapper.selectProduct();
    }

    @Override
    public Product findProductById(Product product) {
        return productMapper.findProductById(product);
    }

    @Override
    public Integer editProduct(Product product) {
        return productMapper.editProduct(product);
    }

    @Override
    public Integer addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    @Override
    public List<Product> productSearch(Product product) {
        return productMapper.productSearch(product);
    }
}
