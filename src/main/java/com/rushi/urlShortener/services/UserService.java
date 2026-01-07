package com.rushi.urlShortener.services;

import com.mongodb.DuplicateKeyException;
import com.rushi.urlShortener.entity.User;
import com.rushi.urlShortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void saveUser(User user){
        userRepository.save(user);
    }
    public void saveAuthUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Email already in use");
        }
    }
}
