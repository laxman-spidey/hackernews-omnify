package com.omnify.hackernews.hackernews.realmModels;

import android.content.Intent;

import com.google.gson.Gson;
import com.omnify.hackernews.hackernews.models.Article;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmArticle extends RealmObject {

    @PrimaryKey
    private int id;

    private String by;
    private int score;
    private String title;
    private String type;
    private String url;
    private String text;
    private long time;

    public int getKidCount() {
        return kidCount;
    }

    public void setKidCount(int kidCount) {
        this.kidCount = kidCount;
    }

    private int kidCount;


    private RealmList<Integer> kids;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public RealmList<Integer> getKids() {
        return kids;
    }

    public void setKids(RealmList<Integer> kids) {
        this.kids = kids;
    }

    public void addKid(Integer kidId) {
        this.kids.add(kidId);
    }

//    @Override
//    public String toString() {
//        return new Gson().toJson(this);
//    }



}
