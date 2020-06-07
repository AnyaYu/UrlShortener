package com.AnyaYu.UrlShortener.api;

import com.AnyaYu.UrlShortener.exception.UrlException;
import com.AnyaYu.UrlShortener.model.Response;
import com.AnyaYu.UrlShortener.model.WrapRequest;
import com.AnyaYu.UrlShortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/wrap")
@RestController
public class UrlWrapController {

    private final UrlService urlService;

    @Autowired
    public UrlWrapController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<Object> addUrl(@RequestBody WrapRequest longUrl) {
        try {
            String shortIdentifier = urlService.addUrl(longUrl.getUrlString());
            String shortUrl = urlService.buildUrlFromIdentifier(shortIdentifier);
            return new ResponseEntity<>(new Response(shortUrl), HttpStatus.CREATED);
        } catch (UrlException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getShortUrl(@RequestParam String longUrl) {
        try {
            return new ResponseEntity<>(new Response(urlService.getShortUrlByLongUrl(longUrl)), HttpStatus.OK);
        } catch (UrlException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
