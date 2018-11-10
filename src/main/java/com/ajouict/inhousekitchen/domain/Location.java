package com.ajouict.inhousekitchen.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
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
