package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.Host;
import com.ajouict.inhousekitchen.domain.MenuImage;
import com.ajouict.inhousekitchen.domain.User;
import com.ajouict.inhousekitchen.dto.HostDto;
import com.ajouict.inhousekitchen.exception.UnAuthorizedException;
import com.ajouict.inhousekitchen.service.HostService;
import com.ajouict.inhousekitchen.storage.StorageService;
import com.ajouict.inhousekitchen.util.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Controller
@RequestMapping("/hosts")
public class ApiHostController {

    @Autowired
    private HostService hostService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/registerForm")
    public String showRegisterPage(@LoginUser User loginUser){
        if(loginUser == null){
            throw new UnAuthorizedException("로그인 해야 합니다.");
        }
        return "/host/regForm";
    }

    @ResponseBody
    @PostMapping("")
    public HostDto registerAsAHost(@LoginUser User loginUser, @ModelAttribute HostDto hostDto, @RequestParam("files") MultipartFile[] files){
        if(loginUser == null){
            throw new UnAuthorizedException("로그인 해야 합니다.");
        }
        return hostService.register(loginUser, hostDto._toHost(), files)._toHostDto();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Host showHostInfo(@LoginUser User loginUser, @PathVariable Long id){
        if(loginUser == null){
            throw new UnAuthorizedException("로그인 해야 합니다.");
        }
        return hostService.findById(id);
    }

}
