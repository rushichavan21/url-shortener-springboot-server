package com.rushi.urlShortener.dto.request;

import lombok.Data;

@Data
public class ShortUrlRequest {
    private String originalUrl;
    private String customCode;
}
