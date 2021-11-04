package com.limm.urlshortener.service;

import com.limm.urlshortener.entity.Urls;
import com.limm.urlshortener.exception.InvalidShortUrlException;
import com.limm.urlshortener.exception.UrlNotFoundException;
import com.limm.urlshortener.repository.UrlRepository;
import com.limm.urlshortener.util.UrlUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UrlServiceTest {

    @Value("${service.host}")
    private String shortenHost;

    @Autowired
    private UrlService urlService;

    @Autowired
    private UrlUtils urlUtils;

    @Test
    @DisplayName("createShortUrl은 ShortUrl을 생성할 수 있다.")
    void createShortUrlTest() {
        // given
        String givenUrl = "www.smilegate.com";
        String expected = shortenHost + "0001";

        // when
        String actual = urlService.createShortURL(givenUrl);

        // then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("createShortUrl은 받은 url이 이미 존재한다면 같은 ShortUrl을 반환할 수 있다.")
    void createShortUrlTest2() {
        // given
        String givenUrl = "https://www.smilegate.com";
        Urls savedUrl = urlService.saveUrl(givenUrl);
        String expected = shortenHost + urlUtils.encoding(savedUrl.getId());

        // when
        String actual = urlService.createShortURL(givenUrl);

        // then
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("convertShortUrl은 받은 short Url을 원래 url로 바꿀 수 있다.")
    void convertShortUrlTest() {
        // given
        String givenUrl = "www.smilegate.com";
        Urls savedUrl = urlService.saveUrl(givenUrl);
        String shortUrl = urlUtils.encoding(savedUrl.getId());

        // when
        String actual = urlService.convertShortUrlToRealUrl(shortUrl);

        // then
        assertEquals(givenUrl, actual);
    }

    @Test
    @DisplayName("convertShortUrl은 받은 short Url이 존재하지 않는다면 exception을 반환한다.")
    void convertShortUrlTest2() {
        // given
        String givenUrl = "12456";

        // then
        assertThrows(UrlNotFoundException.class, () -> urlService.convertShortUrlToRealUrl(givenUrl));
    }

    @Test
    @DisplayName("convertShortUrl은 받은 short Url이 옳은 형식이 아니라면 exception을 반환한다.")
    void convertShortUrlTest3() {
        // given
        String givenUrl = "1";

        // then
        assertThrows(InvalidShortUrlException.class, () -> urlService.convertShortUrlToRealUrl(givenUrl));
    }
}
