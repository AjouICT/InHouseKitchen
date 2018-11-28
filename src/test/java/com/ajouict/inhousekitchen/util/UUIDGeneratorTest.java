package com.ajouict.inhousekitchen.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UUIDGeneratorTest {
    private static final Logger log = LoggerFactory.getLogger(UUIDGenerator.class);


    @Test
    public void CREATE_UNIQUE_FILE_NAME() {
        log.debug("unique file name:{}", UUIDGenerator.generateUniqueFileName("filename.png"));
    }
}