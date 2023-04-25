package com.wipro.graphql.inventoryservicelisting.exception;

public class DuplicateProductInsertionError extends RuntimeException{

    public DuplicateProductInsertionError(String message) {
        super(message);
    }
}
