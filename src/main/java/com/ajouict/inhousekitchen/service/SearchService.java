package com.ajouict.inhousekitchen.service;

import com.ajouict.inhousekitchen.domain.Host;
import com.ajouict.inhousekitchen.domain.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    SearchRepository searchRepository;

    public List<Host> hostList(){
        return searchRepository.findAll();
    }

}
