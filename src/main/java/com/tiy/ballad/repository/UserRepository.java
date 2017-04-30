package com.tiy.ballad.repository;

import com.tiy.ballad.model.User;

import java.util.List;

/**
 * Created by josh on 4/28/17.
 */
public interface UserRepository {

    Integer saveNewUser(User user);
    void updateUserInfo(User user);
    void deleteUser(User user);
    User findUserById(Integer id);
    User findUserByName(String name);
    User getUserByLoginAndPassword(String usernameOrEmail,String password);
    List<User> listAllUsers();
    void startUserSession(User user,String sessionId);

    void updateSession(User user, String jsessionid);
}
