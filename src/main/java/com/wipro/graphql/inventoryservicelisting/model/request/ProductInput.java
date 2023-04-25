package com.wipro.graphql.inventoryservicelisting.model.request;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInput {

    private int productId;
    private String productName;
    private String manufacturedBy;
    private String markedBy;
    private double price;
    private int discount;
    private String soldBy;
    private String feature;
    private String description;
    private String specification;
    private String imageUrl;
    private double reviews;
    private String customerFeedback;
}
