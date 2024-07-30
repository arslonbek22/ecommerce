package org.example.eccommerceinspringboot.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eccommerceinspringboot.entity.Product;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class BasketProduct {
    private Product product;
    private Integer amount;
}
