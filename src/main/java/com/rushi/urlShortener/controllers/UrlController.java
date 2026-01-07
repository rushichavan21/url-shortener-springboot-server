package com.rushi.urlShortener.controllers;

import com.rushi.urlShortener.dto.response.ShortUrlResponse;
import com.rushi.urlShortener.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/url/")
public class UrlController{
@Autowired
    private UrlService urlService;
@GetMapping("/generate")
    public ResponseEntity<ShortUrlResponse> generateWithoutAuth(){
    return ResponseEntity.ok(new ShortUrlResponse(urlService.generateId(6)));
}
}
