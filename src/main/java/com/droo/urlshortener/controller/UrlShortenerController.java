package com.droo.urlshortener.controller;

import com.droo.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;


@Controller
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    @Autowired
    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("{key}")
    public ResponseEntity<String> redirect(@PathVariable String key) {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlShortenerService.decode(key))).build();
    }

    @PostMapping("/encode")
    public ResponseEntity<String> encode(@RequestParam String url) {
        return ResponseEntity.ok(urlShortenerService.encode(url));
    }

}
