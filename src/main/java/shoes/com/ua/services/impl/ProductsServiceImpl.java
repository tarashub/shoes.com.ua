package shoes.com.ua.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import shoes.com.ua.dao.ProductsDAO;
import shoes.com.ua.entity.Products;
import shoes.com.ua.services.ProductsService;

import java.util.List;

public class ProductsServiceImpl implements ProductsService {

    @Autowired
    ProductsDAO productsDAO;

    @Override
    public void save(Products products) {

        productsDAO.save(products);
    }

    @Override
    public Products findOne(long id) {
        return productsDAO.findOne(id);
    }

    @Override
    public List<Products> findAll() {
        return productsDAO.findAll();
    }

    @Override
    public void delete(Products products) {
        productsDAO.delete(products);
    }

    @Override
    public void delete(long id) {
        productsDAO.delete(id);
    }

}
