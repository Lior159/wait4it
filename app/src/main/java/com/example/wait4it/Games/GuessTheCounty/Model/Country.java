package com.example.wait4it.Games.GuessTheCounty.Model;


public class Country {
    private String name;
    private int flagImage;
    private boolean isDoneAlready;

    public Country(String name, int flagImage) {
        this.name = name;
        this.flagImage = flagImage;
        this.isDoneAlready = false;
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public int getFlagImage() {
        return flagImage;
    }

    public Country setFlagImage(int flagImage) {
        this.flagImage = flagImage;
        return this;
    }

    public boolean isDoneAlready() {
        return isDoneAlready;
    }

    public Country setDoneAlready() {
        this.isDoneAlready = true;
        return this;
    }
}
