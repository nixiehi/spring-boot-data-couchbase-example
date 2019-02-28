package com.shopping.product.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;


public class PageWrapper<T> {
    @JsonIgnore
    private Page<T> underlying;
    @Getter
    private List<T> items;
    @Getter
    private Long totalItems;
    @Getter
    private Integer totalPages;
    @Getter
    private Integer number;
    @Getter
    private Integer numberOfItems;

    public PageWrapper(Page<T> page) {
        this.underlying = page;
        this.items = page.getContent();
        this.totalItems = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.number = page.getNumber();
        this.numberOfItems = page.getNumberOfElements();
    }
}
