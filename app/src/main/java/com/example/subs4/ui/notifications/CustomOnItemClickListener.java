package com.example.subs4.ui.notifications;

import android.view.View;

public class CustomOnItemClickListener implements View.OnLongClickListener{
    private final int position;
    private final OnItemClickCallback onItemClickCallback;
    public CustomOnItemClickListener(int position, OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public boolean onLongClick(View view) {
        onItemClickCallback.onItemClicked(view, position);
        return true;
    }

    public interface OnItemClickCallback {
        void onItemClicked(View view, int position);
    }
}
