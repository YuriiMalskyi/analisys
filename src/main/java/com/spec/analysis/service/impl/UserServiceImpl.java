package com.spec.analysis.service.impl;

import com.spec.analysis.dto.response.UserResponse;
import com.spec.analysis.entity.User;
import com.spec.analysis.repository.UserRepository;
import com.spec.analysis.service.UserService;
import com.spec.analysis.utils.mapper.ObjectMapper;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private ObjectMapper mapper;

    public UserServiceImpl(ObjectMapper mapper,
                           UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        throw new NotYetImplementedException("Too lazy to do it :(");
    }

    @Override
    public UserResponse getById(Long id) {
        return mapper.map(userRepository.findById(id).orElse(null), UserResponse.class);
    }
}
