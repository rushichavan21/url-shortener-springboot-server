package com.rushi.urlShortener.controllers;

import com.rushi.urlShortener.entity.ShortUrl;
import com.rushi.urlShortener.repository.ShortUrlRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestController
public class RedirectController {

    private final ShortUrlRepository shortUrlRepository;

    public RedirectController(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }


    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {

        ShortUrl url = shortUrlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Short URL not found"
                        )
                );

        url.setClicks(url.getClicks() + 1);
        shortUrlRepository.save(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getOriginalUrl()));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }


}
