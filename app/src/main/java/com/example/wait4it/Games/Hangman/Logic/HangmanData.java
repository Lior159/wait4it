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

    public HangmanData(){
        this.words = new ArrayList<>();
        this.doneWords = new HashSet<>();
        setWords();
        rnd = new Random();
    }

    /*private void setWords() {
        words.add(new Word("Thus Spoke Zarathustra - Friedrich Nietzsche"));
        words.add(new Word("Crime and Punishment - Fyodor Dostoevsky"));
        words.add(new Word("Ulysses - James Joyce"));
        words.add(new Word("One Hundred Years of Solitude - Gabriel García Marquez"));
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
    }*/
    private void setWords() {
        words.add(new Word("Don Quixote - Miguel de Cervantes"));
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
