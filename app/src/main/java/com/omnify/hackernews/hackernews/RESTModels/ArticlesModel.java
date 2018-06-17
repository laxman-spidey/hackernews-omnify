package com.omnify.hackernews.hackernews.RESTModels;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.omnify.hackernews.hackernews.firebaseModels.ResponseListener;
import com.omnify.hackernews.hackernews.models.Article;

import java.lang.reflect.Type;
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
        String url = SERVER_PATH + TOP_STORIES;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, response -> {
                    Log.i(TAG, response.toString());
                    Type type = new TypeToken<List<Integer>>(){}.getType();
                    List<Integer> articles =  new Gson().fromJson(response.toString(), type);
                    listener.onResponseRecieved(new ResponseListener.Response(true, articles));
                }, error -> {
                    Log.i(TAG, error.getMessage());
                    listener.onResponseRecieved(new ResponseListener.Response(false, null));
                });
        getInstanceRequestQueue(getContext()).add(jsonObjectRequest);
    }

    public void getArticle(int articleId, ResponseListener listener) {
        String url = SERVER_PATH + STORY_ITEM + articleId + JSON;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    Log.i(TAG, response.toString());
                    Article article = new Gson().fromJson(response.toString(), Article.class);
                    listener.onResponseRecieved(new ResponseListener.Response(true, article));
                    Log.i("TAG", response.toString());
                }, error -> {
                    Log.i(TAG, error.getMessage());
                    listener.onResponseRecieved(new ResponseListener.Response(false, null));
                });
        getInstanceRequestQueue(getContext()).add(jsonObjectRequest);
    }


}
