package org.example.eccommerceinspringboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.eccommerceinspringboot.entity.abs.BaseEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

    private String name;
    private Integer price;
    private LocalDateTime created_at;

    @ManyToOne
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment attachment;


    @Transient
    private boolean hasInBasket;

}
