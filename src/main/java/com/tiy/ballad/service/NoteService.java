package com.tiy.ballad.service;

import com.tiy.ballad.model.Note;
import com.tiy.ballad.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by josh on 5/1/17.
 */
@Service
public class NoteService {
    @Autowired
    NoteRepository repository;
    public void saveNotes(Note note){
        repository.saveNotes(note);
    }
    public void updateNote(Note note){
        repository.updateNote(note);
    }
    public void deleteNote(Integer id){
        repository.deleteNote(id);
    }
    public List<Note> showNotesOnBallad(Integer balladId){
        return repository.showNotesOnBallad(balladId);
    }
    public Note findNoteById(Integer id){
        return repository.findNoteById(id);
    }
}
