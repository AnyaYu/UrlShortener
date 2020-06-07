package com.AnyaYu.UrlShortener.api;

import com.AnyaYu.UrlShortener.exception.UrlException;
import com.AnyaYu.UrlShortener.model.ShortUrl;
import com.AnyaYu.UrlShortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/s")
@RestController
public class ShortUrlController {

    private final UrlService urlService;

    @Autowired
    public ShortUrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping(path = "/{identifier}")
    public ResponseEntity<Object> getLongUrl(@PathVariable(value = "identifier") String identifier) {
        try {
            ShortUrl shortUrl = new ShortUrl(identifier);
            String url = urlService.getRedirect(shortUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", url);
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        } catch (UrlException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
