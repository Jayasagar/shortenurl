package com.restutils.shortenurl.service;

import com.restutils.shortenurl.ShortenURL;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShortenUrlService {

    private Map<String, String> tinyUrls = new HashMap<>();

    public String generateUrl(String url) {
        // TODO: Check whether url already exist
        int uniqueId = Instant.now().getNano();
        String tinyUrlValue = ShortenURL.encode(uniqueId);
        tinyUrls.put(tinyUrlValue, url);
        return tinyUrlValue;
    }

    public String getUrl(String urlId) {
        return tinyUrls.get(urlId);
    }

    public boolean delete(String urlId) {
        String removed = tinyUrls.remove(urlId);
        return removed !=null ? true : false;
    }
}
