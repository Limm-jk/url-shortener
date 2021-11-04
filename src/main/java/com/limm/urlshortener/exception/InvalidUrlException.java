package com.limm.urlshortener.exception;

public class InvalidUrlException extends RuntimeException {
    private static final String URL_VALIDATION_ERR_MSG = "URL이 validate하지 않습니다.";

    public InvalidUrlException() { super(URL_VALIDATION_ERR_MSG); }
}
