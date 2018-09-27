package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.User;
import com.ajouict.inhousekitchen.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger log=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/loginForm")
    public String login(){
        return "/user/login";
    }

    @GetMapping("/regForm")
    public String regForm(){
        return "/user/regForm";
    }

    @PostMapping("/register")
    public String registerMember(User user){
        userService.registerMember(user);
        return "redirect:/";
    }

    @GetMapping("/checkId")
    @ResponseBody
    public String checkId(String id){
        log.info("Check User id : "+id);
        String data=userService.checkId(id);
        return data;
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session){
        User user=userService.findById(userId);
        if(user==null){
            log.info("Login fail - no member exist");
            return "redirect:/users/loginForm";
        }

        if(!user.matchPassword(password)){
            log.info("Login fail - not correct password");
            return "redirect:/users/loginForm";
        }

        log.info("Login Success");
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY,user);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return "redirect:/";
    }





}
