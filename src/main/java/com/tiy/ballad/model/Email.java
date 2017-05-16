package com.tiy.ballad.model;

/**
 * Created by josh on 5/14/17.
 */
public class Email {
    private String email;
    private String password;

    public Email() {
    }

    public Email(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Email{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
