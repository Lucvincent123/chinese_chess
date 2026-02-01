package com.chinesechess;

public class User {
    private final String username;
    private final String color; // "Red" or "Black"

    public User(String username, String color) {
        this.username = username;
        this.color = color;
    }

    public String getUsername() {
        return username;
    }

    public String getColor() {
        return color;
    }
}


