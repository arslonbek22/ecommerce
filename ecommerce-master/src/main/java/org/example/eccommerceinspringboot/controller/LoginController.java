package org.example.eccommerceinspringboot.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.example.eccommerceinspringboot.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpSession session) {
        Optional<org.example.eccommerceinspringboot.entity.User> userOpt = userRepo.findByUsername(user.getUsername());
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            return "/admin/category";
        }
        return "auth/login";
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute org.example.eccommerceinspringboot.entity.User user) {
        Optional<org.example.eccommerceinspringboot.entity.User> byUsername = userRepo.findByUsername(user.getUsername());
        if (byUsername.isPresent()) {
            return "auth/register";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return "/auth/login";
        }
    }
}