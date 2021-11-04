package com.limm.urlshortener.util;

import org.springframework.stereotype.Component;

@Component
public class UrlChecker {

    public boolean urlValidate(String url) {
//        String fixedUrl = httpChecker(url);
        // TODO : 다양한 경우의 url을 커버하는 정규식
        return true;
    }

    public String httpChecker(String url) {
        if (!(url.startsWith("https://") || url.startsWith("http://")))
            return "http://" + url;
        return url;
    }

    public boolean shortUrlValidate(String shortUrl) {
        return shortUrl.matches("[0-9A-Za-z]{4,8}+");
    }
}
