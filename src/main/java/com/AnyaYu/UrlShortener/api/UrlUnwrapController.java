package com.AnyaYu.UrlShortener.api;

import com.AnyaYu.UrlShortener.exception.UrlException;
import com.AnyaYu.UrlShortener.model.Response;
import com.AnyaYu.UrlShortener.model.ShortUrl;
import com.AnyaYu.UrlShortener.model.WrapRequest;
import com.AnyaYu.UrlShortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/unwrap")
@RestController
public class UrlUnwrapController {

    private final UrlService urlService;

    @Autowired
    public UrlUnwrapController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<Object> unwrapUrl(@RequestBody WrapRequest shortUrl) {
        try {
            String longUrl = urlService.getRedirect(new ShortUrl(urlService.getIdentifierFromUrl(shortUrl.getUrlString())));
            return new ResponseEntity<>(new Response(longUrl), HttpStatus.OK);
        } catch (UrlException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getLongUrl(@RequestParam String shortUrl) {
        try {
            String longUrl = urlService.getRedirect(new ShortUrl(urlService.getIdentifierFromUrl(shortUrl)));
            return new ResponseEntity<>(new Response(longUrl), HttpStatus.OK);
        } catch (UrlException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
