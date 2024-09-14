package com.example.wait4it.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.wait4it.Games.UI.GamesMainPage;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.ImageLoader;
import com.example.wait4it.Webview.ArticlesMainPage;
import com.example.wait4it.Webview.NewsMainPage;
import com.example.wait4it.Webview.PodcastsMainPage;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout main_DRAWER_menu;

    private MaterialTextView menu_LBL_username;
    private MaterialTextView menu_LBL_email;
    private MaterialTextView menu_LBL_points;
    private MaterialTextView menu_LBL_redeem;
    private MaterialTextView menu_LBL_changePassword;
    private MaterialTextView menu_LBL_about;

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

    private void initDrawer() {

        //get from DB: username, email, points
        menu_LBL_changePassword.setOnClickListener(v->{
            changePassword();
        });
        menu_LBL_about.setOnClickListener(v->{
            showAbout();
        });
    }

    private void changePassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View popupView = getLayoutInflater().inflate(R.layout.change_password,null);
        builder.setTitle("Change password");
        TextInputEditText changePassword_ET_passwordEntry = popupView.findViewById(R.id.changePassword_ET_passwordEntry);
        MaterialButton changePassword_BTN_change = popupView.findViewById(R.id.changePassword_BTN_change);
        builder.setView(popupView);
        AlertDialog dialog = builder.create();
        dialog.show();

        changePassword_BTN_change.setOnClickListener(v->{
            String temp = changePassword_ET_passwordEntry.getText().toString().trim();
            if(!temp.isEmpty() || temp!=null)
                Toast.makeText(this, "new pass is " + temp, Toast.LENGTH_SHORT).show();
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
        menu_LBL_changePassword = findViewById((R.id.menu_LBL_changePassword));
        menu_LBL_about = findViewById((R.id.menu_LBL_about));
    }
}