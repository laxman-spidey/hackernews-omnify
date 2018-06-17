package com.omnify.hackernews.hackernews.RESTModels;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.omnify.hackernews.hackernews.firebaseModels.ResponseListener;
import com.omnify.hackernews.hackernews.models.Article;
import com.omnify.hackernews.hackernews.models.ArticleIdList;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;

public class ArticlesModel extends VolleyModel {

    private static ArticlesModel singleton = null;
    public static ArticlesModel getInstance(Context context) {
        if (singleton == null) {
            singleton = new ArticlesModel(context);
        }
        return singleton;
    }
    public static String TAG = ArticlesModel.class.getSimpleName();

    public ArticlesModel(Context context) {
        setContext(context);
    }

    public void getTopStories(ResponseListener listener) {

        //fetching offline data first
        ArticleIdList articleListOffline = com.omnify.hackernews.hackernews.realmModels.ArticlesModel.getArticleIdList();
        if(articleListOffline != null) {
            Log.i(TAG, "fetching from offline" );
            Log.i(TAG, articleListOffline.toString());
            listener.onResponseRecieved(new ResponseListener.Response(true, articleListOffline));
        }

        //requesting online data even after fetching offline data.
        String url = SERVER_PATH + TOP_STORIES;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, response -> {
                    Log.i(TAG, response.toString());
                    Type type = new TypeToken<List<Integer>>(){}.getType();
                    List<Integer> articles =  new Gson().fromJson(response.toString(), type);
                    com.omnify.hackernews.hackernews.realmModels.ArticlesModel.insertArticleIdList(articles);
                    ArticleIdList articleIdList = new ArticleIdList();
                    articleIdList.articleIds = articles;
                    articleIdList.lastUpdated = Calendar.getInstance().getTimeInMillis();
                    listener.onResponseRecieved(new ResponseListener.Response(true, articleIdList));
                }, error -> {

                    listener.onResponseRecieved(new ResponseListener.Response(false, null));
                });
        getInstanceRequestQueue(getContext()).add(jsonObjectRequest);
    }

    public void getArticle(int articleId, ResponseListener listener) {

        //check Realm before sending request
        Article articleOffline = com.omnify.hackernews.hackernews.realmModels.ArticlesModel.getArticle(articleId);
        if(articleOffline != null) {
            Log.i(TAG, "fetching from offline" );
            Log.i(TAG, articleOffline.toString());
            listener.onResponseRecieved(new ResponseListener.Response(true, articleOffline));
            return;
        }


        String url = SERVER_PATH + STORY_ITEM + articleId + JSON;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    Log.i(TAG, response.toString());
                    Article article = new Gson().fromJson(response.toString(), Article.class);
                    listener.onResponseRecieved(new ResponseListener.Response(true, article));
                    com.omnify.hackernews.hackernews.realmModels.ArticlesModel.insertArticle(article);
                    Log.i("TAG", response.toString());
                }, error -> {
                    listener.onResponseRecieved(new ResponseListener.Response(false, null));
                });
        getInstanceRequestQueue(getContext()).add(jsonObjectRequest);
    }


}
