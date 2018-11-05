package com.ajouict.inhousekitchen.service;

import com.ajouict.inhousekitchen.domain.Host;
import com.ajouict.inhousekitchen.domain.HostRepository;
import com.ajouict.inhousekitchen.domain.User;
import com.ajouict.inhousekitchen.exception.NoSuchHostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostService {
    @Autowired
    private HostRepository hostRepository;

    public Host register(Host host, User loginUser) {
        host.registerUserInfo(loginUser);
        return hostRepository.save(host);
    }

    public Host findById(Long id) {
        return hostRepository.findById(id).orElseThrow(() -> new NoSuchHostException("id에 해당하는 호스트가 존재하지 않습니다."));
    }
}
