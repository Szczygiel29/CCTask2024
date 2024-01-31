package com.codecool.cctask2024.exception;

public class NoPrivilegesException extends RuntimeException {

    private static final String NO_PRIVILEGES = "No permission to perform the operation";;

    public NoPrivilegesException() {
        super(NO_PRIVILEGES);
    }
}
