package com.limm.urlshortener.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlUtils {
    private final int RADIX = 62;
    private final String CODEC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String encoding(long param) {
        StringBuilder sb = new StringBuilder();
        while(param > 0) {
            sb.append(CODEC.charAt((int) (param % RADIX)));
            param /= RADIX;
        }
        return sb.toString();
    }

    public long decoding(String param) {
        long sum = 0;
        long power = 1;
        for (int i = 0; i < param.length(); i++) {
            sum += CODEC.indexOf(param.charAt(i)) * power;
            power *= RADIX;
        }
        return sum;
    }
}
