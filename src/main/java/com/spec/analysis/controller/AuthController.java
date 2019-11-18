package com.spec.analysis.controller;

import com.spec.analysis.dto.request.LoginRequest;
import com.spec.analysis.dto.request.RegisterRequest;
import com.spec.analysis.dto.response.StringObjectResponse;
import com.spec.analysis.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.signUp(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new StringObjectResponse(authService.logIn(loginRequest)));
    }

}
