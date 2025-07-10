package com.ednaldo.dscatalog.services.exceptions;

public class DataBaseIntegrityException extends RuntimeException{
    public DataBaseIntegrityException(String message) {
        super(message);
    }
}
