package com.limm.urlshortener.util;

import com.limm.urlshortener.util.UrlUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UrlUtilsTest {

    @Autowired
    private UrlUtils utils;

    @Test
    @DisplayName("encoding 함수는 long타입의 수를 62진수의 형식으로 encode할 수 있다.")
    void encodingTest() {
        // given
        long origin = 12345L;
        String expected = "7D3";

        // when
        String actual = utils.encoding(origin);

        //then
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("decoding 함수는 62진수 형식의 문자열을 long타입 형식으로 decode할 수 있다.")
    void decodingTest() {
        // given
        String origin = "7D3";
        long expected = 12345L;

        // when
        long actual = utils.decoding(origin);

        //then
        assertEquals(actual, expected);
    }
}
