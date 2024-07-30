package org.example.eccommerceinspringboot.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.eccommerceinspringboot.entity.abs.BaseEntity;


import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category extends BaseEntity {
    private String name;
    private LocalDateTime created_at;

}
