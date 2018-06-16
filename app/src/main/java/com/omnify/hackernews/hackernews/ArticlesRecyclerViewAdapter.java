package com.omnify.hackernews.hackernews;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omnify.hackernews.hackernews.ArticlesFragment.OnListFragmentInteractionListener;
import com.omnify.hackernews.hackernews.dummy.DummyContent.DummyItem;
import com.omnify.hackernews.hackernews.models.Article;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ArticlesRecyclerViewAdapter extends RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ViewHolder> {

    private final List<Article> mValues;
    private final OnListFragmentInteractionListener mListener;


    public ArticlesRecyclerViewAdapter(List<Article> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        Article article = mValues.get(position);
        Log.i("TAG", "bind view holder: "+ article.toString());
        holder.mVoteCount.setText(""+article.score);
        holder.mArticleTitle.setText(article.title);
        holder.mArticleSite.setText(article.url);
        holder.mArticleSubmittedByUser.setText(article.by);
        if (article.kids != null) {
            holder.mCommentCount.setText("" + article.kids.size());
        } else {
            holder.mCommentCount.setText("0");
        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        Log.i("TAG", "articles size: "+mValues.size());
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mVoteCount;
        public final TextView mArticleTitle;
        public final TextView mArticleSite;
        public final TextView mArticleSubmissionDate;
        public final TextView mArticleSubmittedByUser;
        public final TextView mCommentCount;
        public Article mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mVoteCount = view.findViewById(R.id.voteCount);
            mArticleTitle = view.findViewById(R.id.articleTitle);
            mArticleSite = view.findViewById(R.id.articleSite);
            mArticleSubmissionDate = view.findViewById(R.id.submissionDate);
            mArticleSubmittedByUser = view.findViewById(R.id.submittedByUser);
            mCommentCount = view.findViewById(R.id.comment_count);
        }
    }
}
