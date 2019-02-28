package com.shopping.product.testbeans;

import com.shopping.product.model.Product;
import com.shopping.product.model.Product.ProductBuilder;
import lombok.val;

import java.util.HashMap;
import java.util.UUID;

public final class TestProductBuilder {
    private static ProductBuilder builder = Product.builder();

    public static Product aProduct(){
        return aProductBuilder().build();
    }

    public static TestProductBuilder aProductBuilder() {
        val generatedRandom = UUID.randomUUID().toString();
        builder.id(generatedRandom);
        builder.amount(1);
        builder.description( new HashMap<String, String>());
        builder.type("");

        return new TestProductBuilder();
    }

    public Product build(){
        return builder.build();
    }

}