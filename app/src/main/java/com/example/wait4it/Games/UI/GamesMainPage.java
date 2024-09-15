package com.example.wait4it.Games.UI;

import android.content.SharedPreferences;
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

    private static final String SHARED_PREFS = "GamePoints";
    private static final String MAIN_POINTS_KEY = "newPoints";
    private static final String ADDITIONAL_POINTS_KEY = "additionalPoints";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

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


        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (!sharedPreferences.contains(ADDITIONAL_POINTS_KEY)) {
            editor.putInt(ADDITIONAL_POINTS_KEY, 0);
            editor.apply();
        }
    }
    private void transferPointsToMain(){
        int additionalPoints = sharedPreferences.getInt(ADDITIONAL_POINTS_KEY,0);
        int currentPoints = sharedPreferences.getInt(MAIN_POINTS_KEY,0);
        int updatedPoints = additionalPoints + currentPoints;

        editor.putInt(MAIN_POINTS_KEY,updatedPoints);
        editor.apply();

        editor.putInt(ADDITIONAL_POINTS_KEY,0);
        editor.apply();
    }


    @Override
    public void onBackPressed() {
        // Update points in the user's DB before exiting
        int totalPoints = sharedPreferences.getInt("newPoints", 0);

        // Assuming you have a method to update the user's DB with the points
        transferPointsToMain();

        // Now call the super method to handle the actual back navigation
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        transferPointsToMain();
    }

    @Override
    protected void onStop() {
        super.onStop();
        transferPointsToMain();
    }


    private void findViews() {
        gamesMain_LBL_title = findViewById(R.id.gamesMain_LBL_title);
        gamesMain_RCV_list = findViewById(R.id.gamesMain_RCV_list);
    }
}