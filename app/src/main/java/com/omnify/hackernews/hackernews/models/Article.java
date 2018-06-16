package com.omnify.hackernews.hackernews.models;

import com.google.firebase.database.Exclude;

import java.util.List;

public class Article {
    public String by;
    public int id;
    public int score;
    public String title;
    public String type;
    public String url;
    public String text;
    public long time;
    public List<Integer> kids;

}
