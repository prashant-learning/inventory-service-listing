package com.wipro.graphql.inventoryservicelisting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wipro.graphql.inventoryservicelisting.model.Product;
import com.wipro.graphql.inventoryservicelisting.model.request.ProductInput;
import com.wipro.graphql.inventoryservicelisting.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class InventoryController {

    @Autowired
    private ProductInventoryService productInventoryService;

    @QueryMapping
    public List<Product> getProducts() {
        return productInventoryService.productsListing();
    }

    @MutationMapping
    public Product createProduct(@Argument ProductInput productInput) throws JsonProcessingException {

        return productInventoryService.insertProductInventory(productInput);
    }

    @MutationMapping
    public Product deleteProduct(@Argument int productId) throws JsonProcessingException {

        return productInventoryService.deleteProductById(productId);
    }

    @QueryMapping
    public Optional<Product> getProductById(@Argument int productId) {

        System.out.println(productId);
        return productInventoryService.productById(productId);
    }

}
