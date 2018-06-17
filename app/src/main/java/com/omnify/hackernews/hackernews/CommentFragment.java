package com.omnify.hackernews.hackernews;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omnify.hackernews.hackernews.RESTModels.ArticlesModel;
import com.omnify.hackernews.hackernews.RESTModels.CommentsModel;
import com.omnify.hackernews.hackernews.models.Article;
import com.omnify.hackernews.hackernews.models.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends BaseFragment {

    private static final String ARG_ARTICLE_ID = "ARG_ARTICLE_ID";
    private int articleId;
    private List<Comment> comments = new ArrayList<>();
    RecyclerView recyclerView;

    public CommentFragment() {
    }

    public static CommentFragment newInstance(int articleId) {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ARTICLE_ID, articleId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            articleId = getArguments().getInt(ARG_ARTICLE_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_comment_list, container, false);
        setSuccessView(view);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();

            recyclerView = (RecyclerView) view;
//            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new CommentRecyclerViewAdapter(comments));
        }
        getCommentIdList(articleId);
        return rootView;
    }

    public void getCommentIdList(int articleId) {

        ArticlesModel.getInstance(getContext()).getArticle(articleId, response -> {
            if (response.isOkay) {
                Article article = (Article) response.data;
                if (article.kids != null) {
                    if (article.kids.size() > 0) {
                        getComments(article.kids);
                        onSuccess();
                    } else {
                        onNoDataFound();
                    }
                } else {
                    onNoDataFound();
                }
            } else {
                onError();
            }
        });
    }

    public void getComments(List<Integer> commentIds) {
        for (Integer commentId : commentIds) {
            CommentsModel.getInstance(getContext()).getComment(commentId, response -> {
                if (response.isOkay) {
                    comments.add((Comment) response.data);
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            });
        }
    }
}
