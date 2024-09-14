package com.example.wait4it.Model;

public class User {
    private String email;
    private String username;
    private int points;

    public User() {
    }
    public User(String email, String point, String username) {
        this.email = email;
        this.points = points;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String userName) {
        this.username = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getPoints() {
        return this.points;
    }

    public User setPoints(int points) {
        this.points = points;
        return this;
    }
}
