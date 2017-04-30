package com.tiy.ballad.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by josh on 4/27/17.
 */
public class Ballad {

    private Integer id;

    private String title;

    private String ballad;

    private Integer userId;

    private LocalDateTime creationDate;

    private LocalDateTime lastEdit;

    private boolean favorite;

    private boolean publicView;

    public Ballad() {
    }

    public Ballad(Integer id, String title, String ballad, Integer userId, LocalDateTime creationDate, LocalDateTime lastEdit, boolean favorite, boolean publicView) {
        this.id = id;
        this.title = title;
        this.ballad = ballad;
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(LocalDateTime lastEdit) {
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
