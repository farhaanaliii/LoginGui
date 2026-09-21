package com.github.farhaanaliii.logingui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText username_input;
    private EditText password_input;
    private Button signin_button;
    private TextView signup_redirect;

    @Override
    protected void onCreate(Bundle saved_instance_state) {
        super.onCreate(saved_instance_state);
        setContentView(R.layout.activity_signin);

        init_views();
        setup_listeners();
    }

    private void init_views() {
        username_input = findViewById(R.id.username);
        password_input = findViewById(R.id.password);
        signin_button = findViewById(R.id.signinBtn);
        signup_redirect = findViewById(R.id.signupText);
    }

    private void setup_listeners() {
        PasswordToggleHelper.bind(password_input);

        signin_button.setOnClickListener(view -> handle_signin());

        signup_redirect.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void handle_signin() {
        String username = username_input.getText().toString().trim();
        String password = password_input.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            username_input.setError("Username is required");
            username_input.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            password_input.setError("Password is required");
            password_input.requestFocus();
            return;
        }

        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }
}
