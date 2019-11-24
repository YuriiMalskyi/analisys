package com.spec.analysis.service.impl;

import com.spec.analysis.dto.request.LoginRequest;
import com.spec.analysis.dto.request.RegisterRequest;
import com.spec.analysis.dto.response.AuthResponse;
import com.spec.analysis.entity.User;
import com.spec.analysis.exceptions.UserNotFoundException;
import com.spec.analysis.exceptions.UsernameAlreadyTakenException;
import com.spec.analysis.repository.UserRepository;
import com.spec.analysis.service.AuthService;
import com.spec.analysis.utils.mapper.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        AuthResponse authResponse = null;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {
                authResponse = mapper.map(user, AuthResponse.class);
            }
        } else {
            throw new UserNotFoundException(loginRequest.getUsername());
        }
        return authResponse;
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        if (!userRepository.existsByUsername(registerRequest.getUsername())) {
            User user = mapper.map(registerRequest, User.class);
//            user.setIsStudent(registerRequest.getIsStudent());
            System.out.println("Registering new user:\n" + user.toString());
            userRepository.save(user);
            return AuthResponse.builder().id(user.getId()).username(user.getUsername()).build();
        } else {
            throw new UsernameAlreadyTakenException(registerRequest.getUsername());
        }
    }

}
