package com.codecool.cctask2024.exception;

import static java.lang.String.format;

public class CCException extends RuntimeException {

    private static final String ENTITY_NOT_EXIST_FOR_ID = "Encja klasy %s dla id %s nie istnieje";
    private static final String ENTITY_NOT_EXIST = "Obiekt %s nie istnieje";

    public CCException(String message) {
        super(message);
    }

    public CCException(Class<?> clazz, Long entityId) {
        super(format(ENTITY_NOT_EXIST_FOR_ID, clazz.getName(), entityId));
    }
    public CCException(Class<?> clazz) {
        super(format(ENTITY_NOT_EXIST, clazz.getName()));
    }
}
