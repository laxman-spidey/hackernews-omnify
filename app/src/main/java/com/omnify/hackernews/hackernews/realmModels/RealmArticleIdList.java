package com.omnify.hackernews.hackernews.realmModels;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmArticleIdList extends RealmObject {

    @PrimaryKey
    private int id;
    private RealmList<Integer> articleIdList;
    private long lastUpdated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Integer> getArticleIdList() {
        return articleIdList;
    }

    public void setArticleIdList(RealmList<Integer> articleIdList) {
        this.articleIdList = articleIdList;
    }

    public void addArticleId(int articleId) {
        this.articleIdList.add(articleId);
    }
    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
