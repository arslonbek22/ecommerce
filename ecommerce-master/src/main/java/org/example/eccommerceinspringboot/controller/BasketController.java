package org.example.eccommerceinspringboot.controller;


import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.eccommerceinspringboot.dto.BasketProduct;
import org.example.eccommerceinspringboot.entity.Order;
import org.example.eccommerceinspringboot.entity.OrderProduct;
import org.example.eccommerceinspringboot.entity.Product;
import org.example.eccommerceinspringboot.repo.OrderProductRepo;
import org.example.eccommerceinspringboot.repo.OrderRepo;
import org.example.eccommerceinspringboot.repo.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/basket")
public class BasketController {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final OrderProductRepo orderProductRepo;

    @SuppressWarnings("unchecked")
    @PostMapping
    public String addBasket(@RequestParam UUID productID, HttpSession session) {
        Product product = productRepo.findById(productID).orElseThrow(() -> new RuntimeException("Not Fount Product"));
        Object basket = session.getAttribute("basket");
        if (basket == null){
            BasketProduct basketProduct = new BasketProduct(
                    product,1
            );
            List<BasketProduct> basketProducts = new ArrayList<>();
            basketProducts.add(basketProduct);
            session.setAttribute("basket",basketProducts);
        }else{
            List<BasketProduct> basketProducts = (List<BasketProduct>) basket;
            BasketProduct basketProduct = new BasketProduct(
                    product,1
            );
            basketProducts.add(basketProduct);
            session.setAttribute("basket",basketProducts);
        }
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteBasket(@RequestParam UUID productID,HttpSession session){
       deleteFromBasket(productID,session);
        return "redirect:/";
    }

    @SuppressWarnings("unchecked")
    private void deleteFromBasket(UUID productID, HttpSession session) {
        List<BasketProduct> basketProducts = (List<BasketProduct>)session.getAttribute("basket");
        List<BasketProduct> list = basketProducts.stream().filter(item -> !item.getProduct().getId().equals(productID)).toList();
        session.setAttribute("basket",new ArrayList<>(list));
    }

    @SuppressWarnings("unchecked")
    @GetMapping
    public String showBasket(HttpSession session, Model model){
        List<BasketProduct> list = Objects.requireNonNullElse((List<BasketProduct>)session.getAttribute("basket"),new ArrayList<>());
        if (list.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("basket",list);
        int sum = list.stream().mapToInt(item -> item.getProduct().getPrice() * item.getAmount()).sum();
        model.addAttribute("totalAmount",sum);
        return "/admin/basket";
    }
    @SuppressWarnings("unchecked")
    @PostMapping("/amount")
    public String changeBasketProductAmount(HttpSession session ,@RequestParam UUID productID,@RequestParam String operation ){
        List<BasketProduct> basketProducts = (List<BasketProduct>) session.getAttribute("basket");
        for (BasketProduct basketProduct : basketProducts) {
            if (basketProduct.getProduct().getId().equals(productID)){
               if (operation.equals("++")){
                   basketProduct.setAmount(basketProduct.getAmount()+1);
                   session.setAttribute("basket",basketProducts);
               }else {
                   if (basketProduct.getAmount()==1){
                       deleteFromBasket(productID,session);
                   } else {
                       basketProduct.setAmount(basketProduct.getAmount()-1);
                       session.setAttribute("basket",basketProducts);
                   }
               }
                return "redirect:/basket";
            }
        }
        return "redirect:/basket";
    }

    @Transactional
    @PostMapping("/checkout")
    public String heckout(HttpSession session ){
        List<BasketProduct> basketProducts = Objects.requireNonNullElse((List<BasketProduct>) session.getAttribute("basket"), new ArrayList<>());
        Order order = new Order();
        orderRepo.save(order);
        for (BasketProduct basketProduct : basketProducts) {
            OrderProduct orderProduct = new OrderProduct(
                    order,
                    basketProduct.getProduct(),
                    basketProduct.getAmount()
            );
            orderProductRepo.save(orderProduct);
        }
        session.removeAttribute("basket");
        return "redirect:/basket";
    }

}
