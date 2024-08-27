package com.example.wait4it.Games.Hangman.Logic;


import com.example.wait4it.Games.Hangman.Model.Word;

import java.util.HashMap;
import java.util.Map;

public class HangmanLogic {
    private static final int ANSWER_POINTS = 10;
    private static final int LIFE = 7;
    private int score;
    private int questionIndex;
    private int wrongAnswers;
    private HangmanData data;
    private Word secretWord;
    private Map<Character,Integer> uniqueLetters;
    private int amountOfLetters;
    private int letterCount;


    public HangmanLogic(String category)
    {
        data = new HangmanData(category);
        this.score = 0;
        this.questionIndex = 0;
        this.wrongAnswers = 0;
        this.amountOfLetters = 0;
        this.letterCount=0;
        this.uniqueLetters = new HashMap<>();
    }

    public int getScore() {
        return score;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }
    public void incrementQuestionIndex(){
        this.questionIndex++;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setAmountOfLetters() {
        uniqueLetters = new HashMap<>();
        for(char c: secretWord.getWordAsString().toCharArray()){
            if(Character.isLetter(c)){
                char lowerCaseChar = Character.toLowerCase(c);
                uniqueLetters.put(lowerCaseChar,uniqueLetters.getOrDefault(lowerCaseChar,0) + 1);
            }
        }
        amountOfLetters= uniqueLetters.size();
    }

    public Word getSecretWord() {
        return secretWord;
    }
    public Word getRandomWord(){
        secretWord = this.data.getRandomWord();
        setAmountOfLetters();
        return secretWord;
    }
    public int getLetterCount() {
        return letterCount;
    }

    public int getAmountOfLetters(){
        return this.amountOfLetters;
    }

    public boolean checkLetter(String letterAsString) {
        char letter = letterAsString.toLowerCase().charAt(0);
        if(uniqueLetters.containsKey(letter))
        {
            letterCount++;
            return true;
        }
        return false;
    }

    public void incrementWrongAnswers() {
        this.wrongAnswers++;
    }

    public void initLetterCount(){
        this.letterCount = 0;
    }

    public boolean isGameLost() {
        return getWrongAnswers() == LIFE;
    }

    public boolean isGameEnded() {
        return questionIndex == data.getWords().size();
    }


    public int getLIFE(){
        return LIFE;
    }


    public boolean wordIsDone() {
        return this.getLetterCount() == this.getAmountOfLetters();
    }

    public void initWrongAnswers() {
        this.wrongAnswers = 0;
    }
}
