package com.example.wait4it.Games.GuessTheCounty.Logic;

import com.example.wait4it.Games.GuessTheCounty.Model.Country;

import java.util.List;

public class GuessTheCountryLogic {
    public static final int ANSWER_POINTS = 10;
    private int score=0;
    private int questionIndex;
    private int life;
    private int wrongAnswers;
    private int correctAnswer;
    private CountryList countryList;

    public GuessTheCountryLogic(int life){
        this.life = life;
        countryList = new CountryList();
        questionIndex=0;
        wrongAnswers=0;
    }

    public int getScore() {
        return score;
    }
    public void incrementQuestionIndex(){
        this.questionIndex++;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public int getLife() {
        return life;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }
    public void incrementWrongAnswers(){
        this.wrongAnswers++;
    }

    public boolean isGameEnded(){
        return getQuestionIndex() == countryList.getCountries().size();
    }

    public boolean isGameLost(){
        return getLife() == getWrongAnswers();
    }

    public void incrementScore() {
        this.score+=ANSWER_POINTS;
    }

    public Country getRandomCountryForMainQuestion() {
        return this.countryList.getRandomCountry();
    }

    public List<String> getAdditionalAnswers(Country currentCountry) {
        List<String> answers = this.countryList.getAdditionalAnswers(currentCountry);
        for (int i = 0; i < answers.size(); i++) {
            if(currentCountry.getName().equals(answers.get(i))){
                correctAnswer = i;
            }
        }
        return answers;
    }
    public int getCorrectAnswer(){
        return correctAnswer;
    }
    //public void checkAnswer(String name)
}
