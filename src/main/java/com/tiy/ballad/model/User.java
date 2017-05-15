package com.tiy.ballad.model;


import java.util.Arrays;

public class User {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private boolean active;

    private byte[] photo;

    private String about;

    private Integer colorCode;

    public User() {
    }

    public User(Integer id, String firstName, String lastName, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public User(Integer id, String firstName, String lastName, String email, String username, String password, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;

    }
    public User(Integer id, String firstName, String lastName, String email, String username, String password, boolean active, String about, Integer colorCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
        this.photo = photo;
        this.about = about;
        this.colorCode = colorCode;
    }

    public User(Integer id, String firstName, String lastName, String email, String username, String password, boolean active, byte[] photo, String about, Integer colorCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
        this.photo = photo;
        this.about = about;
        this.colorCode = colorCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Integer getColorCode() {
        return colorCode;
    }

    public void setColorCode(Integer colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", photo=" + Arrays.toString(photo) +
                ", about='" + about + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }
}
