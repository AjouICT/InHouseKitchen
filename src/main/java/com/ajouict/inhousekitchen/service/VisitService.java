package com.ajouict.inhousekitchen.service;

import com.ajouict.inhousekitchen.domain.*;
import com.ajouict.inhousekitchen.exception.NoSuchHostException;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

	private final VisitRepository visitRepository;
	private final HostRepository hostRepository;

	public VisitService(VisitRepository visitRepository, HostRepository hostRepository) {
		this.visitRepository = visitRepository;
		this.hostRepository = hostRepository;
	}

	public Visit record(Long id, User user) {
		Host host = hostRepository.findById(id).orElseThrow(() -> new NoSuchHostException("id에 해당하는 host가 존재하지 않습니다."));
		return visitRepository.save(new Visit(user, host));
	}

	public double calculateRateOfVisitorsAboveTwoTimesVisited(Long hostId){
		return bringVisitorsAboveTwoTimesVisited(hostId) / (double)(bringTotalVisitors(hostId));
	}

	public double calculateRateOfVisitorsAboveThreeTimesVisited(Long hostId){
		return bringVisitorsAboveThreeTimesVisited(hostId) / (double)(bringTotalVisitors(hostId));
	}

	private Long bringTotalVisitors(Long hostId) {
		return visitRepository.countRowPerHost(hostId);
	}

	private Long bringVisitorsAboveTwoTimesVisited(Long hostId) {
		return visitRepository.countRowWhichHasAboveTwoCountPerHost(hostId);
	}

	private Long bringVisitorsAboveThreeTimesVisited(Long hostId) {
		return visitRepository.countRowWhichHasAboveThreeCountPerHost(hostId);
	}
}
