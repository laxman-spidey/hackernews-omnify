package com.omnify.hackernews.hackernews;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setDatabaseUrl("https://hacker-news.firebaseio.com/") // Required for RTDB.
//                .build();
//        FirebaseApp.initializeApp(getBaseContext(), options, "hacker-news");

    }
}
