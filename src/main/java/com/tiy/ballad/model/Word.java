package com.tiy.ballad.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by josh on 4/27/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Word {

    private String word;

    public Word() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                '}';
    }
}
