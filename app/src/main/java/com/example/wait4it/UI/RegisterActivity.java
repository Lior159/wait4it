package com.example.wait4it.UI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import com.example.wait4it.Interfaces.HttpCallback;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.HttpUtil;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText register_ET_email;
    private TextInputEditText register_ET_password;
    private TextInputEditText register_ET_username;

    private MaterialButton register_BTN_register;

    private static final String SHARED_PREFS = "UserInfo";
    private final static String USERNAME = "UserName";
    private final static String EMAIL = "Email";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        register_BTN_register.setOnClickListener(v -> registerNewUser());
    }

    private void registerNewUser() {
        String username = register_ET_username.getText().toString().trim();
        String email = register_ET_email.getText().toString().trim();
        String password = register_ET_password.getText().toString().trim();

        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(EMAIL,email);
        editor.apply();

        Log.d("Register", "Username: " + username + ", Email: " + email + ", Password: " + password);

        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
        } else {
            HttpUtil.signup(email, username, password, new HttpCallback() {
                @Override
                public void onSuccess(Response response) {
                    runOnUiThread(() -> {
                        try {
                            String responseBody = response.body().string();

                            Log.d("HTTP GET Success", "" + responseBody);
                            JSONObject jsonObject = new JSONObject(responseBody);

                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();

                            if (status.equals("success"))
                                redirectToLogin();
                            else {
                                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                            }

                            Log.d("STATUS", status);
                        } catch (IOException e) {
                            Log.e("HTTP GET Error", "Request failed", e);
                        } catch (JSONException e) {
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

    private void findViews() {
        register_ET_email = findViewById(R.id.register_ET_email);
        register_ET_password = findViewById(R.id.register_ET_password);
        register_ET_username = findViewById(R.id.register_ET_username);
        register_BTN_register = findViewById(R.id.register_BTN_register);
    }

    private void redirectToLogin() {
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
        finish();
    }
}