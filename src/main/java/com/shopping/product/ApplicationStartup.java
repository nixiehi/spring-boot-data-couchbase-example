package com.shopping.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.product.core.logging.L;
import com.shopping.product.model.Product;
import com.shopping.product.repository.ProductRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Set;

@Profile("local")
@Component
public class ApplicationStartup {

    @Autowired
    ProductRepository productRepository;

    @EventListener(ApplicationStartedEvent.class)
    public void ingestProducts() {
        try {
            val products = parse("product_data.json");
            productRepository.saveAll(products);
        } catch (Exception e) {
            L.errorE("An unexpected error occurred while ingesting product data!", e);
        }
    }

    private Set<Product> parse(String path) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, new TypeReference<Set<Product>>() {
        });
    }
}

