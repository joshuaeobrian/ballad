package com.tiy.ballad.service;

import com.tiy.ballad.model.Email;
import com.tiy.ballad.repository.EmailConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by josh on 5/14/17.
 */
@Service
public class EmailService {

    @Autowired
    private EmailConfigRepository repository;



    public Email getEmail(){
        return repository.getEmail();
    }

}
