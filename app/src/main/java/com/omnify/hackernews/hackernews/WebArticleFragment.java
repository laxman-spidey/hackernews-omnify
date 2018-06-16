package com.omnify.hackernews.hackernews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebArticleFragment extends Fragment {

    private static final String ARTICLE_URL = "ARTICLE_URL";

    private String articleUrl;
    private WebView webview;


    public WebArticleFragment() {
        // Required empty public constructor
    }

    public static WebArticleFragment newInstance(String articleUrl) {
        WebArticleFragment fragment = new WebArticleFragment();
        Bundle args = new Bundle();
        args.putString(ARTICLE_URL, articleUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            articleUrl = getArguments().getString(ARTICLE_URL);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_web_article, container, false);
        webview  = view.findViewById(R.id.article_webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ProgressDialog.hide(getContext());
            }

//
//            @Override
//            public void onLoadResource(WebView view, String url) {
//                super.onLoadResource(view, url);
//                ProgressDialog.hide(getContext());
//            }
        });
        webview.loadUrl(articleUrl);
        return view;
    }
}
