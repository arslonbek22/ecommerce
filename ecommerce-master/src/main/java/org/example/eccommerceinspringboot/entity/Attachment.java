package org.example.eccommerceinspringboot.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.eccommerceinspringboot.entity.abs.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attachment extends BaseEntity {

    private byte[] content;

}
