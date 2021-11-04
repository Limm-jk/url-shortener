package com.limm.urlshortener.util;

import org.springframework.stereotype.Component;

@Component
public class UrlUtils {
    private final int PREFIX_VALUE = 238327;
    private final int RADIX = 62;
    private final String CODEC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String encoding(long id) {
        long targetNum = id + PREFIX_VALUE;

        StringBuilder sb = new StringBuilder();
        while(targetNum > 0) {
            sb.append(CODEC.charAt((int) (targetNum % RADIX)));
            targetNum /= RADIX;
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

        return sum - PREFIX_VALUE;
    }
}
