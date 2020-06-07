package com.AnyaYu.UrlShortener.validator;

import com.AnyaYu.UrlShortener.exception.UrlException;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class Validator {

    public void validateUrl(String url) {
        try {
            URL url1 = new URL(url);
        } catch (MalformedURLException e) {
            throw new UrlException("Not valid url");
        }
    }

    public void validateShortUrl(String shortUrl, String host) {
        if (!shortUrl.startsWith(host)) {
            throw new UrlException("Invalid short url");
        }
    }

}
