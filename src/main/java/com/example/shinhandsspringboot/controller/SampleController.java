package com.example.shinhandsspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
    @GetMapping("/home")
    public String home() {
        return "fuck";
    }
}
