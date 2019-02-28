package com.shopping.product.controller;

import com.shopping.product.core.PageWrapper;
import com.shopping.product.model.Product;
import com.shopping.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("api/v1/")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<PageWrapper<Product>> getAll(@RequestParam("page") @PositiveOrZero Integer page,
                                                       @RequestParam("size") @Positive Integer size) {
        try {
            return ResponseEntity.ok().body(productService.getAll(page, size));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
