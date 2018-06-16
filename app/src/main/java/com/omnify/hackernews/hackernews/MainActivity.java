package com.omnify.hackernews.hackernews;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.omnify.hackernews.hackernews.dummy.DummyContent;
import com.omnify.hackernews.hackernews.models.Article;

public class MainActivity extends BaseActivity implements ArticlesFragment.OnListFragmentInteractionListener {

    Fragment articlesFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        articlesFragment = ArticlesFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, articlesFragment).commit();

    }

    @Override
    public void onListFragmentInteraction(Article item) {

    }
}
