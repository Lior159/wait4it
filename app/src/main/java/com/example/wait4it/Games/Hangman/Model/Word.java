package com.example.wait4it.Games.Hangman.Model;

public class Word {
    private String word;
    private boolean isDoneAlready;

    public Word(String word){
        this.word = word;
        this.isDoneAlready = false;
    }

    public String getWord() {
        return this.word;
    }

    public boolean isDoneAlready() {
        return this.isDoneAlready;
    }

    public Word setDoneAlready() {
        this.isDoneAlready = true;
        return this;
    }
}
