package com.example.wait4it.Games.Model;

import com.example.wait4it.Games.MemoryGame.UI.Memory_Menu;
import com.example.wait4it.R;

import java.util.ArrayList;

public class GameList {
    public ArrayList<GameItem> getGamesList(){
        ArrayList<GameItem> list = new ArrayList<>();
        list.add(new GameItem("Memory Game", R.drawable.memorygame_icon, Memory_Menu.class));

        return list;
    }

}
