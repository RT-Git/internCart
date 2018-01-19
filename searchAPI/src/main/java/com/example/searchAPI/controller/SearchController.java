package com.example.searchAPI.controller;

import com.example.searchAPI.dto.ProductDTO;
import com.example.searchAPI.entity.Product;
import com.example.searchAPI.services.SearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

/*//  For getting all the products stored in database
//    input - Get method
//    output - list of DTO of products*/
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public ResponseEntity<?> getAll(){
        List<Product> products = searchService.findAll();
        ArrayList<ProductDTO> searchDTOList= new ArrayList<>();
        for (Product prod: products) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(prod, productDTO);
            searchDTOList.add(productDTO);
        }
        return new ResponseEntity<>(searchDTOList, HttpStatus.OK);
    }

/*//  For getting all the products dyanamically depends on user input in search
//    input - Get method and name of search by user
//    output - list of products matches with search result*/
    @RequestMapping(method = RequestMethod.GET, value = "/get/{pName}")
    public ResponseEntity<?> get(@PathVariable("pName") String pName){

        List<Product> products = searchService.findLike(pName);
        ArrayList<ProductDTO> productDTOList = new ArrayList<>();
        for (Product prod: products) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(prod, productDTO);
            productDTOList.add(productDTO);
        }
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

/*//  For getting all the product depends on product name
//    input - Get method and name of product to search by name of product only
//    output - list of products matches with name of product*/
    @RequestMapping(method = RequestMethod.GET, value = "/getByPname/{pName}")
    public ResponseEntity<?> getByPname(@PathVariable("pName") String pName){

        Product product = searchService.findByPName(pName);
        ProductDTO productDTO = new ProductDTO();
            if(product == null){
                return new ResponseEntity<String>("", HttpStatus.OK);
            }
            BeanUtils.copyProperties(product, productDTO);
            return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

/*//  For getting all the products depends on product category
//    input - Get method and name of product to search by name of category only
//    output - list of products matches with category of product*/
    @RequestMapping(method = RequestMethod.GET, value = "/getByCategory/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable("category") String category){
        Product product = searchService.findByPCategory(category);
        ProductDTO productDTO = new ProductDTO();
        if(product == null){
            return new ResponseEntity<String>("", HttpStatus.OK);
        }
        BeanUtils.copyProperties(product, productDTO);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

/*//  For getting all the products depends on product brand
//    input - Get method and name of product to search by brand of product only
//    output - list of products matches with brand of product*/
    @RequestMapping(method = RequestMethod.GET, value = "/getByBrand/{brand}")
    public ResponseEntity<?> getByBrand(@PathVariable("brand") String brand){
        Product product = searchService.findByPBrand(brand);
        ProductDTO productDTO = new ProductDTO();
        if(product == null){
            return new ResponseEntity<String>("", HttpStatus.OK);
        }
        BeanUtils.copyProperties(product, productDTO);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

/*//  For adding a product int database by catalogue API
//    input - post method and details of product to add into product database
//    output - product will be added into database and name will be returns*/
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseEntity<String> addOrUpdate(@RequestBody ProductDTO productDTO){
        System.out.println(productDTO);
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        Product productCreated = searchService.save(product);
        return new ResponseEntity<String>(productCreated.getpName(),HttpStatus.CREATED);
    }

/*//  For getting a product depends on productId for fetching details in front end
//    input - Get method and id of product to search by product of product only
//    output - product matches with product of product*/
    @RequestMapping(method = RequestMethod.GET, value = "/getOne/{productId}")
    public ResponseEntity<?> getOne(@PathVariable("productId") String productId){
        Product product = searchService.findOne(productId);
        ProductDTO productDTO = new ProductDTO();
        if(product == null){
            return new ResponseEntity<String>("", HttpStatus.OK);
        }
        BeanUtils.copyProperties(product, productDTO);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

/*//  For getting a list of products depends on list of productIds
//    input - post method and list of product id
//    output - list of products matchces in the database*/
    @RequestMapping(method = RequestMethod.POST, value = "/getList")
    public ResponseEntity<?> getProducts(@RequestBody List<String> listOfPid) {
        List<Product> products = searchService.findByPId(listOfPid);
        System.out.println(listOfPid);
        ArrayList<ProductDTO> productDTOList = new ArrayList<>();
        for (Product ser : products) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(ser, productDTO);
            productDTOList.add(productDTO);
        }
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

/*//  For getting all the products dyanamically depends on user input in search
//    input - Get method and estimated price of search by user
//    output - list of products matches with search result*/
    @RequestMapping(method = RequestMethod.GET, value = "/getPrice/{price}")
    public ResponseEntity<?> getByPPrice(@PathVariable("price") String pprice){
        List<Product> products = searchService.findAll();
        ArrayList<ProductDTO> productDTOList = new ArrayList<>();

        Integer price = Integer.parseInt(pprice);

        for (Product prod: products) {
            if(price < 1000){
                if(prod.getpPrice() < 1000){
                    ProductDTO productDTO = new ProductDTO();
                    BeanUtils.copyProperties(prod, productDTO);
                    productDTOList.add(productDTO);
                }
            }

            else if(price >=1000 && price < 5000){
                if(prod.getpPrice() >=1000 && prod.getpPrice() < 5000){
                    ProductDTO productDTO = new ProductDTO();
                    BeanUtils.copyProperties(prod, productDTO);
                    productDTOList.add(productDTO);
                }
            }

            else if(price >=5000 && price < 10000){
                if(prod.getpPrice() >=1000 && prod.getpPrice() < 5000){
                    ProductDTO productDTO = new ProductDTO();
                    BeanUtils.copyProperties(prod, productDTO);
                    productDTOList.add(productDTO);
                }
            }

            else if(price > 10000){
                if(prod.getpPrice() > 10000){
                    ProductDTO productDTO = new ProductDTO();
                    BeanUtils.copyProperties(prod, productDTO);
                    productDTOList.add(productDTO);
                }
            }

        }
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

/*//  For getting all the latest items added in product database with limit of 20 to show on home page
//    input - Get method
//    output - list of products sorted by added in database in descedeing order*/
    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getRecent(@RequestParam(value = "size", required = false, defaultValue = "20") Integer size){
        if (size > 20)
            size = 20;
        List <Product> productList = searchService.getRecent(size);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
