package org.example.test.apihandlers.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.example.test.apihandlers.auth.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
//        String token = authService.authenticateUser(loginRequest);
//        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
//        authService.registerUser(signUpRequest);
//        return ResponseEntity.ok().build();
//    }


    @PostMapping("/auth")
    public String authenticate(@RequestBody Map<String, String> authRequest) {
        return authService.authenticate(authRequest);
    }
}

