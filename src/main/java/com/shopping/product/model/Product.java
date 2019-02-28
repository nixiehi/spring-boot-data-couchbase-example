package com.shopping.product.model;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Product {
    @Id
    private String id;

    @NotBlank
    private String name;

    @Field
    private Map<String, String> description;

    @NotBlank
    @Field
    private String type;

    @Field
    private Integer amount = 0;

    @NotBlank
    private String pictureUrl;

    @JsonIgnore
    private DateTime cDate = DateTime.now(DateTimeZone.UTC);

    @JsonSerialize(using = CustomDateSerializer.class)
    public DateTime getcDate() {
        return cDate;
    }
}
