package com.omnify.hackernews.hackernews.firebaseModels;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.ValueEventListener;
import com.omnify.hackernews.hackernews.models.Story;

import java.util.ArrayList;
import java.util.List;

public class StoriesModel extends FirebaseModel {

    public static final String TOP_STORIES = "topstories";
    public static final String STORY_ITEM = "item/";

    public static void subscribeToTopStories()
    {
        getDatabase().child(TOP_STORIES).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Story> topStories = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String storyId = snapshot.getValue().toString();
                    getDatabase().child(STORY_ITEM + storyId).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Story story = dataSnapshot.getValue(Story.class);
                            topStories.add(story);
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
