package com.omnify.hackernews.hackernews.RESTModels;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.omnify.hackernews.hackernews.firebaseModels.ResponseListener;
import com.omnify.hackernews.hackernews.models.Comment;

public class CommentsModel extends VolleyModel {

    public void getComment(int commentId, ResponseListener listener) {

        Comment commentOffline = com.omnify.hackernews.hackernews.realmModels.CommentsModel.getComment(commentId);
        if(commentOffline != null) {

            Log.i(TAG, "offline - " +commentOffline.toString());
            listener.onResponseRecieved(new ResponseListener.Response(true, commentOffline));
            return;
        }


        String url = SERVER_PATH + COMMENT_ITEM + commentId + JSON;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    Log.i(TAG, response.toString());
                    Comment comment  = new Gson().fromJson(response.toString(), Comment.class);;
                    listener.onResponseRecieved(new ResponseListener.Response(true, comment));
                    com.omnify.hackernews.hackernews.realmModels.CommentsModel.insertComment(comment);
                    Log.i("TAG", response.toString());
                }, error -> {
                    Log.i(TAG, error.getMessage());
                    listener.onResponseRecieved(new ResponseListener.Response(false, null));
                });
        getInstanceRequestQueue(getContext()).add(jsonObjectRequest);
    }



    private static CommentsModel singleton = null;
    public static CommentsModel getInstance(Context context) {
        if (singleton == null) {
            singleton = new CommentsModel(context);
        }
        return singleton;
    }
    public static String TAG = CommentsModel.class.getSimpleName();

    public CommentsModel(Context context) {
        setContext(context);
    }



}
