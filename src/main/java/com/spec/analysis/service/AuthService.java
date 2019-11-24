package com.spec.analysis.service;

import com.spec.analysis.dto.request.LoginRequest;
import com.spec.analysis.dto.request.RegisterRequest;
import com.spec.analysis.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest);

}
