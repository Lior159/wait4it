package com.example.wait4it.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wait4it.Games.MemoryGame.UI.Memory_Menu;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.ImageLoader;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {
    private ImageLoader imageLoader;
    private ShapeableImageView main_IMG_menu;
    private ShapeableImageView main_IMG_logo;
    private ShapeableImageView main_BTN_games;
    private ShapeableImageView main_BTN_articles;
    private ShapeableImageView main_BTN_podcasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        loadImages();
        //handleButtons();
        main_BTN_games.setOnClickListener(v->{
            Intent intent = new Intent(this, Memory_Menu.class);
            startActivity(intent);
        });
    }

    private void loadImages() {
        imageLoader = new ImageLoader(this);
        imageLoader.load(R.drawable.logo, main_IMG_logo);
        imageLoader.load(R.drawable.ic_more_vert, main_IMG_menu);
        imageLoader.load(R.drawable.articles, main_BTN_articles);
        imageLoader.load(R.drawable.games, main_BTN_games);
        imageLoader.load(R.drawable.podcast, main_BTN_podcasts);
    }

    private void findViews() {
        main_IMG_menu = findViewById(R.id.main_IMG_menu);
        main_IMG_logo = findViewById(R.id.main_IMG_logo);
        main_BTN_games = findViewById(R.id.main_BTN_games);
        main_BTN_articles = findViewById(R.id.main_BTN_articles);
        main_BTN_podcasts = findViewById(R.id.main_BTN_podcasts);
    }
}