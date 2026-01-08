package com.rushi.urlShortener.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShortUrlResponse {
    private String originalUrl;
    private String shortUrl;
}
