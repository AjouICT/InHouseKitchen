package com.ajouict.inhousekitchen.util;

import com.google.common.io.Files;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class UUIDGenerator {

    public static String generateUniqueFileName(String fileName){
        return Files.getNameWithoutExtension(fileName) + UUID.randomUUID() + "." + Files.getFileExtension(fileName);
    }

}
