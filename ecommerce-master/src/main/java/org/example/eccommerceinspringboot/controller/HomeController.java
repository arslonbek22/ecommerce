package org.example.eccommerceinspringboot.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.eccommerceinspringboot.dto.BasketProduct;
import org.example.eccommerceinspringboot.entity.Category;
import org.example.eccommerceinspringboot.entity.Product;
import org.example.eccommerceinspringboot.repo.CategoryRepo;
import org.example.eccommerceinspringboot.repo.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class HomeController  {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @SuppressWarnings("unchecked")
    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) UUID categoryID, HttpSession session) {
        List<Category> categories = categoryRepo.findAll();
        List<Product> products;
        if (categoryID != null) {
            products = productRepo.findAllByCategoryId(categoryID);
            model.addAttribute("categoryID", categoryID);
        } else {
            products = productRepo.findAll();
        }
        for (Product product : products) {
            if(hasInBasket(product,session)){
                product.setHasInBasket(true);

            }
        }
        Integer basketSize = Objects.requireNonNullElse((List<BasketProduct>)session.getAttribute("basket"),new ArrayList<>()).size();
        model.addAttribute("basketSize",basketSize);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "home";
    }

    @SuppressWarnings("unchecked")
    private boolean hasInBasket(Product product, HttpSession session) {
        List<BasketProduct> basketProducts = Objects.requireNonNullElse((List<BasketProduct>)session.getAttribute("basket"),new ArrayList<>());
        for (BasketProduct basketProduct : basketProducts) {
            if (basketProduct.getProduct().getId().equals(product.getId())){
                return true;
            }
        }
        return false;
    }

}
