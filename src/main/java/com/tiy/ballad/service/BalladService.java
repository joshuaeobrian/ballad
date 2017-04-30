package com.tiy.ballad.service;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.User;
import com.tiy.ballad.repository.BalladRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by josh on 4/28/17.
 */
@Service
public class BalladService {
    @Autowired
    private BalladRepository repository;

    public void saveNewBallad(Ballad ballad){
        repository.saveNewBallad(ballad);
    }

    public List<Ballad> showMyBallads(User user){
        return repository.showMyBallads(user);
    }
}
