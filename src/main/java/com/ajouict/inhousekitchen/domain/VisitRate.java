package com.ajouict.inhousekitchen.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class VisitRate {
    @Column(nullable = true)
    private double second = 0.0;

    @Column(nullable = true)
    private double third = 0.0;

    public VisitRate(){}

    public VisitRate(double second, double third){
        this.second = second;
        this.third = third;
    }
}
