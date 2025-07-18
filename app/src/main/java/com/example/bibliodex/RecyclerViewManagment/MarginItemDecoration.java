package com.example.bibliodex.RecyclerViewManagment;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class MarginItemDecoration extends RecyclerView.ItemDecoration {
    private final int margin;

    public MarginItemDecoration(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = margin;
        outRect.bottom = margin;
        outRect.left = margin;
        outRect.right = margin;
    }
}
