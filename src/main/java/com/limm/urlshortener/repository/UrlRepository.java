package com.limm.urlshortener.repository;

import com.limm.urlshortener.entity.Urls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Urls, Long> {
    Optional<Urls> findByUrl(String url);
}
