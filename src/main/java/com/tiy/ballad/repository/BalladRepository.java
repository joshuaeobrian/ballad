package com.tiy.ballad.repository;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.User;

import java.util.List;

/**
 * Created by josh on 4/28/17.
 */
public interface BalladRepository {
    Integer saveNewBallad(Ballad ballad);
    void updateBallad(Ballad ballad,User user);
    void deleteBallad(Integer balladId);
    List<Ballad> showMyBallads(User user);
    List<Ballad> showPublicBallads();
    List<Ballad> showCollaborating();
    Ballad findBalladById(Integer balladId);
    List<Ballad> sortByMostRecent();
    List<Ballad> sortByAlphabetical(boolean isAz);
    List<Ballad> sortByPopular();

    List<Ballad> getTopThreePopular();
}
