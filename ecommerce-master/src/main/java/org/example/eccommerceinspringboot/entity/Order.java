package org.example.eccommerceinspringboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.eccommerceinspringboot.entity.abs.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity

@Table(name = "orders")
public class Order{
    @Id
    @SequenceGenerator(name="order_id_seq", sequenceName = "order_id_seq",initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_id_seq")
    private Integer id;

    @CreationTimestamp
    private LocalDateTime orderedAt;

}
