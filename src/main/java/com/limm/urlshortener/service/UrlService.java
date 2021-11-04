package com.limm.urlshortener.service;

import com.limm.urlshortener.entity.Urls;
import com.limm.urlshortener.exception.InvalidShortUrlException;
import com.limm.urlshortener.exception.InvalidUrlException;
import com.limm.urlshortener.exception.UrlNotFoundException;
import com.limm.urlshortener.repository.UrlRepository;
import com.limm.urlshortener.util.UrlChecker;
import com.limm.urlshortener.util.UrlUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UrlService {

    @Value("${service.host}")
    private String shortenHost;

    private final UrlRepository urlRepository;
    private final UrlUtils urlUtils;
    private final UrlChecker urlChecker;

    public String createShortURL(String url) {
        if (!urlChecker.urlValidate(url)) throw new InvalidUrlException();

        Urls savedUrl = saveUrl(urlChecker.httpChecker(url));
        String shortUrl = urlUtils.encoding(savedUrl.getId());

        log.info(shortenHost);

        return shortenHost + shortUrl;
    }

    public Urls saveUrl(String url) {
        Urls shortUrl = new Urls(url);

        return urlRepository.save(shortUrl);
    }

    public String convertShortUrlToRealUrl(String shortUrl) {
        if (!urlChecker.shortUrlValidate(shortUrl)) throw new InvalidShortUrlException();

        long urlId = urlUtils.decoding(shortUrl);

        return findUrl(urlId).getUrl();
    }

    private Urls findUrl(long id) {
        return urlRepository.findById(id).orElseThrow(UrlNotFoundException::new);
    }
}
