package com.example.wait4it.Games.GuessTheCounty.Logic;

import com.example.wait4it.Games.GuessTheCounty.Model.Country;

import java.util.List;

public class GuessTheCountryLogic {
    public static final int ANSWER_POINTS = 10;
    private int score=0;
    private int questionIndex=0;
    private int life;
    private int wrongAnswers=0;
    private CountryList countryList;

    public GuessTheCountryLogic(int life){
        this.life = life;
        countryList = new CountryList();
    }

    public int getScore() {
        return score;
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

    public boolean isGameEnded(){
        return getQuestionIndex() == countryList.getCountries().size();
    }

    public boolean isGameLost(){
        return getLife() == getWrongAnswers();
    }

    public boolean checkAnswer(String string) {
        return true;
    }

    public Country getRandomCountryForMainQuestion() {
        return this.countryList.getRandomCountry();
    }

    public List<String> getAdditionalAnswers(Country currentCountry) {
        return this.countryList.getAdditionalAnswers(currentCountry);
    }
    //public void checkAnswer(String name)
}
