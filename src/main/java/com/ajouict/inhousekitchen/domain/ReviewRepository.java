package com.ajouict.inhousekitchen.domain;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.function.Predicate;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByWriterId(String id);
    Review findByid(Long id);

}
