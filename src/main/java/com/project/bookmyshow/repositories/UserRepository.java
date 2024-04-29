package com.project.bookmyshow.repositories;

import com.project.bookmyshow.models.User;
import com.project.bookmyshow.services.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long userId);
    Optional<User> findByEmail(String email);
    User save(User user);

    Boolean existsByEmailEquals(String emailId);
}
