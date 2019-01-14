package com.mm.movie.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerItemDecoration is used for showing spaces in all directions in recycler view.
 */
public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    private final int verticalItemSpace;
    private final int horizontalItemSpace;

    public RecyclerItemDecoration(int verticalItemSpace, int horizontalItemSpace) {
        this.verticalItemSpace = verticalItemSpace;
        this.horizontalItemSpace = horizontalItemSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = verticalItemSpace / 2;
        outRect.bottom = verticalItemSpace / 2;
        outRect.left = horizontalItemSpace / 2;
        outRect.right = horizontalItemSpace / 2;
    }
}
