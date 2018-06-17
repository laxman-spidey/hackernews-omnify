package com.omnify.hackernews.hackernews.realmModels;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmComment extends RealmObject {
    @PrimaryKey
    private int id;
    private String text;
    private String by;
    private long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
