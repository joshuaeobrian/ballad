package com.tiy.ballad.model;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

/**
 * Created by josh on 4/27/17.
 */
public class Ballad {

    private Integer id;

    private String title;

    private String ballad;

    private User owner;

    private List<User> collaborators;

    private LocalDate creationDate;

    private LocalDate lastEdit;

    private boolean favorite;

    private boolean publicView;

    public Ballad() {
    }

    public Ballad(String title, String ballad, User owner, boolean publicView) {
        this.title = title;
        this.ballad = ballad;
        this.owner = owner;
        this.publicView = publicView;

    }

    public Ballad(Integer id, String title, String ballad, User owner, List<User> collaborators, LocalDate creationDate, LocalDate lastEdit, boolean favorite, boolean publicView) {
        this.id = id;
        this.title = title;
        this.ballad = ballad;
        this.owner = owner;
        this.collaborators = collaborators;
        this.creationDate = creationDate;
        this.lastEdit = lastEdit;
        this.favorite = favorite;
        this.publicView = publicView;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBallad() {
        return ballad;
    }

    public void setBallad(String ballad) {
        this.ballad = ballad;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<User> collaborators) {
        this.collaborators = collaborators;
    }

    public LocalDate getCreationDate() {

        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(LocalDate lastEdit) {
        this.lastEdit = lastEdit;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isPublicView() {
        return publicView;
    }

    public void setPublicView(boolean publicView) {
        this.publicView = publicView;
    }

}