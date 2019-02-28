package com.shopping.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.product.core.PageWrapper;
import com.shopping.product.model.Product;
import com.shopping.product.service.ProductService;
import lombok.val;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.shopping.product.testbeans.TestProductBuilder.aProduct;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAll_ShouldReturnOkWithProducts() throws Exception {
        val product1 = aProduct();
        val product2 = aProduct();
        val products = pageOf(product1, product2);

        when(service.getAll(0, 2)).thenReturn(products);

        this
                .mockMvc.perform(get("/api/v1/products")).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(products)));
    }

    private PageWrapper pageOf(Product... product) {
        return new PageWrapper<Product>(new PageImpl<>(List.of(product)));
    }

}
