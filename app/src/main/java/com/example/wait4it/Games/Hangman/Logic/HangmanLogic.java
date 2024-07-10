package com.example.wait4it.Games.Hangman.Logic;


import com.example.wait4it.Games.Hangman.Model.Word;

import java.util.HashSet;

public class HangmanLogic {
    private static final int ANSWER_POINTS = 10;
    private static final int LIFE = 7;
    private int score;
    private int questionIndex;
    private int wrongAnswers;
    private HangmanData data;
    private Word secretWord;
    private HashSet<Character> guessedLetters;


    public HangmanLogic()
    {
        data = new HangmanData();
        this.score = 0;
        this.questionIndex = 0;
        this.wrongAnswers = 0;
    }

    public int getScore() {
        return score;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public HangmanData getData() {
        return data;
    }

    public Word getSecretWord() {
        return secretWord;
    }

    public HashSet<Character> getGuessedLetters() {
        return guessedLetters;
    }
    public Word getRandomWord(){
        return this.data.getRandomWord();
    }

    public boolean checkLetter(String letter) {
        return this.getSecretWord().getWord().toLowerCase().contains(letter.toLowerCase());
    }

    public void incrementWrongAnswers() {
        this.wrongAnswers++;
    }
}
