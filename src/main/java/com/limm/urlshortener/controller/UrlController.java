package com.limm.urlshortener.controller;

import com.limm.urlshortener.dto.UrlDto;
import com.limm.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping
    public String home() {
        return "index";
    }

    @PostMapping("/shorten")
    public String urlShorten(Model model, UrlDto dto) {
        String shortURL =  urlService.createShortURL(dto.getUrl());
        model.addAttribute("msg", shortURL);

        return "index :: #resultDiv";
    }

    @GetMapping("/{shortUrl}")
    public void redirectUrl(
            @PathVariable(value = "shortUrl") String shortUrl,
            HttpServletResponse response
    ) throws IOException {
        log.info("Input Short Url : " + shortUrl);
        String realUrl = urlService.convertShortUrlToRealUrl(shortUrl);
        response.sendRedirect(realUrl);
    }
}
