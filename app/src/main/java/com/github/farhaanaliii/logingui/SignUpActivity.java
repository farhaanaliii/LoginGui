package com.github.farhaanaliii.logingui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText email_input;
    private EditText username_input;
    private EditText password_input;
    private Button signup_button;
    private TextView signin_redirect;

    @Override
    protected void onCreate(Bundle saved_instance_state) {
        super.onCreate(saved_instance_state);
        setContentView(R.layout.activity_signup);

        init_views();
        setup_listeners();
    }

    private void init_views() {
        email_input = findViewById(R.id.email);
        username_input = findViewById(R.id.username);
        password_input = findViewById(R.id.password);
        signup_button = findViewById(R.id.signupBtn);
        signin_redirect = findViewById(R.id.signinText);
    }

    private void setup_listeners() {
        PasswordToggleHelper.bind(password_input);

        signup_button.setOnClickListener(view -> handle_signup());

        signin_redirect.setOnClickListener(view -> finish());
    }

    private void handle_signup() {
        String email = email_input.getText().toString().trim();
        String username = username_input.getText().toString().trim();
        String password = password_input.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            email_input.setError("Email is required");
            email_input.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_input.setError("Please enter a valid email");
            email_input.requestFocus();
            return;
        }

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

        if (password.length() < 6) {
            password_input.setError("Password must be at least 6 characters");
            password_input.requestFocus();
            return;
        }

        Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        intent.putExtra("username", username);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
