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
    private String originalImgName;
    private String uniqueImgName;

    public MenuImage(){};

    public MenuImage(String originalImgName, String uniqueImgName){
        this.originalImgName = originalImgName;
        this.uniqueImgName = uniqueImgName;
    }

}
