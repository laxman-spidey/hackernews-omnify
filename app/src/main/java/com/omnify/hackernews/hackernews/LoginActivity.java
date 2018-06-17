package com.omnify.hackernews.hackernews;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends BaseActivity implements FirebaseAuth.AuthStateListener, OnCompleteListener<AuthResult> {
    private static final int RC_GOOGLE_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    public FirebaseAuth mAuth;
    Button phoneLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        setupGoogleLogin();
        phoneLogin = findViewById(R.id.phone_login_button);
        phoneLogin.setOnClickListener(v -> {
            openPhoneLoginDialog();
        });
    }

    private void openPhoneLoginDialog()
    {

    }

    private void setupGoogleLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, connectionResult -> Toast.makeText(LoginActivity.this, "Connection failed.", Toast.LENGTH_SHORT).show())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        Button signInButton = findViewById(R.id.google_login_button);
        signInButton.setOnClickListener(v -> googleSignIn());
    }

    private void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showProgress();
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.i(TAG, "OnActivityResult: google signin" + result.getSignInAccount());
            if (result.isSuccess()) {
                Log.i(TAG, "OnActivityResult: google sign in success ");
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                hideProgress();
                Toast.makeText(getContext(), "Something went wrong while logging in. Please try again.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "OnActivityResult: google sign in failed ");
            }
        }
    }

    private void showMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        hideProgress();
        finish();
        startActivity(intent);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential googleCredentials = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        Log.d(TAG, "firebaseAuthWithGoogle googleCredentials:" + googleCredentials);

        mAuth.signInWithCredential(googleCredentials)
                .addOnCompleteListener(this, this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if (FirebaseAuth.getInstance() != null) {
//            if(FirebaseAuth.getInstance().getCurrentUser() != null)
//            {
//                showMainActivity();
//            }
        }
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            showMainActivity();
            finish();

        }
    }
}