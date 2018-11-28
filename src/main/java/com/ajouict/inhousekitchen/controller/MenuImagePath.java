package com.ajouict.inhousekitchen.controller;

import lombok.Getter;

import java.nio.file.Path;

@Getter
class MenuImagePath {
    private Path path;
    MenuImagePath(Path path){
        this.path = path;
    }
}
