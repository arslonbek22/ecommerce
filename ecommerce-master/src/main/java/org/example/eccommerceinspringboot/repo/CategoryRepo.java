package org.example.eccommerceinspringboot.repo;

import org.example.eccommerceinspringboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface CategoryRepo extends JpaRepository<Category,UUID> {

}
