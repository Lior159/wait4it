package com.example.wait4it.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.util.PatternsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wait4it.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText register_ET_email;
    private TextInputEditText register_ET_password;
    private MaterialButton register_BTN_register;
    private ShapeableImageView register_IMG_logo;
    private TextInputEditText register_ET_firstName;
    private TextInputEditText register_ET_lastName;
    //private FirestoreManager firestoreManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        //firestoreManager = new FirestoreManager();
        //firestoreManager.initFSM();
        register_BTN_register.setOnClickListener(v->registerNewUser());
    }

    private void registerNewUser() {
        String firstName = register_ET_firstName.getText().toString().trim();
        String lastName = register_ET_lastName.getText().toString().trim();
        String email = register_ET_email.getText().toString().trim();
        String password = register_ET_password.getText().toString().trim();

        if (firstName.isEmpty()) {
            Toast.makeText(this, "Please enter a first name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (lastName.isEmpty()) {
            Toast.makeText(this, "Please enter a last name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }
        else{/*
            firestoreManager.registerUser(email, password, new FirestoreManager.RegistrationCallback() {

                @Override
                public void onRegistrationSuccess() {
                    Toast.makeText(RegisterActivity.this, "Registration Succeeded!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }

                @Override
                public void onRegistrationFailed(String msg) {
                    Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }

    private void findViews() {
        register_ET_email = findViewById(R.id.register_ET_email);
        register_ET_password = findViewById(R.id.register_ET_password);
        register_BTN_register = findViewById(R.id.register_BTN_register);
        register_IMG_logo = findViewById(R.id.register_IMG_logo);
        register_ET_firstName = findViewById(R.id.register_ET_firstName);
        register_ET_lastName = findViewById(R.id.register_ET_lastName);
    }
}