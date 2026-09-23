package com.sikavo.libraflow.user.controller;

import com.sikavo.libraflow.user.dto.UserRequest;
import com.sikavo.libraflow.user.dto.UserResponse;
import com.sikavo.libraflow.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(
        name = "Users",
        description = "Endpoints for managing users"
)
@RestController
@Controller("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Create a user",
            description = "Creates a user"
    )
    @PostMapping
    public ResponseEntity<UserResponse> createUser (UserRequest userRequest) {
        log.info("Initiate creating a user");
        UserResponse response = userService.createUser(userRequest);
        return ResponseEntity.ok(response);
    }
}
