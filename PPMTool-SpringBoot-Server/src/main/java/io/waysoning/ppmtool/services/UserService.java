package io.waysoning.ppmtool.services;

import io.waysoning.ppmtool.domain.User;
import io.waysoning.ppmtool.exceptions.UsernameAlreadyExistsException;
import io.waysoning.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User user) {
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            // Username is unique
            user.setUsername(user.getUsername());
            user.setConfirmPassword("");

            return userRepository.save(user);
        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '" + user.getUsername() + "' already exists");
        }
    }
}
