package com.example.wait4it.UI;

import static com.example.wait4it.Utilities.AuthUtil.getJwtToken;
import static com.example.wait4it.Utilities.AuthUtil.getUsername;
import static com.example.wait4it.Utilities.HttpUtil.validateToken;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wait4it.Interfaces.HttpCallback;
import com.example.wait4it.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class AuthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        String token = getJwtToken(this);
        String username = getUsername(this);

        Log.d("AuthActivity", "Token: " + token);
        Log.d("AuthActivity", "Username: " + username);

        if (token != null && username != null) {
            validateToken(username, token, new HttpCallback() {
                @Override
                public void onSuccess(Response response) {
                    runOnUiThread(() -> {
                        try {
                            String responseBody = null;
                            if (response.body() != null) {
                                responseBody = response.body().string();
                            }
                            JSONObject jsonObject = new JSONObject(responseBody);

                            String status = jsonObject.getString("status");

                            if (status.equals("success")) {
                                redirectToMainMenu();
                            }
                            else {
                                redirectToLogin();
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
        else {
            redirectToLogin();
        }



    }

    private void redirectToMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void redirectToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}