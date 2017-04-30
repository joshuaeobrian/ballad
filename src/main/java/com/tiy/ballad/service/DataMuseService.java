package com.tiy.ballad.service;

import com.tiy.ballad.repository.DataMuseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by josh on 4/28/17.
 */
@Component
public class DataMuseService {
    @Autowired
    private DataMuseRepository repository;


//    public String listWords(String word){
//       return repository.listWords(word);
//    }

    public String getSuggestedWords(String  word){
        return repository.getSuggestedWords(word);
    }
    public String getWordsThatRhymeWith(String word){
        return repository.getWordsThatRhymeWith(word);
    }

    public String getRhymingWordsRelatedTo(String word, String category){
        return repository.getRhymingWordsRelatedTo(word, category);
    }


}
