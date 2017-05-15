package com.tiy.ballad.repository;

import com.tiy.ballad.model.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by josh on 5/12/17.
 */
@Repository
public class ColorRepositoryImpl implements ColorRepository {

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Color> listColors() {
        return template.query("SELECT id, hex_code, color_name FROM color_code",
                (rs,i)-> new Color(
                        rs.getInt("id"),
                        rs.getString("hex_code"),
                        rs.getString("color_name")
                ));
    }

    @Override
    public Color getColorByID(Integer id) {
        return template.queryForObject("SELECT * from color_code WHERE id=?",
                new Object[]{id},(rs,i)-> new Color(
                        rs.getInt("id"),
                        rs.getString("hex_code"),
                        rs.getString("color_name")
                ));
    }

    @Override
    public Color getColorByName(String name) {
        return null;
    }

    @Override
    public void addColor(Color color) {

    }
}
