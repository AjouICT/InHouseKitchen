package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.HostDto;
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
        return null;
    }

    @PostMapping("")
    @ResponseBody
    public HostDto registerAsAHost(@ModelAttribute HostDto hostDto, @RequestParam("files") MultipartFile[] files){
        Arrays.stream(files).forEach(file -> storageService.store(file));
        return hostService.register(hostDto._toHost())._toHostDto();
    }

    @GetMapping("/{id}")
    public HostDto showHostInfo(@PathVariable Long id){
        return hostService.findById(id)._toHostDto();
    }

}
