package com.ajouict.inhousekitchen.service;

import com.ajouict.inhousekitchen.domain.*;
import com.ajouict.inhousekitchen.exception.NoSuchHostException;
import com.ajouict.inhousekitchen.storage.StorageService;
import com.ajouict.inhousekitchen.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Service
public class HostService {

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private VisitService visitService;

    public Host register(User loginUser, Host host, MultipartFile[] files) {
        Host savedHost = Host.registerUserInfo(loginUser, host);
        Arrays.stream(files).forEach(file -> saveMenuImageAndInfo(savedHost, file));
        return hostRepository.save(savedHost);
    }

    private void saveMenuImageAndInfo(Host host, MultipartFile file) {
        String uniqueFileName = UUIDGenerator.generateUniqueFileName(file.getOriginalFilename());

        storageService.store(file, uniqueFileName);
        host.recordMenuImageInfo(new MenuImage(file.getOriginalFilename(), uniqueFileName));
    }

    private Host findById(Long id) {
        return hostRepository.findById(id).orElseThrow(() -> new NoSuchHostException("id에 해당하는 host 가 존재하지 않습니다."));
    }

    @Transactional
    public Host calculateRateOfVisitorsAboveTwoTimesVisitedAndFindHost(Long id) {
        Host host = findById(id);
        host.recordVisitRate(new VisitRate(visitService.calculateRateOfVisitorsAboveTwoTimesVisited(id),
                visitService.calculateRateOfVisitorsAboveThreeTimesVisited(id)));
        return hostRepository.save(host);
    }

}
