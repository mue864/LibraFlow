package com.sikavo.libraflow.user.repository;

import com.sikavo.libraflow.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findUserByUserName(String userName);
    Optional<User> findUserByLibraryCode(String libraryCode);
    boolean isUserActive(String userName);
}
