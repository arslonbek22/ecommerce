package org.example.eccommerceinspringboot.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.example.eccommerceinspringboot.dto.ProductReq;
import org.example.eccommerceinspringboot.entity.Attachment;
import org.example.eccommerceinspringboot.entity.Category;
import org.example.eccommerceinspringboot.entity.Product;
import org.example.eccommerceinspringboot.repo.AttachmentRepo;
import org.example.eccommerceinspringboot.repo.CategoryRepo;
import org.example.eccommerceinspringboot.repo.ProductRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final AttachmentRepo attachmentRepo;

    @PostMapping
    public String saveProduct(@ModelAttribute ProductReq productReq, @RequestParam("file") MultipartFile file) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        LocalDateTime localDateTime = LocalDateTime.parse(formattedDateTime, formatter);
        Attachment attachment = attachmentRepo.save(new Attachment(file.getBytes()));
        Category category = categoryRepo.findById(productReq.categoryID()).orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = new Product(
                productReq.name(),
                productReq.price(),
                localDateTime,
                category,
                attachment,
                false
        );
        productRepo.save(product);
        return "redirect:/admin/product";
    }
}
