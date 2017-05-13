package com.tiy.ballad.repository;

import com.tiy.ballad.model.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by josh on 5/12/17.
 */
public interface ColorRepository {

    List<Color> listColors();
    Color getColorByID(Integer id);
    Color getColorByName(String name);
    void addColor(Color color);
}
