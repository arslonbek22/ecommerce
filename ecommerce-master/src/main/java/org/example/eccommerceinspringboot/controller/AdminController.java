package org.example.eccommerceinspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.eccommerceinspringboot.entity.Category;
import org.example.eccommerceinspringboot.entity.Product;
import org.example.eccommerceinspringboot.repo.CategoryRepo;
import org.example.eccommerceinspringboot.repo.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @GetMapping("/category")
    public String category(Model model){
        List<Product> products = productRepo.findAll();
        model.addAttribute("products",products);
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories",categories);
        return "admin/category";
    }

    @GetMapping("/category/create")
    public String categoryCreate(){
        return "admin/create-category";
    }

    @PostMapping("/category/delete/{id}")
    public String categoryDelete(@PathVariable UUID id){
        categoryRepo.deleteById(id);
        return "redirect:/admin/category";
    }

    @GetMapping("/product")
    public String product(Model model){
        List<Product> products = productRepo.findAll();
        model.addAttribute("products",products);
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories",categories);
        return "admin/product";
    }
    @GetMapping("/product/create")
    public String productCreate(Model model){
        List<Product> products = productRepo.findAll();
        model.addAttribute("products",products);
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories",categories);
        return "admin/create-product";
    }

}
