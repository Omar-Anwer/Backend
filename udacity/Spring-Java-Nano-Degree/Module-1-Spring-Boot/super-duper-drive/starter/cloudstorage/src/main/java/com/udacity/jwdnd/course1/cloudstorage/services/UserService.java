package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.security.HashService;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final HashService hashService;


    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public Integer createUser(User user){

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(0, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    public User getUser(String username){   

        return userMapper.findByName(username);
    }

    public boolean isUsernameAvailable(String username){ 
        boolean isAvailable = (null == userMapper.findByName(username));
        return isAvailable;
    }
    
}
