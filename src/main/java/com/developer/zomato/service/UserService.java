package com.developer.zomato.service;

import com.developer.zomato.entity.User;

public interface UserService {

    User findUserByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
}
