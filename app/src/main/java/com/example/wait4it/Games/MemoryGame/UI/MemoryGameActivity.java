package com.example.wait4it.Games.MemoryGame.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wait4it.Games.MemoryGame.Adapters.CardAdapter;
import com.example.wait4it.Games.MemoryGame.Interfaces.TimerControlListener;
import com.example.wait4it.R;
import com.google.android.material.textview.MaterialTextView;

public class MemoryGameActivity extends AppCompatActivity implements TimerControlListener {
    private RecyclerView memoryGame_RCV_table;
    private MaterialTextView memoryGame_LBL_title;
    private MaterialTextView memoryGame_LBL_timer;

    private CardAdapter adapter;

    private Handler timerHandler;
    private Runnable timerRunnable;
    private long startTime;
    private boolean timerStarted = false;

    private String level = "";
    private int rows;
    private int cols;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);
        findViews();
        Intent intent = getIntent();
        level = intent.getStringExtra("LEVEL");
        rows = intent.getIntExtra("ROWS",4);
        cols = intent.getIntExtra("COLS",2);
        initViews();
        //configureGridView();
        configureTimer();
    }

    private void initViews() {
        memoryGame_LBL_title.setText(String.format("Memory Game - Level: %s", level));
        adapter = new CardAdapter(this,rows*cols, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,cols){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }

        };
        memoryGame_RCV_table.setLayoutManager(gridLayoutManager);

        memoryGame_RCV_table.setAdapter(adapter);
        memoryGame_RCV_table.setHasFixedSize(true);

    }

    private void configureTimer() {
        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = SystemClock.elapsedRealtime() - startTime;
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                memoryGame_LBL_timer.setText(String.format("%02d:%02d", minutes,seconds));
                timerHandler.postDelayed(this,500);
            }
        };
    }

    private void findViews() {
        memoryGame_LBL_title = findViewById(R.id.memoryGame_LBL_title);
        memoryGame_LBL_timer = findViewById(R.id.memoryGame_LBL_timer);
        memoryGame_RCV_table = findViewById(R.id.memoryGame_RCV_table);
    }
    public void startTimer(){
        if (!timerStarted){
            startTime = SystemClock.elapsedRealtime();
            timerHandler.postDelayed(timerRunnable,0);
            timerStarted = true;
        }
    }

    public void stopTimer(){
        timerHandler.removeCallbacks(timerRunnable);;
        timerStarted=false;
    }

    @Override
    public void onGameCompleted(int score) {
        endGame(score);
    }

    public void endGame(int score){
        stopTimer();
        long millis = SystemClock.elapsedRealtime() - startTime;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        showEndGameDialog(minutes, seconds, score);

    }

    private void showEndGameDialog(int minutes, int seconds, int score) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String timeMessage;

        if(minutes > 0)
            timeMessage = String.format("You cleared the level in %d minutes and %d seconds!\nYour score is: %d", minutes,seconds, score);
        else
            timeMessage = String.format("You cleared the level in %d seconds!\nYour score is: %d",seconds, score);

        builder.setTitle("Congratulations!");
        builder.setMessage(timeMessage);
        builder.setPositiveButton("OK", (dialog, id) -> {
            addPoints(score);
            navigateToGameMenu();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void addPoints(int pointsEarned) {
        SharedPreferences sharedPreferences = getSharedPreferences("GamePoints", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int currentPoints = sharedPreferences.getInt("newPoints", 0);
        editor.putInt("newPoints", currentPoints + pointsEarned);
        editor.apply();
    }

    private void navigateToGameMenu() {
        Intent intent = new Intent(this, Memory_Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
