package com.spec.analysis.service.impl;

import com.spec.analysis.dto.response.UserResponse;
import com.spec.analysis.entity.User;
import com.spec.analysis.repository.UserRepository;
import com.spec.analysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {

    }

    @Override
    public UserResponse getById(Long id) {
        return null;
    }
}
