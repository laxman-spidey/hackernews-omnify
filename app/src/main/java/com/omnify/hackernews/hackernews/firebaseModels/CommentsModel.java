package com.omnify.hackernews.hackernews.firebaseModels;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.omnify.hackernews.hackernews.models.Comment;

public class CommentsModel  extends FirebaseModel {
    public static final String COMMENT_ITEM = "item/";

    public static void getComment(int commentId, ResponseListener listener) {
        getDatabase().child(COMMENT_ITEM + commentId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Comment comment  = dataSnapshot.getValue(Comment.class);
                listener.onResponseRecieved(new ResponseListener.Response(true, comment));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
