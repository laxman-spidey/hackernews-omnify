package com.omnify.hackernews.hackernews.firebaseModels;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.omnify.hackernews.hackernews.models.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticlesModel extends FirebaseModel {

    public static final String TOP_STORIES = "topstories";
    public static final String STORY_ITEM = "item/";

    public static int MAX_TOP_STORIES = 50;

    public static void subscribeToTopStories(ResponseListener listener)
    {
        getDatabase().child(TOP_STORIES).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(count++ > MAX_TOP_STORIES) { break; }
                    String storyId = snapshot.getValue().toString();
                    getDatabase().child(STORY_ITEM + storyId).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Article article = dataSnapshot.getValue(Article.class);
                            listener.onResponseRecieved(new ResponseListener.Response(true, article));
                            Log.i("TAG", dataSnapshot.toString());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}