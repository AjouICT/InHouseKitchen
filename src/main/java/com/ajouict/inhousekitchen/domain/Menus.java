package com.ajouict.inhousekitchen.domain;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class Menus {
    private String name;
    private double price;
    @Lob
    private String introduction;

    public Menus(){}
    public Menus(String name, double price, String introduction){
        this.name = name;
        this.price = price;
        this.introduction = introduction;
    }
}
