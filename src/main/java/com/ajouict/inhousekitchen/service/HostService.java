package com.ajouict.inhousekitchen.service;

import com.ajouict.inhousekitchen.domain.Host;
import com.ajouict.inhousekitchen.domain.HostRepository;
import com.ajouict.inhousekitchen.domain.MenuImage;
import com.ajouict.inhousekitchen.domain.User;
import com.ajouict.inhousekitchen.exception.NoSuchHostException;
import com.ajouict.inhousekitchen.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Set;

@Service
public class HostService {
    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private StorageService storageService;

    public Host register(User loginUser, Host host, MultipartFile[] files) {
        Host savedHost = Host.registerUserInfo(loginUser, host);
        Arrays.stream(files).forEach(file -> saveMenuImageAndInfo(savedHost, file));
        return hostRepository.save(savedHost);
    }

    private void saveMenuImageAndInfo(Host host, MultipartFile file) {
        storageService.store(file);
        host.recordMenuImageInfo(new MenuImage(file.getOriginalFilename()));
    }

    public Host findById(Long id) {
        return hostRepository.findById(id).orElseThrow(() -> new NoSuchHostException("id에 해당하는 호스트가 존재하지 않습니다."));
    }

}
