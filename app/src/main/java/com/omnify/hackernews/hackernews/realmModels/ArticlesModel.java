package com.omnify.hackernews.hackernews.realmModels;

import android.util.Log;

import com.omnify.hackernews.hackernews.models.Article;
import com.omnify.hackernews.hackernews.models.ArticleIdList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ArticlesModel {


    public static void insertArticleIdList(List<Integer> articleIdList) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmArticleIdList realmArticleIdList = realm.where(RealmArticleIdList.class).equalTo("id", 0).findFirst();
        if (realmArticleIdList == null) {
            realmArticleIdList = realm.createObject(RealmArticleIdList.class, 0);
        }
        realmArticleIdList.setLastUpdated(Calendar.getInstance().getTimeInMillis());
        realmArticleIdList.setArticleIdList(new RealmList<>());
        if (articleIdList != null) {
            for (Integer articleId : articleIdList) {
                realmArticleIdList.addArticleId(articleId);
            }
        }
        realm.commitTransaction();
    }

    public static ArticleIdList getArticleIdList() {
        Realm realm = Realm.getDefaultInstance();

        RealmArticleIdList realmArticleIdList = realm.where(RealmArticleIdList.class).equalTo("id", 0).findFirst();
        if (realmArticleIdList == null) {
            return null;
        }
        ArticleIdList list = new ArticleIdList();
        list.articleIds = new ArrayList<>();
        for (Integer articleId : realmArticleIdList.getArticleIdList()) {
            list.articleIds.add(articleId);
        }
        list.lastUpdated = realmArticleIdList.getLastUpdated();
        return list;
    }

    public static void insertArticle(Article article) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
//        RealmArticle realmArticle = toRealm(article);
//        realm.copyToRealm(realmArticle);
        RealmArticle realmArticle = realm.createObject(RealmArticle.class, article.id);
        realmArticle.setBy(article.by);
        realmArticle.setScore(article.score);
        realmArticle.setType(article.type);
        realmArticle.setUrl(article.url);
        realmArticle.setText(article.text);
        realmArticle.setTime(article.time);
        realmArticle.setTitle(article.title);
        realmArticle.setKids(new RealmList<>());
        if (article.kids != null) {
            for (Integer kid : article.kids) {
                realmArticle.addKid(kid);
            }
        }
        realm.commitTransaction();
    }

    public static Article getArticle(int articleId) {
        Realm realm = Realm.getDefaultInstance();

        RealmArticle realmArticle = realm.where(RealmArticle.class).equalTo("id", articleId).findFirst();
        if (realmArticle == null) {
            return null;
        }
        return toNonRealm(realmArticle);
    }

    public static Article toNonRealm(RealmArticle realmArticle) {

        Article article = new Article();
        article.id = realmArticle.getId();
        article.title = realmArticle.getTitle();
        article.by = realmArticle.getBy();
        article.score = realmArticle.getScore();
        article.type = realmArticle.getType();
        article.url = realmArticle.getUrl();
        article.text = realmArticle.getText();
        article.time = realmArticle.getTime();
        article.kids = new ArrayList<>();
        for (Integer kid : realmArticle.getKids()) {
            article.kids.add(kid);
        }
        return article;

    }
}
