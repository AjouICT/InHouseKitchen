package com.ajouict.inhousekitchen.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VisitRepository extends JpaRepository<Visit, VisitId> {

	@Query(
			value = "SELECT count(*) from Visit WHERE host_id = ?1",
			nativeQuery = true
	)
	Long countRowPerHost(Long hostId);

	@Query(
			value = "SELECT count(*) from Visit WHERE host_id = ?1 AND count >= 2",
			nativeQuery = true
	)
	Long countRowWhichHasAboveTwoCountPerHost(Long hostId);

	@Query(
			value = "SELECT count(*) from Visit WHERE host_id = ?1 AND count >= 3",
			nativeQuery = true
	)
	Long countRowWhichHasAboveThreeCountPerHost(Long hostId);

}
