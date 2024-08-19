package com.example.wait4it.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.util.PatternsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wait4it.Interfaces.HttpCallback;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.HttpUtil;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText register_ET_email;
    private TextInputEditText register_ET_password;
    private TextInputEditText register_ET_username;;
    private MaterialButton register_BTN_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        register_BTN_register.setOnClickListener(v->registerNewUser());
    }

    private void registerNewUser() {
        String username = register_ET_username.getText().toString().trim();
        String email = register_ET_email.getText().toString().trim();
        String password = register_ET_password.getText().toString().trim();

        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
        }
        else if (password.isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
        }
        else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
        }
        else{
            HttpUtil.signup(email, password, username, new HttpCallback() {
                @Override
                public void onSuccess(Response response) {
                    runOnUiThread(() -> {
                        try {
                            assert response.body() != null;
                            Log.d("HTTP GET Success", "" + response.body().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
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