package com.tiy.ballad.repository;

import com.tiy.ballad.model.Note;
import com.tiy.ballad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by josh on 5/1/17.
 */
@Repository
public class NoteRepositoryImpl implements NoteRepository {
    @Autowired
    private JdbcTemplate template;


    @Override
    public void saveNotes(Note note) {
        template.update("INSERT INTO notes(ballad_use_id, notes, ballad_id)" +
                " VALUES(?,?,?)", new Object[]{note.getUser().getId(),note.getNote(),note.getBalladId()});

    }

    @Override
    public void updateNote(Note note) {
        template.update("UPDATE notes SET notes=? WHERE id=?",new Object [] {note.getId()});

    }

    @Override
    public void deleteNote(Integer id) {
        template.update("DELETE FROM notes where id=?",new Object[]{id});

    }

    @Override
    public List<Note> showNotesOnBallad(Integer balladId) {
        return template.query("SELECT " +
                        "n.id as id,n.notes AS note, n.creation_date as date, n.ballad_id as ballad_id,  " +
                        "u.id as user_id, u.first_name, u.last_name, u.email, u.username, u.password,u.active " +
                        "from notes AS n " +
                        "JOIN ballads as b ON n.ballad_id = b.id " +
                        "JOIN ballad_users as u ON n.ballad_use_id = u.id " +
                        "WHERE b.id=?",
                new Object[]{balladId},
                (rs,i)->new Note(
                        rs.getInt("id"),
                        rs.getString("note"),
                        rs.getInt("ballad_id"),
                        new User(
                                rs.getInt("user_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getBoolean("active")
                        ),
                        LocalDate.parse(rs.getString("date"))
                ));
    }

    @Override
    public Note findNoteById(Integer id) {
        return template.queryForObject("SELECT " +
                        "n.id as id,n.notes AS note, n.creation_date as date, n.ballad_id as ballad_id,  " +
                        "u.id as user_id, u.first_name, u.last_name, u.email, u.username, u.password,u.active " +
                        "from notes AS n " +
                        "JOIN ballads as b ON n.ballad_id = b.id " +
                        "JOIN ballad_users as u ON n.ballad_use_id = u.id " +
                        "WHERE n.id=?",
                new Object[]{id},
                (rs,i)->new Note(
                        rs.getInt("id"),
                        rs.getString("note"),
                        rs.getInt("ballad_id"),
                        new User(
                                rs.getInt("user_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getBoolean("active")
                        ),
                        LocalDate.parse(rs.getString("date"))
                ));
    }
}
