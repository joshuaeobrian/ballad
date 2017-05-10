package com.tiy.ballad.repository;

import com.tiy.ballad.model.User;

import java.util.List;

/**
 * Created by josh on 4/28/17.
 */
public interface UserRepository {

    Integer saveNewUser(User user);
    void updateUserInfo(User user);
    void deleteUser(Integer userId);
    User findUserById(Integer id);
    List<User> findUserByName(String name);
    User getUserByLogin(String usernameOrEmail);
    List<User> listAllUsers();



}
