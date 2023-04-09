package com.vaos.store.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @NotBlank(message = "Please Provide Product Name")
    private String productName;

    @NotNull
    private BigDecimal productPrice;
    @NotBlank(message = "Please Provide Product Manufacturing Date")
    String productManufacturingDate;

    @Min(0)
    @Max(1)
    @Column( columnDefinition = "int default 0")
    int productIsAvailable;

    @Min(0)
    @Max(5)
    private int productRating = 1;

}
