package com.omnify.hackernews.hackernews.realmModels;

import com.omnify.hackernews.hackernews.models.Comment;

import io.realm.Realm;

public class CommentsModel {

    public static void insertComment(Comment comment) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmComment realmComment = realm.createObject(RealmComment.class, comment.id);
        realmComment.setBy(comment.by);
        realmComment.setText(comment.text);
        realmComment.setTime(comment.time);
        realm.commitTransaction();
    }

    public static Comment getComment(int commentId) {
        Realm realm = Realm.getDefaultInstance();
        RealmComment realmComment = realm.where(RealmComment.class).equalTo("id", commentId).findFirst();
        if (realmComment == null) {
            return null;
        }
        return toNonRealm(realmComment);
    }

    public static Comment toNonRealm(RealmComment realmComment) {

        Comment comment = new Comment();
        comment.id = realmComment.getId();
        comment.text = realmComment.getText();
        comment.by = realmComment.getBy();
        comment.time = realmComment.getTime();
        return comment;

    }
}
