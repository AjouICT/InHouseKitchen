package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.Host;
import com.ajouict.inhousekitchen.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("")
    public String hostList(Model model){
        List<Host> hostList=searchService.hostList();
        model.addAttribute("hostList",hostList);
        return "/host/hostList";
    }

    @GetMapping("/sample")
    public String sampleGeo(Model model){

        return "/host/sampleGeo";
    }



}

