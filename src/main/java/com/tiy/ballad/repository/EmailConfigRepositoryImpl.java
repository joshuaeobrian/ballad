package com.tiy.ballad.repository;

import com.tiy.ballad.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by josh on 5/14/17.
 */
@Repository
public class EmailConfigRepositoryImpl implements EmailConfigRepository {
    @Autowired
    JdbcTemplate template;

    @Override
    public Email getEmail() {
        return template.queryForObject("SELECT * from email_config",
                (rs,i)-> new Email(
                        rs.getString("email"),
                        rs.getString("email_password")));
    }
}
