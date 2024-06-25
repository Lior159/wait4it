package com.example.wait4it.Games.GuessTheCounty.Logic;

import com.example.wait4it.Games.GuessTheCounty.Model.Country;
import com.example.wait4it.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class CountryList {

    private ArrayList<Country> countries;
    private HashSet<Country> doneCountries;
    private Random rnd;

    public CountryList(){
        this.doneCountries = new HashSet<>();
        this.countries = new ArrayList<>();
        setCountries();
        rnd = new Random();

    }
    public void setCountries(){
        countries.add(new Country("Albania", R.drawable.albania));
        countries.add(new Country("Angola", R.drawable.angola));
        countries.add(new Country("Argentina", R.drawable.argentina));
        countries.add(new Country("Australia", R.drawable.australia));
        countries.add(new Country("Bangladesh", R.drawable.bangladesh));
        countries.add(new Country("Belgium", R.drawable.belgium));
        countries.add(new Country("Belize", R.drawable.belize));
        countries.add(new Country("Bhutan", R.drawable.bhutan));
        countries.add(new Country("Bolivia", R.drawable.bolivia));
        countries.add(new Country("Brazil", R.drawable.brazil));
        countries.add(new Country("Canada", R.drawable.canada));
        countries.add(new Country("China", R.drawable.china));
        countries.add(new Country("Czech Republic", R.drawable.czech_republic));
        countries.add(new Country("Egypt", R.drawable.egypt));
        countries.add(new Country("Estonia", R.drawable.estonia));
        countries.add(new Country("France", R.drawable.france));
        countries.add(new Country("Germany", R.drawable.germany));
        countries.add(new Country("Greece", R.drawable.greece));
        countries.add(new Country("Greenland", R.drawable.greenland));
        countries.add(new Country("Iceland", R.drawable.iceland));
        countries.add(new Country("India", R.drawable.india));
        countries.add(new Country("Indonesia", R.drawable.indonesia));
        countries.add(new Country("Ireland", R.drawable.ireland));
        countries.add(new Country("Israel", R.drawable.israel));
        countries.add(new Country("Italy", R.drawable.italy));
        countries.add(new Country("Jamaica", R.drawable.jamaica));
        countries.add(new Country("Japan", R.drawable.japan));
        countries.add(new Country("Jordan", R.drawable.jordan));
        countries.add(new Country("Kazakhstan", R.drawable.kazakhstan));
        countries.add(new Country("Kenya", R.drawable.kenya));
        countries.add(new Country("Kyrgyzstan", R.drawable.kyrgyzstan));
        countries.add(new Country("Latvia", R.drawable.latvia));
        countries.add(new Country("Luxembourg", R.drawable.luxembourg));
        countries.add(new Country("Madagascar", R.drawable.madagascar));
        countries.add(new Country("Mexico", R.drawable.mexico));
        countries.add(new Country("Moldova", R.drawable.moldova));
        countries.add(new Country("Monaco", R.drawable.monaco));
        countries.add(new Country("Montenegro", R.drawable.montenegro));
        countries.add(new Country("Mozambique", R.drawable.mozambique));
        countries.add(new Country("Nepal", R.drawable.nepal));
        countries.add(new Country("Nigeria", R.drawable.nigeria));
        countries.add(new Country("Oman", R.drawable.oman));
        countries.add(new Country("Papua New Guinea", R.drawable.papua_new_guinea));
        countries.add(new Country("Peru", R.drawable.peru));
        countries.add(new Country("Russia", R.drawable.russia));
        countries.add(new Country("San Marino", R.drawable.san_marino));
        countries.add(new Country("Seychelles", R.drawable.seychelles));
        countries.add(new Country("Slovakia", R.drawable.slovakia));
        countries.add(new Country("South Africa", R.drawable.south_africa));
        countries.add(new Country("South Korea", R.drawable.south_korea));
        countries.add(new Country("Sri Lanka", R.drawable.sri_lanka));
        countries.add(new Country("Tanzania", R.drawable.tanzania));
        countries.add(new Country("Thailand", R.drawable.thailand));
        countries.add(new Country("Turkey", R.drawable.turkey));
        countries.add(new Country("Turkmenistan", R.drawable.turkmenistan));
        countries.add(new Country("United Kingdom", R.drawable.uk));
        countries.add(new Country("United States of America", R.drawable.usa));
        countries.add(new Country("Vietnam", R.drawable.vietnam));
        countries.add(new Country("Zambia", R.drawable.zambia));
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public Country getRandomCountry(){
        List<Country> availableCountries = countries.stream()
                .filter(country -> !country.isDoneAlready())
                .collect(Collectors.toList());
        if(availableCountries.isEmpty())
            return null;

        Country selectedCountry = availableCountries.get(rnd.nextInt(availableCountries.size()));
        selectedCountry.setDoneAlready();
        return selectedCountry;
    }

    public List<String> getAdditionalAnswers(Country mainCountry){
        ArrayList<String> answers = new ArrayList<>();
        answers.add(mainCountry.getName());
        Collections.shuffle(countries);
        for (Country country:countries) {
            if(answers.size() < 4 && !answers.equals(country.getName()))
                answers.add(country.getName());
        }
        Collections.shuffle(answers);
        return answers;
    }
}
