package org.example.eccommerceinspringboot.repo;

import org.example.eccommerceinspringboot.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepo extends JpaRepository<OrderProduct, UUID>{
    List<OrderProduct> findByOrderId(Integer orderId);
}
