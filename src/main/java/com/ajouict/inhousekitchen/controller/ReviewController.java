package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private UserRepository userRepository;
    /*
    @GetMapping("/review")
    public String create() {
        return "/review/add_review";
    }
    */
    @GetMapping("")
    public String list(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "/review/list_review";
    }
}
