package com.tiy.ballad.repository;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by josh on 4/28/17.
 */
@Repository
public class BalladRepositoryImpl implements BalladRepository {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void saveNewBallad(Ballad ballad) {
        template.update("WITH ballad_insert AS (\n" +
                "  INSERT INTO ballads(title, ballad)\n" +
                "  VALUES (?,?)\n" +
                "  RETURNING id\n" +
                ")\n" +
                "INSERT INTO collaborators(ballad_id, ballad_user_id,creator)\n" +
                "VALUES (\n" +
                "  (SELECT id FROM ballad_insert),\n" +
                "  ?,\n" +
                "  TRUE\n" +
                ");",new Object[]{ballad.getTitle(),ballad.getBallad(),ballad.getUserId()});

    }

    @Override
    public void updateBallad(Ballad ballad) {

    }

    @Override
    public void deleteBallad(Ballad ballad) {

    }

    @Override
    public List<Ballad> showMyBallads(User user) {
        return null;
    }

    @Override
    public List<Ballad> showPublicBallads() {
        return null;
    }

    @Override
    public List<Ballad> showCollaborating() {
        return null;
    }
}
