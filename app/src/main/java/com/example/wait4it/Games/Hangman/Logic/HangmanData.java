package com.example.wait4it.Games.Hangman.Logic;

import com.example.wait4it.Games.Hangman.Model.Word;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class HangmanData {
    private ArrayList<Word> words;
    private HashSet<Word> doneWords;
    private Random rnd;

    public HangmanData(String category){
        this.words = new ArrayList<>();
        this.doneWords = new HashSet<>();
        setWords(category);
        rnd = new Random();
    }
    private void setWords(String category)
    {
        if(category.equals("Books"))
            setWordsBooks();
        else if(category.equals("Countries"))
            setWordsCountries();
        else if(category.equals("Quotes"))
            setWordsQuotes();
    }

    private void setWordsQuotes() {
        words.add(new Word("May the Force be with you - Star Wars"));
        words.add(new Word("I will be back - The Terminator"));
        words.add(new Word("Keep your friends close but your enemies closer - The Godfather"));
        words.add(new Word("To infinity and beyond - Toy Story"));
        words.add(new Word("Life is like a box of chocolates - Forrest Gump"));
        words.add(new Word("Just keep swimming - Finding Nemo"));
        words.add(new Word("Elementary my dear Watson - Sherlock Holmes"));
        words.add(new Word("Hakuna Matata - The Lion King"));
        words.add(new Word("Nobody puts Baby in a corner - Dirty Dancing"));
        words.add(new Word("Show me the money - Jerry Maguire"));
        words.add(new Word("Carpe diem seize the day - Dead Poets Sociery"));
        words.add(new Word("There is no place like home - The Wizard of Oz"));
        words.add(new Word("They call it a Royale with Cheese - Pulp Fiction"));
        words.add(new Word("The bare necessities of life will come to you - The Jungle Book"));
        words.add(new Word("Remember who you are - The Lion King"));

    }

    private void setWordsCountries() {
        words.add(new Word("Kyrgyzstan"));
        words.add(new Word("Luxembourg"));
        words.add(new Word("Papua New Guinea"));
        words.add(new Word("San Marino"));
        words.add(new Word("Israel"));
        words.add(new Word("Czech Republic"));
        words.add(new Word("Bangladesh"));
        words.add(new Word("Mozambique"));
        words.add(new Word("South Africa"));
        words.add(new Word("United States of America"));
        words.add(new Word("Turkmenistan"));
        words.add(new Word("Madagascar"));
        words.add(new Word("Greenland"));
        words.add(new Word("Bhutan"));
        words.add(new Word("Australia"));
    }

    private void setWordsBooks() {
        words.add(new Word("Thus Spoke Zarathustra - Friedrich Nietzsche"));
        words.add(new Word("Crime and Punishment - Fyodor Dostoevsky"));
        words.add(new Word("Ulysses - James Joyce"));
        words.add(new Word("One Hundred Years of Solitude - Gabriel Garcia Marquez"));
        words.add(new Word("The Trial - Franz Kafka"));
        words.add(new Word("In Search of Lost Time - Marcel Proust"));
        words.add(new Word("The Brothers Karamazov - Fyodor Dostoevsky"));
        words.add(new Word("War and Peace - Leo Tolstoy"));
        words.add(new Word("Moby Dick - Herman Melville"));
        words.add(new Word("The Divine Comedy - Dante Alighieri"));
        words.add(new Word("The Magic Mountain - Thomas Mann"));
        words.add(new Word("Madame Bovary - Gustave Flaubert"));
        words.add(new Word("Don Quixote - Miguel de Cervantes"));
        words.add(new Word("Anna Karenina - Leo Tolstoy"));
        words.add(new Word("Finnegans Wake - James Joyce"));
    }



    public Word getRandomWord(){
        List<Word> availableWords = words.stream()
                .filter(word -> !word.isDoneAlready())
                .collect(Collectors.toList());
        if(availableWords.isEmpty())
            return null;
        Word selectedWord = availableWords.get(rnd.nextInt(availableWords.size()));
        selectedWord.setDoneAlready();
        return selectedWord;
    }
    public ArrayList<Word> getWords(){
        return this.words;
    }
}
