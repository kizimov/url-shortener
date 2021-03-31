package com.droo.urlshortener.service;

import com.droo.urlshortener.repositiory.UrlShortenerRepository;
import com.droo.urlshortener.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

    final static String BASE_URL = "http://localhost:8080/";

    private final UrlShortenerRepository urlShortenerRepository;

    @Autowired
    public UrlShortenerService(UrlShortenerRepository urlShortenerRepository) {
        this.urlShortenerRepository = urlShortenerRepository;
    }

    public String decode(String key) {
        return urlShortenerRepository.findUrlByKey(key);
    }

    public String encode(String url) {
        final String savedUrlKey = urlShortenerRepository.findKeyByUrl(url);
        if (savedUrlKey == null) {
            final String randomizer = new Randomizer().nextString();
            urlShortenerRepository.saveUrl(randomizer, url);
            return BASE_URL + randomizer;
        } else {
            return BASE_URL + savedUrlKey;
        }
    }

}
