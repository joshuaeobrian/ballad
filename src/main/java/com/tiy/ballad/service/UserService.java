package com.tiy.ballad.service;

import com.tiy.ballad.model.User;
import com.tiy.ballad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by josh on 4/28/17.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User login(String  username,String password){
        return repository.getUserByLoginAndPassword(username,password);
    }

    public User findUserById(Integer id){
        return repository.findUserById(id);
    }

    public Integer saveNewUser(User user) {
        return repository.saveNewUser(user);
    }

    public void updateUserInfo(User user){
        repository.updateUserInfo(user);
    }
    public void deleteUser(Integer userId){
        repository.deleteUser(userId);
    }
}
