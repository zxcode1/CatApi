package org.example.test.controllers;

import lombok.AllArgsConstructor;
import org.example.test.entity.CatDTO;
import org.example.test.services.CatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
public class KitController {

    private final CatService catService;

    @PostMapping("/reg")
    public String register(@RequestBody Map<String, String> regRequest) {
        catService.register(
                regRequest.get("username"),
                regRequest.get("password")
        );
        return "hahahaha";
    }

    @PostMapping("/auth")
    public String authenticate(@RequestBody Map<String, String> authRequest) {
        return catService.authenticate(
                authRequest.get("username"),
                authRequest.get("password")
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CatDTO> getUserById(@PathVariable Long id) {
        return catService.getById(id);
    }


    @GetMapping("/info")
    public String info() {
        return "/secure - Проверка срока жизни токена\n" + "/secure - Проверка срока жизни токена";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

}