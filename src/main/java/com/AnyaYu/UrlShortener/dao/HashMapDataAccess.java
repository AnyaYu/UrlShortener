package com.AnyaYu.UrlShortener.dao;

import com.AnyaYu.UrlShortener.model.ShortUrl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("UrlDataAccess")
public class HashMapDataAccess implements DataAccess {

    private static final Map<ShortUrl, String> stringIdToLongUrl = new HashMap<>();
    private static final Map<String, ShortUrl> longUrlToStringId = new HashMap<>();

    @Override
    public void insertNewUrl(String longUrl, ShortUrl shortUrl) {
        longUrlToStringId.put(longUrl, shortUrl);
        stringIdToLongUrl.put(shortUrl, longUrl);
    }

    @Override
    public ShortUrl findShortUrlByLongUrl(String longUrl) {
        return longUrlToStringId.get(longUrl);
    }

    @Override
    public String getRedirect(ShortUrl shortUrl) {
        return stringIdToLongUrl.get(shortUrl);
    }

}
