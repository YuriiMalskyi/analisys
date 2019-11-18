package com.spec.analysis.service;

import com.spec.analysis.dto.request.LoginRequest;
import com.spec.analysis.dto.request.RegisterRequest;

public interface AuthService {

    String logIn(LoginRequest loginRequest);

    String signUp(RegisterRequest registerRequest);

}
