package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.Host;
import com.ajouict.inhousekitchen.domain.MenuImage;
import com.ajouict.inhousekitchen.domain.User;
import com.ajouict.inhousekitchen.dto.HostDto;
import com.ajouict.inhousekitchen.exception.UnAuthorizedException;
import com.ajouict.inhousekitchen.service.HostService;
import com.ajouict.inhousekitchen.storage.StorageService;
import com.ajouict.inhousekitchen.util.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/hosts")
public class ApiHostController {
    private static final Logger log = LoggerFactory.getLogger(ApiHostController.class);


    @Autowired
    private HostService hostService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/registerForm")
    public String showRegisterPage(@LoginUser User loginUser){
        if(loginUser == null){
            throw new UnAuthorizedException("로그인 해야 합니다.");
        }
        return "/host/registerForm";
    }

    @PostMapping("")
    public String registerAsAHost(@LoginUser User loginUser, @ModelAttribute HostDto hostDto, @RequestParam("files") MultipartFile[] files, Model model){
        if(loginUser == null){
            throw new UnAuthorizedException("로그인 해야 합니다.");
        }
        Host host =  hostService.register(loginUser, hostDto._toHost(), files);
        List<MenuImagePath> menuImagePaths = host.getMenuImages().stream().map(menuImage -> new MenuImagePath(storageService.load(menuImage.getUniqueImgName()))).collect(Collectors.toList());

        model.addAttribute("host", host._toHostDto());
        model.addAttribute("menuImagePaths", menuImagePaths);

        return "/host/host_detail";
    }

    @GetMapping("/{id}")
    public String showHostInfo(@PathVariable Long id, Model model) {
        model.addAttribute("host", hostService.findById(id)._toHostDto());
        return "/host/host_detail";
    }

}
