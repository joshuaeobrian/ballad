package com.tiy.ballad.repository;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.User;

import java.util.List;

/**
 * Created by josh on 4/28/17.
 */
public interface BalladRepository {
    void saveNewBallad(Ballad ballad);
    void updateBallad(Ballad ballad);
    void deleteBallad(Ballad ballad);
    List<Ballad> showMyBallads(User user);
    List<Ballad> showPublicBallads();
    List<Ballad> showCollaborating();
}
