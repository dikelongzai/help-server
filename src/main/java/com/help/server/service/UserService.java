package com.help.server.service;


import com.help.server.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
