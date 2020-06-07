package com.AnyaYu.UrlShortener.dao;

import com.AnyaYu.UrlShortener.model.ShortUrl;

public interface DataAccess {

    String getRedirect(ShortUrl shortUrl);

    ShortUrl findShortUrlByLongUrl(String longUrl);

    void insertNewUrl(String longUrl, ShortUrl hash);
}
