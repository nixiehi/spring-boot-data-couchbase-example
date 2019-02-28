package com.shopping.product.service;

import com.shopping.product.core.PageWrapper;
import com.shopping.product.model.Product;
import com.shopping.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.repository.query.N1qlCountQueryCreator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public PageWrapper<Product> getAll(int pageNo, int size){
        Page<Product> result = productRepository.findAll(PageRequest.of(pageNo, size));
        return new PageWrapper<>(result);
    }
}
