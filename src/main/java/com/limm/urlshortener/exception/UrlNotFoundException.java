package com.limm.urlshortener.exception;

public class UrlNotFoundException  extends RuntimeException {
    private static final String USER_NOT_FOUND_EXCEPTION_MSG = "등록된 url이 아닙니다.";

    public UrlNotFoundException() {
        super(USER_NOT_FOUND_EXCEPTION_MSG);
    }
}
