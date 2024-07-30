package org.example.eccommerceinspringboot.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.eccommerceinspringboot.entity.Attachment;
import org.example.eccommerceinspringboot.entity.Product;
import org.example.eccommerceinspringboot.repo.AttachmentRepo;
import org.example.eccommerceinspringboot.repo.CategoryRepo;
import org.example.eccommerceinspringboot.repo.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.UUID;


@RequiredArgsConstructor
@RequestMapping("/file")
@Controller
public class AttachmentController {

    private final ProductRepo productRepo;

    @Transactional
    @GetMapping("/product/{id}")
    public void returnProductImg(@PathVariable UUID id, HttpServletResponse response) throws IOException {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("product file not found"));
        Attachment attachment = product.getAttachment();
        response.getOutputStream().write(attachment.getContent());
    }
}
