package com.ajouict.inhousekitchen.domain;

import javax.persistence.Embeddable;

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
