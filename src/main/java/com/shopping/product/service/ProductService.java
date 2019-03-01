package com.shopping.product.service;

import com.couchbase.client.java.query.N1qlParams;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.Statement;
import com.couchbase.client.java.query.consistency.ScanConsistency;
import com.couchbase.client.java.query.dsl.Expression;
import com.couchbase.client.java.query.dsl.path.DefaultDeleteUsePath;
import com.couchbase.client.java.query.dsl.path.WherePath;
import com.shopping.product.core.PageWrapper;
import com.shopping.product.exceptions.ProductNotFoundException;
import com.shopping.product.model.N1qlQueryResultWrapper;
import com.shopping.product.model.Product;
import com.shopping.product.repository.ProductRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.repository.query.N1qlCountQueryCreator;
import org.springframework.data.couchbase.repository.query.support.N1qlUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public PageWrapper<Product> getAll(int pageNo, int size) {
        Page<Product> result = productRepository.findAll(PageRequest.of(pageNo, size));
        return new PageWrapper<>(result);
    }

    public Optional<Product> findBy(String id) {
        return productRepository.findById(id);
    }

    public boolean deleteBy(String id) {
        val queryResult = doExecuteDelete(id);
        return queryResult.hasValue("id", id);
    }

    private N1qlQueryResultWrapper doExecuteDelete(String id){
        String query = String.format("DELETE FROM `product` USE KEYS \"%s\" RETURNING meta().id", id);
        return new N1qlQueryResultWrapper(
                productRepository.getCouchbaseOperations().getCouchbaseBucket().query(N1qlQuery.simple(query))
        );
    }


}
