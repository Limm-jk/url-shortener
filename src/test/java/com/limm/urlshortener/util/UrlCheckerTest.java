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
}
