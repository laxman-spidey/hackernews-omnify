package com.omnify.hackernews.hackernews.firebaseModels;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.omnify.hackernews.hackernews.models.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArticlesModel extends FirebaseModel {
    public static String TAG = ArticlesModel.class.getSimpleName();

    public static final String TOP_STORIES = "topstories";
    public static final String STORY_ITEM = "item/";


    public static void subscribeToTopStories(ResponseListener listener)
    {
        getDatabase().child(TOP_STORIES).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, "onDataChange()");
                GenericTypeIndicator<ArrayList<Integer>> type = new GenericTypeIndicator<ArrayList<Integer>>() {};
                ArrayList storyList = dataSnapshot.getValue(type);
                listener.onResponseRecieved(new ResponseListener.Response(true, storyList));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i(TAG, databaseError.getDetails());
                listener.onResponseRecieved(new ResponseListener.Response(false, null));
            }
        });
    }

    public static void getArticle(int articleId, ResponseListener listener)
    {
        getDatabase().child(STORY_ITEM + articleId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Article article = dataSnapshot.getValue(Article.class);
                listener.onResponseRecieved(new ResponseListener.Response(true, article));
                Log.i("TAG", dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onResponseRecieved(new ResponseListener.Response(false, null));
            }
        });
    }




}
