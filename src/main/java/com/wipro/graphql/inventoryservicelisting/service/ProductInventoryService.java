package com.wipro.graphql.inventoryservicelisting.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.graphql.inventoryservicelisting.exception.DuplicateProductInsertionError;
import com.wipro.graphql.inventoryservicelisting.exception.ProductNotFoundException;
import com.wipro.graphql.inventoryservicelisting.model.Product;
import com.wipro.graphql.inventoryservicelisting.model.request.ProductInput;
import com.wipro.graphql.inventoryservicelisting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductInventoryService {

    @Autowired
    private ProductRepository productRepository;

    public Product insertProductInventory(ProductInput productInput) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(productInput);
        Product productEntity = mapper.readValue(jsonString, Product.class);

        if (productRepository.findById(productEntity.getProductId()).isPresent()){
            throw new DuplicateProductInsertionError("Product Already exist");
        }
        return productRepository.save(productEntity);

    }

    public List<Product> productsListing(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().iterator().forEachRemaining(products::add);
        return products;
    }


    public Optional<Product> productById(int productId){

        return productRepository.findById(productId);
    }

    /**
     *
     *  1. Simply delete it and always send 200 response either it present or not present we dont care
     *  2. First get and verify if item present, if present then delete or else return different(404) status code
     *  3. First get and if item is present then save it to archive table and delete from original table
     */
    public Product deleteProductById(int productId){
        Optional<Product> mayBeproduct = productRepository.findById(productId);
        if (mayBeproduct.isPresent()){
            productRepository.deleteById(productId);
        } else {
            throw new ProductNotFoundException("Product is not present in our inventory");
        }
        return mayBeproduct.get();
    }
}
