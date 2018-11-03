package com.ajouict.inhousekitchen.domain;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class Menus {
    private String name;
    private double price;
    @Lob
    private String description;

    public Menus(){}
    public Menus(String name, double price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
