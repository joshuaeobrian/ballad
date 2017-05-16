package com.tiy.ballad.web;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by josh on 5/14/17.
 */
@Component
public class RandomPasswordGenerator {
    private Random random = new Random();
    private List<String> characters = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","L","M","N","O","P","Q","R","S","T","1","2","3","4","5","6","7","8","9","0");


    public String generateRandomPassword(){
        String newPassword = "";

        for(int i = 0; i < 10; i++){
            newPassword += characters.get(random.nextInt(characters.size()));
        }


        return newPassword;
    }
}
