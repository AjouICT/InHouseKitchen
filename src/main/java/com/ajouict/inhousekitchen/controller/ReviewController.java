package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.Review;
import com.ajouict.inhousekitchen.domain.ReviewRepository;
import com.ajouict.inhousekitchen.domain.User;
import com.ajouict.inhousekitchen.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/{userId}")
    public String create(@PathVariable String userId, Model model, HttpSession session) {
        // 로그인하지 않았을 경우
        if(!HttpSessionUtils.isLoginUser(session)){

        }
        User loginUser = HttpSessionUtils.getUserFromSession(session);
        User host = userRepository.findByUserId(userId);

        model.addAttribute("loginUser", loginUser);
        model.addAttribute("user", userRepository.findByUserId(userId));
        model.addAttribute("reviews", reviewRepository.findAll());

        return "/review/add_review";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "/review/list_review";
    }

    @PutMapping("/create/{userId}")
    public String create(@PathVariable String userId, HttpSession session, String title, String contents){

        User writer = HttpSessionUtils.getUserFromSession(session);
        Review review = new Review(writer, title, contents);
        reviewRepository.save(review);

        return String.format("redirect:/review/%s", writer.getUserId());
    }
}
