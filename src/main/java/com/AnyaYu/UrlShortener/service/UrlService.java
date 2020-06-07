package com.AnyaYu.UrlShortener.service;

import com.AnyaYu.UrlShortener.dao.DataAccess;
import com.AnyaYu.UrlShortener.exception.UrlException;
import com.AnyaYu.UrlShortener.validator.Validator;
import com.AnyaYu.UrlShortener.model.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    //public static final String HOST = "localhost:8081/s/";

    @Value("${application.server}")
    private String host;

    private DataAccess dataAccess;

    @Autowired
    private Validator validator;

    @Autowired
    public UrlService(@Qualifier("UrlDataAccess") DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public String addUrl(String longUrl) {
        validator.validateUrl(longUrl);
        ShortUrl shortUrl = new ShortUrl();
        dataAccess.insertNewUrl(longUrl, shortUrl);
        return shortUrl.getStringId();
    }

    public String getShortUrlByLongUrl(String longUrl) {
        validator.validateUrl(longUrl);
        ShortUrl shortUrl = dataAccess.findShortUrlByLongUrl(longUrl);
        if (shortUrl == null) {
            throw new UrlException("Url not found");
        }
        return buildUrlFromIdentifier(shortUrl.getStringId());
    }

    public String buildUrlFromIdentifier(String stringId) {
        return host + stringId;
    }

    public String getRedirect(ShortUrl shortUrl) {
        String redirect = dataAccess.getRedirect(shortUrl);
        if (redirect == null) {
            throw new UrlException("Url not found");
        }
        return redirect;
    }

    public String getIdentifierFromUrl(String urlString) {
        validator.validateShortUrl(urlString, host);
        return urlString.replaceAll(host, "");
    }
}
