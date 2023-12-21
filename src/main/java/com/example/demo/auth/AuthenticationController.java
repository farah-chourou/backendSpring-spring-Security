package com.example.demo.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor


public class AuthenticationController {

private final AuthenticationService service;


@PostMapping("/register")

public ResponseEntity<AuthenticationResponse>register(@RequestBody RegisterRequest request){

    return ResponseEntity.ok(service.register(request));

}
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>authenticate
            (@RequestBody AuthentiquationRequest request){

return ResponseEntity.ok(service.authenticate(request));
    }



    @GetMapping
    public ResponseEntity<String> SayHello(){
        return ResponseEntity.ok("hello");
    }

}
