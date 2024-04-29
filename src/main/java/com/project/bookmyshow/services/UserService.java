package com.project.bookmyshow.services;


import com.project.bookmyshow.exceptions.EmailAlreadyExistsException;
import com.project.bookmyshow.models.User;
import com.project.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUpUser(String email, String password) throws EmailAlreadyExistsException {
        User user;
        Optional<User> userOptional =userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            user = new User();
            user.setEmail(email);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(password));
            User savedUser = userRepository.save(user);
            return savedUser;
        }else{
            throw new EmailAlreadyExistsException("Email already exists.!");
        }

    }
}
