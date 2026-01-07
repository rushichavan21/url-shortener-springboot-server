package com.rushi.urlShortener.services;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.springframework.stereotype.Component;

import static com.aventrix.jnanoid.jnanoid.NanoIdUtils.DEFAULT_ALPHABET;
import static com.aventrix.jnanoid.jnanoid.NanoIdUtils.DEFAULT_NUMBER_GENERATOR;

@Component
public class UrlService {
    public String generateId(Integer length){
        return NanoIdUtils.randomNanoId(DEFAULT_NUMBER_GENERATOR, DEFAULT_ALPHABET, 6);
    }
}
