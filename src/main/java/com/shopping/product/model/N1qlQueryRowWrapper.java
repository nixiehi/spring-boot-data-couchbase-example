package com.shopping.product.model;

import com.couchbase.client.java.query.N1qlQueryRow;

import java.util.Map;
import java.util.Optional;

public class N1qlQueryRowWrapper {
    private Map<String, Object> rows;

    public N1qlQueryRowWrapper(N1qlQueryRow row) {
        this.rows = row.value().toMap();
    }

    public Optional<Object> get(String key){
        return Optional.of(rows.get(key));
    }
}
