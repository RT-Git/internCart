package com.example.searchAPI.services;

import com.example.searchAPI.entity.Product;
import java.util.List;

public interface SearchService {
    Product findByPName(String pName);
    Product findByPCategory(String category);
    Product findByPBrand(String pBrand);
    Product save(Product employee);
    List<Product> findAll();
    List<Product> findLike(String keyword);


    Product findOne(String productId);
    List<Product> findByPId(List<String> productIds);
    List<Product> getRecent(int size);
}
