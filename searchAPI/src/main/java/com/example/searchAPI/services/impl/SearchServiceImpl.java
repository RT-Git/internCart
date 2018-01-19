package com.example.searchAPI.services.impl;

import com.example.searchAPI.entity.Product;
import com.example.searchAPI.repository.SearchRepository;
import com.example.searchAPI.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchRepository searchRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Product findByPName(String pName) {
        return searchRepository.findByPName(pName);
    }

    @Override
    public Product findByPCategory(String category) {
        return searchRepository.findByPCategory(category);
    }

    @Override
    public Product findByPBrand(String pBrand) {
        return searchRepository.findByPBrand(pBrand);
    }

    @Override
    public Product save(Product product) {
        if (product.getDateCreated() == null) {
            product.setDateCreated(Date.from(Instant.now()));
        }
        return searchRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return searchRepository.findAll();
    }

    @Override
    public List<Product> findLike(String keyword) {
        List <Product> products = searchRepository.findByPNameLikeOrPCategoryLikeOrPBrandLikeAllIgnoreCase(keyword, keyword, keyword);
		return products;
    }

    @Override
    public Product findOne(String productId) {
        return searchRepository.findOne(productId);
    }

    @Override
    public List<Product> findByPId(List<String> productIds) {
        return searchRepository.findByProductIdIn(productIds);
    }

    @Override
    public List<Product> getRecent(int size) {
        PageRequest pageRequest = new PageRequest(0, size, new Sort(Sort.Direction.DESC, "dateCreated"));
        Page<Product> pageResponse = searchRepository.findAll(pageRequest);
        List <Product> resultList = new ArrayList<>();
        pageResponse.forEach(resultList::add);
        return resultList;
    }
}
