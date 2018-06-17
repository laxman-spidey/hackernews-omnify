package com.omnify.hackernews.hackernews.models;

import com.google.firebase.database.Exclude;
import com.google.gson.Gson;

import java.util.List;

import io.realm.RealmObject;

public class Article extends Model {
    public String by;
    public int id;
    public int score;
    public String title;
    public String type;
    public String url;
    public String text;
    public long time;
    public List<Integer> kids;

    public static Article newInstance(String json) {
        return new Gson().fromJson(json, Article.class);
    }
    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }
}
