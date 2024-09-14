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


        sharedPreferences = getSharedPreferences("GamePoints", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (!sharedPreferences.contains("newPoints")) {
            editor.putInt("newPoints", 0);
            editor.apply();
        }
    }

    @Override
    public void onBackPressed() {
        // Update points in the user's DB before exiting
        int totalPoints = sharedPreferences.getInt("newPoints", 0);

        // Assuming you have a method to update the user's DB with the points
        editor.remove("newPoints");
        editor.apply();
        updatePointsInDatabase(totalPoints);

        // Now call the super method to handle the actual back navigation
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        int totalPoints = sharedPreferences.getInt("newPoints", 0);
        editor.remove("newPoints");
        editor.apply();
        updatePointsInDatabase(totalPoints);
    }

    @Override
    protected void onStop() {
        super.onStop();
        int totalPoints = sharedPreferences.getInt("newPoints", 0);
        editor.remove("newPoints");
        editor.apply();
        updatePointsInDatabase(totalPoints);
    }

    private void updatePointsInDatabase(int points) {
        // Your logic for updating the points in the user's database
    }


    private void findViews() {
        gamesMain_LBL_title = findViewById(R.id.gamesMain_LBL_title);
        gamesMain_RCV_list = findViewById(R.id.gamesMain_RCV_list);
    }
}