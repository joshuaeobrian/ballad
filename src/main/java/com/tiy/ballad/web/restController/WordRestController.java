package com.tiy.ballad.web.restController;

import com.tiy.ballad.repository.DataMuseRepository;
import com.tiy.ballad.service.DataMuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by josh on 4/27/17.
 */
@RestController
public class WordRestController {
    @Autowired
    DataMuseService service;
    @Autowired
    DataMuseRepository repository;


    @PostMapping("/api/rhymingWord")
    public String rhymingWords(String word) throws IOException {
        return repository.getWordsThatRhymeWith(word);
    }

    @PostMapping("/api/rhymingWordRelatesTo")
    public String rhymingWordsRelatedTo(String word,String category){
        return null;
    }

    @PostMapping("/api/sugWord")
    public String suggestedWords(String substring){
        return service.getSuggestedWords(substring);
    }
}
