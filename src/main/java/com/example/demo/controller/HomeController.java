package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/home")
    public String homeIndex() {
        return "home";
    }

    @PostMapping("/user/{id}")
    public String userInfo(@PathVariable long id, Model model) {
        Optional<User> userOpt = iUserService.findById(id);
        if (!userOpt.isPresent()) {
            throw new NotFoundException("User not found");
        }

        model.addAttribute("user", userOpt.get());

        return "user-details";
    }
}
