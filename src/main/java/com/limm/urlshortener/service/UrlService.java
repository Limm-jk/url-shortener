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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        String fixedUrl = urlChecker.httpChecker(url);
        long urlId;
        Optional<Urls> findUrl;

        if ((findUrl = urlRepository.findByUrl(fixedUrl)).isPresent()) {
            urlId = findUrl.get().getId();
        } else {
            Urls savedUrl = saveUrl(fixedUrl);
            urlId = savedUrl.getId();
        }

        return shortenHost + urlUtils.encoding(urlId);
    }

    public Urls saveUrl(String url) {
        Urls shortUrl = new Urls(url);

        return urlRepository.save(shortUrl);
    }

    @Cacheable("shortUrl")
    public String convertShortUrlToRealUrl(String shortUrl) {
        if (!urlChecker.shortUrlValidate(shortUrl)) throw new InvalidShortUrlException();

        long urlId = urlUtils.decoding(shortUrl);

        return findUrl(urlId).getUrl();
    }

    private Urls findUrl(long id) {
        return urlRepository.findById(id).orElseThrow(UrlNotFoundException::new);
    }
}
