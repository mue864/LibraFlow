package com.sikavo.libraflow.user.service.impl;


import com.sikavo.libraflow.user.dto.UserRequest;
import com.sikavo.libraflow.user.dto.UserResponse;
import com.sikavo.libraflow.user.mapper.UserMapper;
import com.sikavo.libraflow.user.model.User;
import com.sikavo.libraflow.user.repository.UserRepository;
import com.sikavo.libraflow.user.service.UserService;
import org.springframework.stereotype.Component;
import com.sikavo.libraflow.library.model.Library;

import java.util.UUID;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl (UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        Library library = new Library();

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse blockUser(UUID userId) {
        return null;
    }
}
