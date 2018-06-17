package com.omnify.hackernews.hackernews;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omnify.hackernews.hackernews.RESTModels.ArticlesModel;
import com.omnify.hackernews.hackernews.models.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ArticlesFragment extends BaseFragment {

    private OnListFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    private List<Article> articles;

    public ArticlesFragment() {
    }

    public static ArticlesFragment newInstance() {
        ArticlesFragment fragment = new ArticlesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articles = new ArrayList<>();
        getArticleIdsList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        setSuccessView(view);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();

            recyclerView = (RecyclerView) view;
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new ArticlesRecyclerViewAdapter(articles, mListener));

        }
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Article item);
    }

    public void getArticleIdsList()
    {

        ArticlesModel.getInstance(getContext()).getTopStories(response -> {
            if(response.isOkay) {
                List<Integer> articleList = (List<Integer>)response.data;
                if(articleList.size() > 0 )
                {
                    onSuccess();
                    getArticles(articleList);
                }
                else {
                    onNoDataFound();
                }
            }
            else {
                onError();
            }
        });
    }

    public void getArticles(List<Integer> articleList) {
        int MAX_TOP_STORIES = 50;
        int articlesFetched = 1;
        for (Integer articleId : articleList) {
            if (articlesFetched++ > MAX_TOP_STORIES) {
                break;
            }
            ArticlesModel.getInstance(getContext()).getArticle(articleId, response -> {
                if (response.isOkay) {
                    articles.add((Article)response.data);
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            });
        }
    }

}
