package com.example.wait4it.Games.Hangman.Logic;


import java.util.HashSet;

public class HangmanLogic {
    private static final int ANSWER_POINTS = 10;
    private int score;
    private int questionIndex;
    private int wrongAnswers;
    private HangmanData data;
    private String secretWord;
    private HashSet<Character> guessedLetters;


    public HangmanLogic()
    {
        this.score = 0;
        this.questionIndex = 0;
        this.wrongAnswers = 0;
        this.
        selectWord();

        return this;
    }
}
