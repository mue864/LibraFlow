package com.sikavo.libraflow;

import com.sikavo.libraflow.user.model.User;
import com.sikavo.libraflow.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class LibraFlowApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void userCanBePersisted() {
        User user = new User();
        user.setUserName("Test User");
        user.setEmail("test@example.com");
        user.setUserActive(true);

        User savedUser = userRepository.saveAndFlush(user);

        Assertions.assertNotNull(savedUser.getUserId());
        Assertions.assertNotNull(savedUser.getCreatedAt());
        User persistedUser = userRepository.findById(savedUser.getUserId()).orElseThrow();
        Assertions.assertEquals("Test User", persistedUser.getUserName());
        Assertions.assertEquals("test@example.com", persistedUser.getEmail());
    }

}
