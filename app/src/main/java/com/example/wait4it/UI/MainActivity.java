package com.example.wait4it.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wait4it.Games.UI.GamesMainPage;
import com.example.wait4it.Webview.NewsMainPage;
import com.example.wait4it.Webview.PodcastsMainPage;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.ImageLoader;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {
    private ImageLoader imageLoader;
    private ShapeableImageView main_IMG_menu;
    private ShapeableImageView main_IMG_logo;
    private ShapeableImageView main_BTN_games;
    private ShapeableImageView main_BTN_news;
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
            Intent intent = new Intent(this, GamesMainPage.class);
            startActivity(intent);
        });
        main_BTN_podcasts.setOnClickListener(v->{
            Intent intent = new Intent(this, PodcastsMainPage.class);
            startActivity(intent);
        });
        main_BTN_news.setOnClickListener(v->{
            Intent intent = new Intent(this, NewsMainPage.class);
            startActivity(intent);
        });
    }

    private void loadImages() {
        imageLoader = new ImageLoader(this);
        imageLoader.load(R.drawable.logo, R.drawable.ic_launcher_background, main_IMG_logo);
        imageLoader.load(R.drawable.ic_more_vert, R.drawable.ic_launcher_background, main_IMG_menu);
    }

    private void findViews() {
        main_IMG_menu = findViewById(R.id.main_IMG_menu);
        main_IMG_logo = findViewById(R.id.main_IMG_logo);
        main_BTN_games = findViewById(R.id.main_BTN_games);
        main_BTN_articles = findViewById(R.id.main_BTN_articles);
        main_BTN_news = findViewById(R.id.main_BTN_news);
        main_BTN_podcasts = findViewById(R.id.main_BTN_podcasts);
    }
}