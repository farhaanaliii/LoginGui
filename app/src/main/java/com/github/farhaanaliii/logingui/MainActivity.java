package com.github.farhaanaliii.logingui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView greeting_title;
    private TextView greeting_subtitle;
    private LinearLayout auth_buttons_layout;
    private Button signin_button;
    private Button signup_button;
    private Button signout_button;

    @Override
    protected void onCreate(Bundle saved_instance_state) {
        super.onCreate(saved_instance_state);
        setContentView(R.layout.activity_main);

        init_views();
        setup_listeners();
        update_ui_state();
    }

    private void init_views() {
        greeting_title = findViewById(R.id.greetingTitle);
        greeting_subtitle = findViewById(R.id.greetingSubtitle);
        auth_buttons_layout = findViewById(R.id.authButtonsLayout);
        signin_button = findViewById(R.id.signinBtn);
        signup_button = findViewById(R.id.signupBtn);
        signout_button = findViewById(R.id.signoutBtn);
    }

    private void setup_listeners() {
        signin_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        });

        signup_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        signout_button.setOnClickListener(view -> {
            greeting_title.setText("LoginGUI");
            greeting_subtitle.setText("Select an option to continue");
            auth_buttons_layout.setVisibility(View.VISIBLE);
            signout_button.setVisibility(View.GONE);
        });
    }

    private void update_ui_state() {
        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            greeting_title.setText("Welcome, " + username + "!");
            greeting_subtitle.setText("You are successfully signed in.");
            auth_buttons_layout.setVisibility(View.GONE);
            signout_button.setVisibility(View.VISIBLE);
        }
    }
}
