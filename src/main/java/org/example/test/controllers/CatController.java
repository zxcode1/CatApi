package org.example.test.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
//@ControllerAdvice
public class CatController {

//    @ExceptionHandler
//    new throw

    @GetMapping("/secure")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<String> secureEndpoint(@RequestHeader(name = "Authorization") String token) {
        System.out.println("hello");
        return ResponseEntity.ok("hell");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> adminEndpoint(@RequestHeader(name = "Authorization") String token) {
        System.out.println("Admin endpoint accessed");
        return ResponseEntity.ok("Admin endpoint");
    }

    @GetMapping("/manager")
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<String> managerEndpoint(@RequestHeader(name = "Authorization") String token) {
        System.out.println("Manager endpoint accessed");
        return ResponseEntity.ok("Manager endpoint");
    }

    @GetMapping("/employee")
    @PreAuthorize("hasRole('employee')")
    public ResponseEntity<String> employeeEndpoint(@RequestHeader(name = "Authorization") String token) {
        System.out.println("Employee endpoint accessed");
        return ResponseEntity.ok("Employee endpoint");
    }

    @GetMapping("/authenticated")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> authenticatedEndpoint(@RequestHeader(name = "Authorization") String token) {
        System.out.println("Authenticated endpoint accessed");
        return ResponseEntity.ok("Authenticated endpoint");
    }

    @GetMapping("/authenticated-user")
    @PreAuthorize("isAuthenticated() and hasRole('user')")
    public ResponseEntity<String> authenticatedUserEndpoint(@RequestHeader(name = "Authorization") String token) {
        System.out.println("Authenticated user endpoint accessed");
        return ResponseEntity.ok("Authenticated user endpoint");
    }
}