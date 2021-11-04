package com.limm.urlshortener.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlChecker {

    public boolean urlValidate(String url) {
        return true;
    }

    public String httpChecker(String url) {
        return url;
    }
}
