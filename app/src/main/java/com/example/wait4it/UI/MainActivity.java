package com.example.wait4it.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.wait4it.Games.UI.GamesMainPage;
import com.example.wait4it.Model.User;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.ImageLoader;
import com.example.wait4it.Webview.ArticlesMainPage;
import com.example.wait4it.Webview.NewsMainPage;
import com.example.wait4it.Webview.PodcastsMainPage;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_POINTS = "GamePoints";
    private static final String SHARED_PREFS_USER = "UserInfo";
    private static final String POINTS_KEY = "newPoints";
    private static final String USERNAME_KEY = "UserName";
    private static final String EMAIL_KEY = "Email";
    private DrawerLayout main_DRAWER_menu;

    private MaterialTextView menu_LBL_username;
    private MaterialTextView menu_LBL_email;
    private MaterialTextView menu_LBL_points;
    private MaterialTextView menu_LBL_redeem;

    private MaterialTextView menu_LBL_about;

    private ImageLoader imageLoader;
    private ShapeableImageView main_IMG_menu;
    private ShapeableImageView main_IMG_logo;
    private ShapeableImageView main_BTN_games;
    private ShapeableImageView main_BTN_news;
    private ShapeableImageView main_BTN_articles;
    private ShapeableImageView main_BTN_podcasts;

    private User user;
    private SharedPreferences sharedPreferencesPoints;
    private SharedPreferences sharedPreferencesUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferencesUser = getSharedPreferences(SHARED_PREFS_USER,MODE_PRIVATE);
        sharedPreferencesPoints = getSharedPreferences(SHARED_PREFS_POINTS, MODE_PRIVATE);

        setUser();
        findViews();
        initDrawer();
        loadImages();
        //handleButtons();
        main_BTN_games.setOnClickListener(v->{
            startActivity(new Intent(this, GamesMainPage.class));
        });
        main_BTN_podcasts.setOnClickListener(v->{
            startActivity(new Intent(this, PodcastsMainPage.class));
        });
        main_BTN_news.setOnClickListener(v->{
            startActivity(new Intent(this, NewsMainPage.class));
        });
        main_BTN_articles.setOnClickListener(v->{
            startActivity(new Intent(this, ArticlesMainPage.class));
        });

        main_IMG_menu.setOnClickListener(v->{
            if(main_DRAWER_menu != null) {
                if (main_DRAWER_menu.isDrawerOpen(GravityCompat.END)) {
                    main_DRAWER_menu.closeDrawer(GravityCompat.END);
                } else {
                    main_DRAWER_menu.openDrawer(GravityCompat.END);
                }
            }
        });
    }

    private void setUser() {
        user = new User();
        String username = sharedPreferencesUser.getString(USERNAME_KEY, "Failed to get username" );
        String email = sharedPreferencesUser.getString(EMAIL_KEY, "Failed to get email" );
        int points = sharedPreferencesPoints.getInt(POINTS_KEY,0);
        user.setUsername(username);
        user.setEmail(email);
        user.setPoints(points);
    }

    @SuppressLint("DefaultLocale")
    private void initDrawer() {

        menu_LBL_username.setText(String.format("Username: \n%s\n", user.getUsername()));
        menu_LBL_email.setText(String.format("Email: \n%s\n", user.getEmail()));
        menu_LBL_points.setText(String.format("Points: \n%d\n", user.getPoints()));


        menu_LBL_about.setOnClickListener(v->{
            showAbout();
        });
    }

    private void showAbout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View popupView = getLayoutInflater().inflate(R.layout.about,null);
        builder.setTitle("About");
        MaterialTextView LBL_about = popupView.findViewById(R.id.LBL_about);
        builder.setView(popupView);
        builder.setPositiveButton("OK", (dialog,which)->{
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();



    }

    private void loadImages() {
        imageLoader = new ImageLoader(this);
        imageLoader.load(R.drawable.logo, R.drawable.ic_launcher_background, main_IMG_logo);
        imageLoader.load(R.drawable.ic_more_vert, R.drawable.ic_launcher_background, main_IMG_menu);
    }

    @Override
    protected void onResume(){
        super.onResume();

        int points = sharedPreferencesPoints.getInt(POINTS_KEY,0);
        menu_LBL_points.setText(String.format("Points: \n%d\n", points));
    }

    private void findViews() {
        main_DRAWER_menu = findViewById(R.id.main_DRAWER_menu);
        main_IMG_menu = findViewById(R.id.main_IMG_menu);
        main_IMG_logo = findViewById(R.id.main_IMG_logo);
        main_BTN_games = findViewById(R.id.main_BTN_games);
        main_BTN_articles = findViewById(R.id.main_BTN_articles);
        main_BTN_news = findViewById(R.id.main_BTN_news);
        main_BTN_podcasts = findViewById(R.id.main_BTN_podcasts);
        menu_LBL_username = findViewById((R.id.menu_LBL_username));
        menu_LBL_email = findViewById((R.id.menu_LBL_email));
        menu_LBL_points = findViewById((R.id.menu_LBL_points));
        menu_LBL_redeem = findViewById((R.id.menu_LBL_redeem));
        menu_LBL_about = findViewById((R.id.menu_LBL_about));
    }
}