package com.omnify.hackernews.hackernews.RESTModels;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.omnify.hackernews.hackernews.firebaseModels.ArticlesModel;

public class VolleyModel {
    public Context context;
    public static String TAG = VolleyModel.class.getSimpleName();
    public static final String SERVER_PATH = "https://hacker-news.firebaseio.com/v0/";
    public static final String TOP_STORIES = "topstories.json";
    public static final String STORY_ITEM = "item/";
    public static final String COMMENT_ITEM = "item/";
    public static final String JSON = ".json";


    private static RequestQueue requestQueue ;

    public static RequestQueue getInstanceRequestQueue(Context context)
    {
        if(requestQueue  == null)
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        return  requestQueue;
    }

    public Context getContext()
    {
        if (context == null)
        {
            throw new RuntimeException("Context is null, send context before getting it.");
            //            return CrafterApplication.getContext();
        }
        return context;
    }
    public void setContext(Context context)
    {
        this.context = context;
    }



}
