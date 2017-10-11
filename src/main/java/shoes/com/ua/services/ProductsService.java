package shoes.com.ua.services;

import shoes.com.ua.entity.Products;

import java.util.List;

public interface ProductsService {

    void save(Products products);

    Products findOne(long id);

    List<Products> findAll();

    void delete(Products products);

    void delete(long id);

}
