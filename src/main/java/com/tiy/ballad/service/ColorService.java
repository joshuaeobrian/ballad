package com.tiy.ballad.service;

import com.tiy.ballad.model.Color;
import com.tiy.ballad.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by josh on 5/12/17.
 */
@Service
public class ColorService {
    @Autowired
    private ColorRepository repository;

    public List<Color> listColors(){
        return repository.listColors();
    }
    public Color getColorByID(Integer id){
        return repository.getColorByID(id);
    }
    public Color getColorByName(String name){
        return repository.getColorByName(name);
    }
    public void addColor(Color color){
        repository.addColor(color);
    }
}
