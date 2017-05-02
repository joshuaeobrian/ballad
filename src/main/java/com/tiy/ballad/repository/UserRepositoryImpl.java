package com.tiy.ballad.repository;

import com.tiy.ballad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by josh on 4/28/17.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    JdbcTemplate template;

    @Override
    public Integer saveNewUser(User user) {
        return template.queryForObject("INSERT INTO ballad_users(first_name, last_name, email, username, password)VALUES (?,?,?,?,?) RETURNING id",
                new Object[]
                        {user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getUsername(),
                        user.getPassword(),
                        },(rs, i)-> rs.getInt("id"));
    }

    @Override
    public void updateUserInfo(User user) {
        template.update("UPDATE ballad_users SET first_name=?, last_name=?, email=?, username=?, password=?, active=?, profile_image=?, about=? WHERE id=?",
                new Object[]{user.getFirstName(),user.getLastName(),user.getEmail(), user.getUsername(),user.getPassword(), user.getPhoto(), user.getAbout()});
    }

    @Override
    public void deleteUser(Integer userId) {
        template.update( "UPDATE ballad_users SET active=FALSE WHERE id=?",
                new Object[]{userId});


    }

    @Override
    public User findUserById(Integer id) {
        return template.queryForObject("SELECT id, first_name, last_name, email, username, password, active FROM ballad_users where id=?",
                new Object[]{id},
                (rs,i)-> new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("active")
                ));
    }

    @Override
    public List<User> findUserByName(String name) {
        return template.query("SELECT id, first_name, last_name,email, username,password,active from ballad_users" +
                        " WHERE lower(first_name) LIKE lower(?) OR lower(last_name) LIKE lower(?) OR lower(username) LIKE lower(?)",
                new Object[]{"%"+name+"%","%"+name+"%","%"+name+"%"},
                (rs,i)->new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("active")
                ));

    }

    @Override
    public User getUserByLoginAndPassword(String usernameOrEmail, String password) {
        return template.queryForObject("SELECT id, first_name, last_name,email, username,password,active from ballad_users where password=? AND username=? or password=? AND email=?",
                new Object[]{password,usernameOrEmail,password,usernameOrEmail},
                (rs,i)->new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("active")
                ));
    }

    @Override
    public List<User> listAllUsers() {
        return template.query("SELECT id, first_name, last_name,email, username,password,active from ballad_users",
                (rs,i)->new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("active")
        ));
    }

}
