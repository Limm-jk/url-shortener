package com.limm.urlshortener.exception;

public class InvalidShortUrlException extends RuntimeException {
    private static final String URL_VALIDATION_ERR_MSG = "Short URL이 validate하지 않습니다.";

    public InvalidShortUrlException() { super(URL_VALIDATION_ERR_MSG); }
}
