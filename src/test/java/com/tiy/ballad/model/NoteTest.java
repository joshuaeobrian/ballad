package com.tiy.ballad.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by josh on 5/2/17.
 */


public class NoteTest {

    @Test
    public void emptyNoteConstructor(){
        Note note = new Note();
    }

    @Test
    public void creatingNewNoteConstructorForWebAdi(){
        Note note = new Note("This is a test note",3, new User(
              2, "Bob","Log", "bobLog2012"
        ));

        assertThat(note.getNote(),equalTo("This is a test note"));
        assertThat(note.getBalladId(),equalTo(3));
        assertThat(note.getUser().getId(), equalTo(2));
    }

    @Test
    public void creatingNewNoteForDatabase(){

    }

    @Test
    public void testIdOnNotes(){

    }
    @Test
    public void testNoteOnNotes(){

    }




}
