package com.omnify.hackernews.hackernews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.omnify.hackernews.hackernews.dummy.DummyContent;
import com.omnify.hackernews.hackernews.models.Article;

public class MainActivity extends BaseActivity implements ArticlesFragment.OnListFragmentInteractionListener {

    Fragment articlesFragment;
    ImageButton logoutButton;
    TextView lastUpdated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(v -> {
            logout();
        });
        lastUpdated = findViewById(R.id.lastUpdated);
        setSupportActionBar(toolbar);
        articlesFragment = ArticlesFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, articlesFragment).commit();

    }

    @Override
    public void onListFragmentInteraction(Article item) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("article", item.toString());
        startActivity(intent);

    }

    public void logout() {
        Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show();
        ProgressDialog.show(this);
        if (FirebaseAuth.getInstance() != null) {
            FirebaseAuth.getInstance().signOut();
            ProgressDialog.hide(this);
            finish();
//            showLoginScreen();
        } else {
            ProgressDialog.hide(this);
            Toast.makeText(this, "Unable to logout. Please try again", Toast.LENGTH_SHORT).show();
        }

    }

    public void showLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void setLastUpdated(long millis)
    {
        lastUpdated.setText("Updated "+DateUtil.getRelativeTime(millis));
    }

}
