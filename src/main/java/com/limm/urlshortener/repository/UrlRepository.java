package com.limm.urlshortener.repository;

import com.limm.urlshortener.entity.Urls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Urls, Long> {
}
