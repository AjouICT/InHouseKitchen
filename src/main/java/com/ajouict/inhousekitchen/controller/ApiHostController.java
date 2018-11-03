package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.Host;
import com.ajouict.inhousekitchen.service.HostService;
import com.ajouict.inhousekitchen.storage.StorageService;
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
    public String showRegisterPage(){
        return "/user/regForm";
    }

    // TODO : 1. 로그인 상태여야 하고
    // TODO : 2. 현재 로그인한 사용자의 정보를 얻어야 한다.

    @ResponseBody
    @PostMapping("")
    public Host registerAsAHost(@ModelAttribute Host host, @RequestParam("files") MultipartFile[] files){
        Arrays.stream(files).forEach(file -> storageService.store(file));
        return hostService.register(host);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Host showHostInfo(@PathVariable Long id){
        return hostService.findById(id);
    }

}
