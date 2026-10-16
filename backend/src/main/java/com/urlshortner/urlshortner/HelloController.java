package com.urlshortner.urlshortner;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/urlshortner")
    public String hello() {
        return "Url shortner is alive";
    }
}
