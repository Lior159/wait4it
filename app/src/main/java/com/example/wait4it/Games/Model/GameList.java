package com.example.wait4it.Games.Model;

import com.example.wait4it.Games.GuessTheCounty.UI.GuessTheCountryActivity;
import com.example.wait4it.Games.Hangman.UI.HangmanActivity;
import com.example.wait4it.Games.MemoryGame.UI.Memory_Menu;
import com.example.wait4it.R;

import java.util.ArrayList;

public class GameList {
    ArrayList<GameItem> list;
    public ArrayList<GameItem> getGamesList(){
        list = new ArrayList<>();
        list.add(new GameItem("Memory Game", R.drawable.memorygame_icon, Memory_Menu.class));
        list.add(new GameItem("Guess The Flag", R.drawable.guesstheflag_icon, GuessTheCountryActivity.class));
        list.add(new GameItem("Hangman", R.drawable.hangman_icon, HangmanActivity.class));

        return list;
    }

}
