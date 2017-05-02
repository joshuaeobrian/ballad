package com.tiy.ballad.model;


import java.time.LocalDate;

/**
 * Created by josh on 4/27/17.
 */
public class Note {

    private Integer id;

    private String note;

    private Integer balladId;

    private User user;

    private LocalDate creationDate;

    public Note() {
    }

    public Note(String note, Integer balladId, User user) {
        this.note = note;
        this.balladId = balladId;
        this.user = user;
    }

    public Note(Integer id, String note, Integer balladId, User user, LocalDate creationDate) {
        this.id = id;
        this.note = note;
        this.balladId = balladId;
        this.user = user;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getBalladId() {
        return balladId;
    }

    public void setBalladId(Integer balladId) {
        this.balladId = balladId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}