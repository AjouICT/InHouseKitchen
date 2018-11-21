package com.ajouict.inhousekitchen.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class MenuImage {
    @NotNull
    private String imgName;
    private String originalImgName;

    public MenuImage(){};

    public MenuImage(String imgName){
        this.imgName = imgName;
        this.originalImgName = imgName + UUID.randomUUID();
    }

}
