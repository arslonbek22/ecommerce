package org.example.eccommerceinspringboot.repo;

import org.example.eccommerceinspringboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Integer> {

}
