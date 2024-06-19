package com.example.wait4it.Games.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wait4it.Games.Adapters.GamesAdapter;
import com.example.wait4it.Games.Model.GameList;
import com.example.wait4it.R;
import com.google.android.material.textview.MaterialTextView;

public class GamesMainPage extends AppCompatActivity {

    private MaterialTextView gamesMain_LBL_title;
    private RecyclerView gamesMain_RCV_list;
    private GamesAdapter adapter;
    private GameList list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_main_page);
        findViews();
        initViews();
    }

    private void initViews() {
        gamesMain_LBL_title.setText("Games");
        list = new GameList();
        adapter = new GamesAdapter(this, list.getGamesList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        gamesMain_RCV_list.setLayoutManager(linearLayoutManager);
        gamesMain_RCV_list.setAdapter(adapter);

    }

    private void findViews() {
        gamesMain_LBL_title = findViewById(R.id.gamesMain_LBL_title);
        gamesMain_RCV_list = findViewById(R.id.gamesMain_RCV_list);
    }
}