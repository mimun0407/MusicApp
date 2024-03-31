package org.example.musicapp.service;

import org.example.musicapp.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    void createUser(User user);
    void updateUser(User user);
    Optional<User> FindUserById(int id);
    void deleteUser(int id);
    User logIn(String username,String password);
    List<User> findUserName(String username);
}
