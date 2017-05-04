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

    public Integer saveNewBallad(Ballad ballad){
        return repository.saveNewBallad(ballad);
    }

    public List<Ballad> showMyBallads(User user){
        return repository.showMyBallads(user);
    }

    public Ballad findBalladById(Integer balladId) {
        return repository.findBalladById(balladId);
    }

    public void updateBallad(Ballad ballad,User user) {
        repository.updateBallad(ballad,user);
    }

    public void deleteBallad(Integer balladId){
        repository.deleteBallad(balladId);
    }

    public List<Ballad> getTopThreePopular() {
        return repository.getTopThreePopular();
    }

    public List<Ballad> getPopularBallads() {
        return repository.getPopularBallads();
    }

    public List<Ballad> sortBallads(Boolean userOnly,Integer userId,Boolean isPublic, Boolean isPrivate, Integer caseId) {
        return repository.sortBallads( userOnly, userId, isPublic,  isPrivate,  caseId);
    }
}
