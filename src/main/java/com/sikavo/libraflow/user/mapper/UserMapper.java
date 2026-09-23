package com.sikavo.libraflow.user.mapper;

import com.sikavo.libraflow.user.dto.UserRequest;
import com.sikavo.libraflow.user.dto.UserResponse;
import com.sikavo.libraflow.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public String normalizeStrings (String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }

    public User toEntity (UserRequest userRequest) {
        User user = new User();

        user.setUserName(normalizeStrings(userRequest.userName()));
        user.setEmail(normalizeStrings(userRequest.email()));
        user.setUserActive(true);
//        library code is set later on not here
//        password not set here too

        return user;
    };

    public UserResponse toResponse (User user) {
        return new UserResponse(
                user.getUserId(),
                user.getCreatedAt(),
                user.getUserName(),
                user.getEmail(),
                user.getLibraryCode(),
                user.isUserActive()
        );
    }
}
