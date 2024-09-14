package com.example.wait4it.UI;

import static com.example.wait4it.Utilities.AuthUtil.saveJwtToken;
import static com.example.wait4it.Utilities.AuthUtil.saveUsername;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wait4it.Interfaces.HttpCallback;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.HttpUtil;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

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

        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_LONG).show();
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show();
        } else {
            HttpUtil.login(username, password, new HttpCallback() {
                @Override
                public void onSuccess(Response response) {
                    runOnUiThread(() -> {
                        try {
                            String responseBody = null;
                            responseBody = response.body().string();
                            Log.d("HTTP GET Success", "" + responseBody);
                            JSONObject jsonObject = new JSONObject(responseBody);

                            String status = jsonObject.getString("status");
                            String token = jsonObject.getString("token");
                            String message = jsonObject.getString("message");

                            Log.d("STATUS", status);
                            Log.d("TOKEN", token);
                            Log.d("MESSAGE", message);

                            if (status.equals("success")) {
                                saveJwtToken(LoginActivity.this, token);
                                saveUsername(LoginActivity.this, username);

                                redirectToMainMenu();
                            } else {
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                        } catch (IOException | JSONException e) {
                            Log.e("HTTP GET Error", "Request failed", e);
                        }

                    });
                }

                @Override
                public void onFailure(IOException e) {
                    runOnUiThread(() -> {
                        // Handle error
                        Log.e("HTTP GET Error", "Request failed", e);
                    });
                }
            });
        }

    }

    private void redirectToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void redirectToMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}