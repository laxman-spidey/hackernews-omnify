package com.omnify.hackernews.hackernews.models;

import com.google.gson.Gson;

import io.realm.RealmObject;

public class Comment extends RealmObject {
    public int id;
    public String text;
    public String by;
    public long time;

    public static Article newInstance(String json) {
        return new Gson().fromJson(json, Article.class);
    }
    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }
}
