package org.example.test.apihandlers.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.example.test.apihandlers.auth.services.RegistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cats")
public class RegistController {
    private final RegistService registService;

    @PostMapping("/reg")
    public ResponseEntity<String> register(@RequestBody Map<String, String> regRequest) {
        registService.register(regRequest);
        return ResponseEntity.ok("Cat was registered successfully");
    }
}
