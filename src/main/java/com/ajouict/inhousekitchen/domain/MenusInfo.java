package com.ajouict.inhousekitchen.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Getter
@Builder
@Embeddable
public class MenusInfo {
    private String name;

    private double price;

    @Lob
    private String description;

    public MenusInfo(){}
    public MenusInfo(String name, double price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
