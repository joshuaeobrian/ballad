package com.tiy.ballad.repository;

import com.tiy.ballad.model.Note;

import java.util.List;

/**
 * Created by josh on 5/1/17.
 */
public interface NoteRepository {
    void saveNotes(Note note);
    void updateNote(Note note);
    void deleteNote(Integer id);
    List<Note> showNotesOnBallad(Integer balladId);
    Note findNoteById(Integer id);


}
