package com.urlshortner.urlshortner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UrlController {
    private final UrlService urlService;
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    record ShortenRequest(String url) {}

    @PostMapping("/api/shorten")
    public UrlMapping shorten(@RequestBody ShortenRequest request) {
        return urlService.shorten(request.url());
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        return urlService.getLongUrl(shortCode)
                .map(url -> ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create(url))
                        .<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/urls")
     public List<UrlMapping> getAllUrls() {
        return urlService.getAllUrls();
    }
}
