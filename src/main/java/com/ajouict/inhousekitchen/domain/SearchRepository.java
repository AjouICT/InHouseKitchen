package com.ajouict.inhousekitchen.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<Host, Long> {
    Host findByHost(User user);
    Host findByid(Long id);
}
