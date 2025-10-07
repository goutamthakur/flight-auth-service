package com.goutamthakur.flight.auth.api.v1.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1")
public class InfoController {

    @GetMapping("/info")
    public String info(){
        return "OK";
    }
}
