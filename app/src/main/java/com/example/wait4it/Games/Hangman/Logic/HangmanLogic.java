package com.example.wait4it.Games.Hangman.Logic;


import com.example.wait4it.Games.Hangman.Model.Word;

import java.util.HashSet;
import java.util.Set;

public class HangmanLogic {
    private static final int ANSWER_POINTS = 10;
    private static final int LIFE = 7;
    private int score;
    private int questionIndex;
    private int wrongAnswers;
    private HangmanData data;
    private Word secretWord;
    private Set<Character> uniqueLetters;
    private int amountOfLetters;


    public HangmanLogic()
    {
        data = new HangmanData("Books");
        this.score = 0;
        this.questionIndex = 0;
        this.wrongAnswers = 0;
        this.uniqueLetters = new HashSet<>();
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

    public int getAmountOfLetters(){
        Set<Character> uniqueLetters = new HashSet<>();
        for(char c: secretWord.getWordAsString().toCharArray()){
            if(Character.isLetter(c))
                uniqueLetters.add(Character.toLowerCase(c));
        }
        return uniqueLetters.size();
    }

    public boolean checkLetter(String temp) {
        char letter = temp.toLowerCase().charAt(0);
        for (char c: secretWord.getWordAsString().toLowerCase().toCharArray()){
            if(c == letter){
                uniqueLetters.add(c);
                return true;
            }
        }
        return false;
    }

    public void incrementWrongAnswers() {
        this.wrongAnswers++;
    }


    public boolean isGameLost() {
        return getWrongAnswers() == LIFE;
    }

    public boolean isGameEnded(String category) {
        //return getQuestionIndex() == data.
        return false;
    }
}
