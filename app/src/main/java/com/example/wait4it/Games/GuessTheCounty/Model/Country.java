package com.example.wait4it.Games.GuessTheCounty.Model;


public class Country {
    private String name;
    private int flagImage;

    public Country() {
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

}
