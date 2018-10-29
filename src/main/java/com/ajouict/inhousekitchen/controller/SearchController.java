package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.Host;
import com.ajouict.inhousekitchen.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView sampleGeo(){
        ModelAndView mv=new ModelAndView("/host/sampleGeo2","hostList",searchService.hostList());
        return mv;
    }

    @GetMapping("/getHostList")
    @ResponseBody
    public List<Host> getHostList(){
        List<Host> hostList=searchService.hostList();
        System.out.println("/getHostList : "+hostList);
        return hostList;
    }

    @PostMapping("/userLocation")
    public String getUserLocation(@RequestParam(name="latitude") double latitude, @RequestParam(name="longitude") double longitude){
        System.out.println("위도 : "+latitude);
        System.out.println("경도 : "+longitude);

        return "redirect:/";
    }



}

