package com.omnify.hackernews.hackernews.models;

import com.google.gson.Gson;

public class Model {

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }
}
