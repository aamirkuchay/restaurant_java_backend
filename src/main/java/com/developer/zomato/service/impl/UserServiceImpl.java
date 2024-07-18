package com.developer.zomato.service.impl;

import com.developer.zomato.config.JwtProvider;
import com.developer.zomato.entity.User;
import com.developer.zomato.repository.UserRepository;
import com.developer.zomato.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = findUserByEmail(email);
        return user;
    }

    @Cacheable(value = "user", key = "email")
    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new Exception("User not found with given email"+ email);
        }
        return user;
    }
}
