package com.example.wait4it.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wait4it.Interfaces.HttpCallback;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.HttpUtil;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;

import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText login_ET_username;
    private TextInputEditText login_ET_password;
    private MaterialButton login_BTN_login;
    private MaterialButton login_BTN_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        initViews();

    }

    private void findViews() {
        login_ET_username = findViewById(R.id.login_ET_username);
        login_ET_password = findViewById(R.id.login_ET_password);
        login_BTN_login = findViewById(R.id.login_BTN_login);
        login_BTN_signup = findViewById(R.id.login_BTN_signup);
    }
    private void initViews() {
        login_BTN_login.setOnClickListener(v -> login());
        login_BTN_signup.setOnClickListener(v -> redirectToRegister());
    }


    private void login() {
        String username = login_ET_username.getText().toString().trim();
        String password = login_ET_password.getText().toString().trim();

        redirectToMainMenu();
//
//
//
//        if (username.isEmpty()) {
//            Toast.makeText(this, "Please enter a username", Toast.LENGTH_LONG).show();
//            return;
//        }
//        else if (password.isEmpty()) {
//            Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show();
//            return;
//        }
//        else{
//            HttpUtil.login(username, password, new HttpCallback() {
//                @Override
//                public void onSuccess(Response response) {
//                    runOnUiThread(() -> {
//                        try {
//                            assert response.body() != null;
//                            Log.d("HTTP GET Success", "" + response.body().string());
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//                }
//
//                @Override
//                public void onFailure(IOException e) {
//                    runOnUiThread(() -> {
//                        // Handle error
//                        Log.e("HTTP GET Error", "Request failed", e);
//                    });
//                }
//            });
//        }

    }

    private void redirectToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void redirectToMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}