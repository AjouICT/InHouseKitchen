package com.ajouict.inhousekitchen.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Location {

    @NotNull
    private double latitude;
    @NotNull
    private double longitude;

    public Location(){}

    public Location(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
