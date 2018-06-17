package com.omnify.hackernews.hackernews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omnify.hackernews.hackernews.dummy.DummyContent.DummyItem;
import com.omnify.hackernews.hackernews.models.Comment;

import java.util.List;

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder> {

    private final List<Comment> mValues;

    public CommentRecyclerViewAdapter(List<Comment> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setData(mValues.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCommentDateTime;
        public final TextView mCommentUser;
        public final TextView mCommentText;
        public Comment mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCommentDateTime = view.findViewById(R.id.commentDateTime);
            mCommentUser = view.findViewById(R.id.commentUser);
            mCommentText = view.findViewById(R.id.commentText);
        }

        public void setData(Comment comment) {
            mItem = comment;
            mCommentText.setText(comment.text);
            mCommentUser.setText(comment.by);
            mCommentDateTime.setText(""+comment.time);
        }

    }
}
