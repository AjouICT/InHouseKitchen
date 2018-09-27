package com.ajouict.inhousekitchen.service;

import com.ajouict.inhousekitchen.controller.UserController;
import com.ajouict.inhousekitchen.domain.User;
import com.ajouict.inhousekitchen.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log=LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;


    public void registerMember(User user){
        userRepository.save(user);
    }

    public String checkId(String id){
        log.info("UserService CheckId : "+id);
        User user=userRepository.findByUserId(id);
        String result="0";
        if(user==null){
            return result;
        }else{
            result="1";
        }
        return result;
    }

    public User findById(String userId){
        User user=userRepository.findByUserId(userId);
        return user;
    }


}
