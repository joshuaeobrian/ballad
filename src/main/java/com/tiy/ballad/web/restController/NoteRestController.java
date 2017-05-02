package com.tiy.ballad.web.restController;

import com.tiy.ballad.model.Note;
import com.tiy.ballad.model.User;
import com.tiy.ballad.service.NoteService;
import com.tiy.ballad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by josh on 5/1/17.
 */
@RestController
@RequestMapping("/api")
public class NoteRestController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;


    @PostMapping("/saveNewNote")
    public void saveNewNote(HttpSession session, String content){
        Integer userId = Integer.parseInt(session.getAttribute("id").toString());
        Integer balladId = Integer.parseInt(session.getAttribute("balladId").toString());

        User user = userService.findUserById(userId);
        if(content != null){
            Note note = new Note(content, balladId,user);
            noteService.saveNotes(note);
        }
    }

    @PostMapping("/updateNote")
    public void updateNote(HttpSession session, String content){
        Integer userId = Integer.parseInt(session.getAttribute("id").toString());
        Integer balladId = Integer.parseInt(session.getAttribute("balladId").toString());

        User user = userService.findUserById(userId);
        if(content != null){
            Note note = new Note(content, balladId,user);
            noteService.updateNote(note);
        }
    }
    @GetMapping("/balladNotes")
    public List<Note> getBalladNotes(Integer balladId){

        return noteService.showNotesOnBallad(balladId);
    }

    @GetMapping("/NoteById")
    public Note getNoteById(Integer id){
        return noteService.findNoteById(id);
    }


}
