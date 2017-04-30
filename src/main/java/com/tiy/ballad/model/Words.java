package com.tiy.ballad.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by josh on 4/27/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Words {
    private List<Word> words;

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

//    @Override
//    public String toString() {
//        return "Words{" +
//                "words=" + words +
//                '}';
//    }
}
