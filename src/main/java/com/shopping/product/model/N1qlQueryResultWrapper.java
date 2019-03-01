package com.shopping.product.model;

import com.couchbase.client.java.query.N1qlQueryResult;
import lombok.Data;
import lombok.val;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class N1qlQueryResultWrapper {
    private String status;
    private Set<N1qlQueryRowWrapper> rows;

    public N1qlQueryResultWrapper(N1qlQueryResult result) {
        this.status = result.status();
        this.rows = result.allRows().stream().map(N1qlQueryRowWrapper::new).collect(Collectors.toSet());
    }

    public <T> boolean hasValue(String key, T value) {
        return findAll(key).contains(value);
    }

    public Set<Object> findAll(String key) {
        return rows
                .stream()
                .flatMap(o -> {
                    val maybeValue = o.get(key);
                    return maybeValue.stream().flatMap(Stream::of);
                })
                .collect(Collectors.toSet());
    }
}
