package com.spec.analysis.service.impl;

import com.spec.analysis.dto.request.LoginRequest;
import com.spec.analysis.dto.request.RegisterRequest;
import com.spec.analysis.entity.User;
import com.spec.analysis.enums.Authorities;
import com.spec.analysis.exceptions.UsernameAlreadyTakenException;
import com.spec.analysis.repository.UserRepository;
import com.spec.analysis.service.AuthService;
import com.spec.analysis.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    //TODO implement
    @Override
    public String logIn(LoginRequest loginRequest) {
        return null;
    }

    //TODO implement
    @Override
    public String signUp(RegisterRequest registerRequest) {
        if(!userRepository.existsByUsername(registerRequest.getUsername())){
            User user = userMapper.map(registerRequest, User.class);
            user.setAuthority(registerRequest.getIsStudent() ? Authorities.ROLE_STUDENT : Authorities.ROLE_LECTURER);
            userRepository.save(user);
            return "ok";
        } else {
            throw new UsernameAlreadyTakenException(registerRequest.getUsername());
        }
    }

}
