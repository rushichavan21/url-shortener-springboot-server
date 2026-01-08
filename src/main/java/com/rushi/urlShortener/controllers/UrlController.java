package com.rushi.urlShortener.controllers;

import com.rushi.urlShortener.dto.request.ShortUrlRequest;
import com.rushi.urlShortener.dto.response.ShortUrlResponse;
import com.rushi.urlShortener.entity.ShortUrl;
import com.rushi.urlShortener.entity.User;
import com.rushi.urlShortener.services.UrlService;
import com.rushi.urlShortener.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
public class UrlController {

    private final UrlService urlService;
    private final UserService userService;

    @Autowired
    public UrlController(UrlService urlService, UserService userService) {
        this.urlService = urlService;
        this.userService = userService;
    }

    /**
     * Guest (no JWT required)
     */
    @PostMapping("/shorten")
    public ResponseEntity<ShortUrlResponse> shortenForGuest(
            @RequestBody ShortUrlRequest request
    ) {
        ShortUrl shortUrl = urlService.createShortUrl(
                request.getOriginalUrl(),
                null
        );

        return ResponseEntity.ok(
                new ShortUrlResponse(
                        shortUrl.getOriginalUrl(),
                        shortUrl.getShortUrl()
                )
        );
    }

    /**
     * Authenticated via JWT
     */
    @PostMapping("/shorten/auth")
    public ResponseEntity<ShortUrlResponse> shortenForUser(
            @RequestBody ShortUrlRequest request
    ) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName(); // from JWT

        User user = userService.findByEmail(email);

        ShortUrl shortUrl = urlService.createShortUrl(
                request.getOriginalUrl(),
                user
        );

        return ResponseEntity.ok(
                new ShortUrlResponse(
                        shortUrl.getOriginalUrl(),
                        shortUrl.getShortUrl()
                )
        );
    }
}
