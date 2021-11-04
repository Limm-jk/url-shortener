package com.limm.urlshortener.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UrlCheckerTest {

    @Autowired
    private UrlChecker urlChecker;

    @Test
    @DisplayName("urlValidate를 통해서 url이 옳은지 확인할 수 있다.")
    void UrlValidatorTest() {
        // given
        String givenURL = "https://smilegate.com";

        // when
        boolean actual = urlChecker.urlValidate(givenURL);

        // then
        assertTrue(actual);
    }

    @Test
    @DisplayName("urlValidate를 통해서 url이 옳지 않은지 확인할 수 있다.")
    void UrlValidatorFalseTest() {
        // given
        String givenURL = "https://https://smilegate.com";

        // when
        boolean actual = urlChecker.urlValidate(givenURL);

        // then
        assertFalse(actual);
    }

    @Test
    @DisplayName("HttpChecker를 통해서 http 프로토콜이 안붙어있는 url에 붙여줄 수 있다.")
    void HttpCheckerTest() {
        // given
        String givenURL = "smilegate.com";
        String expected = "http://smilegate.com";

        // when
        String actual = urlChecker.httpChecker(givenURL);

        // then
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("HttpChecker를 통해서 http 프로토콜이 붙어있는 url은 그냥 반환한다.")
    void HttpCheckerTest2() {
        // given
        String givenURL = "https://smilegate.com";
        String expected = "https://smilegate.com";

        // when
        String actual = urlChecker.httpChecker(givenURL);

        // then
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("shortUrlValidate를 통해서 입력받은 short url이 valid한지 확인한다.")
    void shortUrlValidateTest() {
        // given
        String givenURL = "HVe8w";

        // when
        boolean actual = urlChecker.shortUrlValidate(givenURL);

        // then
        assertTrue(actual);
    }

    @Test
    @DisplayName("shortUrlValidate를 통해서 입력받은 short url에 옳지 않은 문자가 포함되어있다면 false를 반환한다.")
    void shortUrlValidateTest2() {
        // given
        String givenURL = "HVe8w!";

        // when
        boolean actual = urlChecker.shortUrlValidate(givenURL);

        // then
        assertFalse(actual);
    }

    @Test
    @DisplayName("shortUrlValidate를 통해서 입력받은 short url이 너무 길다면 false를 반환한다.")
    void shortUrlValidateTest3() {
        // given
        String givenURL = "HVe8wH3caR";

        // when
        boolean actual = urlChecker.shortUrlValidate(givenURL);

        // then
        assertFalse(actual);
    }
}
