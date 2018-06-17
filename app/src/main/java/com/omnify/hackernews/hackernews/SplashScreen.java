package com.omnify.hackernews.hackernews;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


    }

    @Override
    protected void onStart() {
        super.onStart();
        int SPLASH_TIMEOUT_SHORT = 3000;
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }, SPLASH_TIMEOUT_SHORT);
        } else {
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }, SPLASH_TIMEOUT_SHORT);
        }
    }
}
