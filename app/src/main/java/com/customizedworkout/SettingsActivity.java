package com.customizedworkout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button changePsd;
    private TextView textAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getSupportActionBar().setTitle(R.string.app);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        auth = FirebaseAuth.getInstance();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        changePsd = findViewById(R.id.change_psd);
        textAbout = findViewById(R.id.text_about);
        final EditText inputEmail = findViewById(R.id.email);

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();


        changePsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Objects.requireNonNull(auth.getCurrentUser()).getEmail();

                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SettingsActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SettingsActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }

                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }

        });
    }


    public void changeVisibility(View v) {

        changePsd = findViewById(R.id.change_psd);
        textAbout = findViewById(R.id.text_about);


        if (v == findViewById(R.id.settings)) {

            if (changePsd.getVisibility() == View.VISIBLE) {
                changePsd.setVisibility(View.GONE);
            } else {
                changePsd.setVisibility(View.VISIBLE);
                textAbout.setVisibility(View.GONE);
            }

        } else if (v == findViewById(R.id.about)) {

            if (textAbout.getVisibility() == View.VISIBLE) {
                textAbout.setVisibility(View.GONE);
            } else {

                changePsd.setVisibility(View.GONE);
                textAbout.setVisibility(View.VISIBLE);

            }
        }

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(SettingsActivity.this, MainActivity.class);

                    startActivity(home);
                    return true;
                case R.id.navigation_tips:
                    Intent tips = new Intent(SettingsActivity.this, TipsActivity.class);

                    startActivity(tips);
                    return true;
                case R.id.navigation_settings:
                    Intent myworkout = new Intent(SettingsActivity.this, MyWorkoutActivity.class);

                    startActivity(myworkout);
                    return true;
            }
            return false;
        }
    };

    public void signOut(View v) {
        auth.signOut();
        Intent settings = new Intent(SettingsActivity.this, LoginActivity.class);

        startActivity(settings);
    }
}
