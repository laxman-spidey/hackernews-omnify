package com.omnify.hackernews.hackernews.firebaseModels;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class FirebaseModel {

    private static FirebaseDatabase database;

    private static FirebaseDatabase getInstance() {
        if (database == null) {
            database = FirebaseDatabase.getInstance("https://hacker-news.firebaseio.com/");
        }
        return database;
    }

    protected static DatabaseReference getDatabase() {
        return getInstance().getReference("v0/");
    }
}
