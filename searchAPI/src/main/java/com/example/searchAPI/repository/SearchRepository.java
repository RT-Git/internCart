package com.example.searchAPI.repository;

import com.example.searchAPI.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SearchRepository extends MongoRepository<Product, String> {

    Product findByPName(String pName);
    Product findByPCategory(String category);
    Product findByPBrand(String pBrand);
    List <Product> findByPNameLikeOrPCategoryLikeOrPBrandLikeAllIgnoreCase(String pName, String pCat, String pBrand);
    List<Product> findByProductIdIn(List<String> productIdList);
}
