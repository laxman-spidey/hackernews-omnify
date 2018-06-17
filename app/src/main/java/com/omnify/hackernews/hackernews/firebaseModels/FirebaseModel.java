package com.omnify.hackernews.hackernews.firebaseModels;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omnify.hackernews.hackernews.BaseActivity;

public abstract class FirebaseModel {
    public static String TAG = FirebaseModel.class.getSimpleName();

    private static FirebaseDatabase database;

    private static FirebaseDatabase getInstance() {
        if (database == null) {
//            FirebaseApp app = FirebaseApp.getInstance("hacker-news");
//            database = FirebaseDatabase.getInstance(app);

            database = FirebaseDatabase.getInstance("https://hacker-news.firebaseio.com/");
            database.setPersistenceEnabled(true);
        }
        return database;
    }

    protected static DatabaseReference getDatabase() {
        DatabaseReference reference = getInstance().getReference("v0/");
        reference.keepSynced(true);
        return reference;
    }


}
