package com.example.wait4it.Games.Hangman.Logic;


import com.example.wait4it.Games.Hangman.Model.Word;

public class HangmanLogic {
    private static final int ANSWER_POINTS = 10;
    private static final int LIFE = 7;
    private int score;
    private int questionIndex;
    private int wrongAnswers;
    private HangmanData data;
    private Word secretWord;


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

    public Word getSecretWord() {
        return secretWord;
    }
    public Word getRandomWord(){
        secretWord = this.data.getRandomWord();
        return secretWord;
    }

    public boolean checkLetter(String temp) {
        char letter = temp.toLowerCase().charAt(0);
        for (char c: secretWord.getWordAsString().toLowerCase().toCharArray()){
            if(c == letter)
                return true;
        }
        return false;
    }

    public void incrementWrongAnswers() {
        this.wrongAnswers++;
    }
}
