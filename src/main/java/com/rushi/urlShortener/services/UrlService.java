package com.rushi.urlShortener.services;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.rushi.urlShortener.entity.ShortUrl;
import com.rushi.urlShortener.entity.User;
import com.rushi.urlShortener.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aventrix.jnanoid.jnanoid.NanoIdUtils.DEFAULT_ALPHABET;
import static com.aventrix.jnanoid.jnanoid.NanoIdUtils.DEFAULT_NUMBER_GENERATOR;

@Service
public class UrlService {

    private final ShortUrlRepository shortUrlRepository;

    @Autowired
    public UrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public String generateId(int length) {
        return NanoIdUtils.randomNanoId(
                DEFAULT_NUMBER_GENERATOR,
                DEFAULT_ALPHABET,
                length
        );
    }

    public ShortUrl createShortUrl(String originalUrl, User user) {
        ShortUrl url = new ShortUrl();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(generateUniqueId(6));
        url.setUser(user);
        url.setClicks(0);
        return shortUrlRepository.save(url);
    }

    private String generateUniqueId(int length) {
        String shortUrl;
        do {
            shortUrl = generateId(length);
        } while (shortUrlRepository.existsByShortUrl(shortUrl));
        return shortUrl;
    }

    public List<ShortUrl> getUrlsByUser(User user) {
        return shortUrlRepository.findByUser(user);
    }

    public ShortUrl createCustomShortUrl(String originalUrl, String customCode, User user) {

        if (customCode == null || customCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Custom code cannot be empty");
        }
        if (shortUrlRepository.existsByShortUrl(customCode)) {
            throw new RuntimeException("Custom code already taken");
        }

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(originalUrl);
        shortUrl.setShortUrl(customCode);
        shortUrl.setUser(user);

        return shortUrlRepository.save(shortUrl);
    }
}
