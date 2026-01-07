package com.rushi.urlShortener.controllers;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/health")
    public String health(){
        return "Backend Server is live";
    }
}
