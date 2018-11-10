package com.ajouict.inhousekitchen.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class VisitRate {
    private double second;
    private double third;

    public VisitRate(){}

    public VisitRate(double second, double third){
        this.second = second;
        this.third = third;
    }
}
