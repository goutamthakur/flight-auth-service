package com.goutamthakur.flight.auth.api.v1.controller;

import com.goutamthakur.flight.auth.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1")
public class InfoController {

    @GetMapping("/info")
    public ResponseEntity<ApiResponse<String>> info(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("v1 apis are live"));
    }
}
