package com.sikavo.libraflow.user.service;

import com.sikavo.libraflow.user.dto.UserRequest;
import com.sikavo.libraflow.user.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {
    UserResponse createUser (UserRequest request);
    UserResponse blockUser (UUID userId);

}
