package com.tiy.ballad.repository;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.nio.cs.US_ASCII;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by josh on 4/28/17.
 */
@Repository
public class BalladRepositoryImpl implements BalladRepository {
    @Autowired
    private JdbcTemplate template;

    @Override
    public Integer saveNewBallad(Ballad ballad) {
        //TODO: add log table and interaction for creator
        return template.queryForObject("WITH ballad_insert AS (\n" +
                        "  INSERT INTO ballads(title, ballad, creator_id)\n" +
                        "  VALUES (?,?,?)\n" +
                        "  RETURNING id, creator_id\n" +
                        "),\n" +
                        "interaction AS (\n" +
                        "    INSERT INTO ballad_interaction(ballad_id, ballad_user_id)\n" +
                        "        VALUES (\n" +
                        "          (SELECT id FROM ballad_insert),\n" +
                        "          (SELECT creator_id FROM ballad_insert)\n" +
                        "        )\n" +
                        "  )\n" +
                        "INSERT INTO ballad_logs(ballad_id, ballad_user_id, action_id, item_id)\n" +
                        "    VALUES (\n" +
                        "      (SELECT id FROM ballad_insert),\n" +
                        "      (SELECT creator_id FROM ballad_insert),\n" +
                        "      (SELECT id FROM log_roles WHERE action='Created'),\n" +
                        "      (SELECT id FROM ballad_items WHERE item_type='Ballad')\n" +
                        "    ) RETURNING ballad_id;"
                ,new Object[]{ballad.getTitle(),ballad.getBallad(),ballad.getOwner().getId()},
                (rs, i)-> rs.getInt("ballad_id"));
    }

    @Override
    public void updateBallad(Ballad ballad,User user) {
        template.update("UPDATE ballads set title=?, ballad=? WHERE id=?;" +
                        "INSERT INTO ballad_logs(ballad_id, ballad_user_id, action_id, item_id) " +
                        "VALUES(" +
                        "?," +
                        "?," +
                        "(SELECT id FROM log_roles WHERE action='Edit')," +
                        "(SELECT id FROM ballad_items WHERE item_type='Ballad')" +
                        ");",
                ballad.getTitle(),
                ballad.getBallad(),
                ballad.getId(),
                ballad.getId(),
                user.getId());
    }

    @Override
    public void deleteBallad(Integer balladId) {
        template.update("DELETE FROM ballad_interaction WHERE ballad_id = ?;\n" +
                "DELETE FROM collaborators WHERE ballad_id = ?;\n" +
                "DELETE FROM ballad_logs WHERE ballad_id = ?;\n" +
                "DELETE FROM ballads WHERE id=?;",
                balladId,
                balladId,
                balladId,
                balladId);
    }

    @Override
    public List<Ballad> showMyBallads(User user) {
        return template.query("SELECT\n" +
                        "  b.id as id, b.title as title, b.ballad as ballad, b.creation_date AS creation_date, b.public AS ispublic,\n" +
                        "  u.id as user_id, u.first_name, u.last_name, u.email, u.username, u.password,u.active,\n" +
                        "  i.favorite as favorite, i.liked as liked\n" +
                        "FROM ballads AS b\n" +
                        "  JOIN ballad_users as u on b.creator_id = u.id \n" +
                        "  JOIN ballad_interaction as i ON b.id = i.ballad_id\n" +
                        "  WHERE b.creator_id =?;",
                new Object[]{user.getId()},
                (rs,i)-> new Ballad(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("ballad"),
                        user,
                        collaborators( rs.getInt("id")),
                        LocalDate.parse(rs.getString("creation_date")),
                        null,
                        rs.getBoolean("favorite"),
                        rs.getBoolean("ispublic")
                ));

    }
    public List<User> collaborators(Integer balladId){
        try {
            return template.query("SELECT u.id AS user_id, u.first_name, u.last_name, u.email, u.username, u.password,u.active FROM collaborators AS c JOIN ballad_users AS u ON c.ballad_user_id = u.id WHERE ballad_id=?",
                    new Object[]{balladId},
                    (rs, i) -> new User(
                            rs.getInt("user_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getBoolean("active")
                    ));
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Ballad> showPublicBallads() {
       return template.query("SELECT\n" +
                       "  b.id as id, b.title as title, b.ballad as ballad, b.creation_date AS creation_date, b.public AS ispublic,\n" +
                       "  u.id as user_id, u.first_name, u.last_name, u.email, u.username, u.password,u.active,\n" +
                       "  i.favorite as favorite, i.liked as liked\n" +
                       "FROM ballads AS b\n" +
                       "  JOIN ballad_users as u on b.creator_id = u.id \n" +
                       "  JOIN ballad_interaction as i ON b.id = i.ballad_id\n" +
                       "  WHERE b.public=TRUE ;",
               (rs, i) -> new Ballad(
                       rs.getInt("id"),
                       rs.getString("title"),
                       rs.getString("ballad"),
                       new User(
                               rs.getInt("user_id"),
                               rs.getString("first_name"),
                               rs.getString("last_name"),
                               rs.getString("email"),
                               rs.getString("username"),
                               rs.getString("password"),
                               rs.getBoolean("active")
                       ),
                       collaborators( rs.getInt("id")),
                       LocalDate.parse(rs.getString("creation_date")),
                       null,
                       rs.getBoolean("favorite"),
                       rs.getBoolean("liked")
               ));
    }

    @Override
    public List<Ballad> showCollaborating() {
        return null;
    }

    @Override
    public Ballad findBalladById(Integer balladId) {
        return template.queryForObject("SELECT\n" +
                        "  b.id as id, b.title as title, b.ballad as ballad, b.creation_date AS creation_date, b.public AS ispublic,\n" +
                        "  u.id as user_id, u.first_name, u.last_name, u.email, u.username, u.password,u.active,\n" +
                        "  i.favorite as favorite, i.liked as liked\n" +
                        "FROM ballads AS b\n" +
                        "  JOIN ballad_users as u on b.creator_id = u.id \n" +
                        "  JOIN ballad_interaction as i ON b.id = i.ballad_id\n" +
                        "  WHERE b.id=?;",
                new Object[]{balladId},(rs, i) -> new Ballad(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("ballad"),
                        new User(
                                rs.getInt("user_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getBoolean("active")
                        ),
                        collaborators( rs.getInt("id")),
                        LocalDate.parse(rs.getString("creation_date")),
                        null,
                        rs.getBoolean("favorite"),
                        rs.getBoolean("liked")
                ));
    }

    @Override
    public List<Ballad> sortByMostRecent() {
        return null;
    }

    @Override
    public List<Ballad> sortByAlphabetical(boolean isAz) {
        return null;
    }

    @Override
    public List<Ballad> sortByPopular() {
        return null;
    }
}
