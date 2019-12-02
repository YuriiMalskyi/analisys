package com.spec.analysis.service;

import com.spec.analysis.dto.response.UserResponse;
import com.spec.analysis.entity.User;

public interface UserService {

    void saveUser(User user);

    UserResponse getById(Long id);
}
