package com.droo.urlshortener.repositiory;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class UrlShortenerRepository {

    private final static HashMap<String, String> DATABASE = new HashMap<>();

    public String findUrlByKey(String key) {
        return DATABASE.get(key);
    }

    public String findKeyByUrl(String url) {
        for (Map.Entry<String, String> entry : DATABASE.entrySet()) {
            if (Objects.equals(url, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void saveUrl(String key, String value) {
        DATABASE.put(key, value);
    }

}
