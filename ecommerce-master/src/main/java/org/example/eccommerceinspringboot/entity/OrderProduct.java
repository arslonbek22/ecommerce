package org.example.eccommerceinspringboot.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.eccommerceinspringboot.entity.abs.BaseEntity;
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
 public class OrderProduct extends BaseEntity {

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private Integer amount;

}
