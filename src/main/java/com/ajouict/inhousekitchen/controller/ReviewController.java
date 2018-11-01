package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

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
        System.out.println("userId : " + userId);

        User temp = userRepository.findByUserId(userId);

        Host user = searchRepository.findByHost(temp);

        User loginUser = HttpSessionUtils.getUserFromSession(session);

        System.out.println("host : " + user);
        System.out.println("loginUser : " + loginUser);

        model.addAttribute("loginUser", loginUser);
        model.addAttribute("user", user);
        //model.addAttribute("reviews", reviews);
        return "/review/add_review";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "/review/list_review";
    }

    @PutMapping("/create/{hostId}/{userId}")
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
}
