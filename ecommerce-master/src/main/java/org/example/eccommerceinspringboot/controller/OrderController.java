package org.example.eccommerceinspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.eccommerceinspringboot.entity.Order;
import org.example.eccommerceinspringboot.entity.OrderProduct;
import org.example.eccommerceinspringboot.repo.OrderProductRepo;
import org.example.eccommerceinspringboot.repo.OrderRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepo orderRepo;
    private final OrderProductRepo orderProductRepo;

    @GetMapping
    public String showOrder(Model model){
        List<Order> orders = orderRepo.findAll();
        model.addAttribute("order",orders);
        return "/admin/order";
    }

    @GetMapping("/info/{id}")
    public String orderInfo(@PathVariable Integer id, Model model){
        List<OrderProduct> orders = orderProductRepo.findByOrderId(id);
        model.addAttribute("orderProducts",orders);
        return "/admin/info";

    }

}
