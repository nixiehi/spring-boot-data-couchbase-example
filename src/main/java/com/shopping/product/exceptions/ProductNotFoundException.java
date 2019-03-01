package com.shopping.product.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productId) {
        super(String.format("No such a product with id: %s", productId));
    }
}

