package com.shopping.product.repository;

import com.shopping.product.model.Product;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "product")
@Repository
public interface ProductRepository extends CouchbasePagingAndSortingRepository<Product, String> {
}
