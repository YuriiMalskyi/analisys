package com.spec.analysis.controller;

import com.spec.analysis.dto.response.UserResponse;
import com.spec.analysis.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(userService.getById(userId), HttpStatus.OK);
    }

}
