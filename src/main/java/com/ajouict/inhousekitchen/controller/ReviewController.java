package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.*;
import com.ajouict.inhousekitchen.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SearchRepository searchRepository;

    @GetMapping("/{userId}")
    public String show(@PathVariable String userId, Model model, HttpSession session) {

        User temp = userRepository.findByUserId(userId);
        // 호스트
        Host user = searchRepository.findByHost(temp);

        User loginUser = HttpSessionUtils.getUserFromSession(session);
        

        model.addAttribute("loginUser", loginUser);
        model.addAttribute("user", user);

        return "/review/add_review";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "/review/list_review";
    }

    @PostMapping("/create/{hostId}/{userId}")
    public String create(@PathVariable Long hostId, @PathVariable String userId, HttpSession session, String title, String contents){

        System.out.println("호스트 아이디 : " + hostId);
        System.out.println("유저 아이디 : " + userId);

        User writer = HttpSessionUtils.getUserFromSession(session);
        Host host = searchRepository.findByid(hostId);
        User tempHost = userRepository.getOne(hostId);

        System.out.println("login : " + writer);
        System.out.println("host : " + host);

        if(host.IsSameHost(writer)){
           return String.format("redirect:/review/%s", tempHost.getUserId());
        }

        Review review = new Review(writer, host, title, contents);
        reviewRepository.save(review);

        return String.format("redirect:/review/%s", tempHost.getUserId());
    }

    @GetMapping("/update/{reviewId}")
    public String update(@PathVariable Long reviewId, HttpSession session, Model model){
        // 글쓴이
        User writer = HttpSessionUtils.getUserFromSession(session);
        // 리뷰
        Review review = reviewRepository.findByid(reviewId);
        User hostUser = review.getHost().getHost();

        if(!review.IsSameWriter(writer)) {
            return String.format("redirect:/review/%s", hostUser.getUserId());
        }
        model.addAttribute("review", review);
        return "review/update_review";
    }

    @PutMapping("/{reviewId}")
    public String update(@PathVariable Long reviewId, String title, String contents, HttpSession session){
        Review review = reviewRepository.findByid(reviewId);

        review.update(review, title, contents);
        reviewRepository.save(review);

        return String.format("redirect:/review/%s", review.getHost().getHost().getUserId());
    }

    @GetMapping("/delete/{reviewId}")
    public String delete(@PathVariable Long reviewId, HttpSession session){
        Review review = reviewRepository.findByid(reviewId);
        User hostUser = review.getHost().getHost();
        User writer = HttpSessionUtils.getUserFromSession(session);

        // 작성자가 아닌 경우 리스트로 보냄
        if(!review.IsSameWriter(writer)){
            return String.format("redirect:/review/%s", hostUser.getUserId());
        }

        reviewRepository.delete(review);

        return String.format("redirect:/review/%s", hostUser.getUserId());
    }
}
