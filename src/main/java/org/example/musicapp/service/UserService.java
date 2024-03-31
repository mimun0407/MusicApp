package org.example.musicapp.service;

import org.example.musicapp.model.User;
import org.example.musicapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService{
    @Autowired
    UserRepo userRepo;
    @Override
    public void createUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public Optional<User> FindUserById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public User logIn(String username, String password) {
        return userRepo.findAllByUserNameAndPasswordIs(username,password);
    }

    @Override
    public List<User> findUserName(String username) {
        return userRepo.findAllByUserNameIs(username);
    }

}
