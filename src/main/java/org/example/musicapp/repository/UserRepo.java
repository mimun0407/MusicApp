package org.example.musicapp.repository;

import org.example.musicapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findAllByUserNameAndPasswordIs(String name,String password);
    List<User>findAllByUserNameIs(String name);
}
