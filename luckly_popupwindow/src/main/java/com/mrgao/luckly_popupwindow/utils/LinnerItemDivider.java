package com.mrgao.luckly_popupwindow.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mr.gao on 2018/1/12.
 * Package:    mrgao.com.recyclerviewtext.dividerModule.divider
 * Create Date:2018/1/12
 * Project Name:RecyclerViewText
 * Description:
 */

public class LinnerItemDivider extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private int mMargin;
    private Drawable mDivider;
    private int mOrientation;
    private int mLineHeight;
    private Paint mPaint;

    public LinnerItemDivider(Context context, int orientation) {
        TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();

        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("无效的参数");
        }

        mOrientation = orientation;
    }

    public LinnerItemDivider(int orientation, int color, int lineHeight) {
        mOrientation = orientation;

        mPaint = new Paint();
        mPaint.setColor(color);
        mLineHeight = lineHeight;
        mPaint.setStrokeWidth(lineHeight);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == HORIZONTAL_LIST) {
            drawHo(c, parent);
        } else {
            drawVertical(c, parent);
        }
    }



    private void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        int left = recyclerView.getPaddingLeft();
        int right = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int count = recyclerView.getChildCount();
        for (int i = 0; i < count-1; i++) {
            View view = recyclerView.getChildAt(i);
            //获取到每一个item的margin的值

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view
                    .getLayoutParams();

            int top = view.getBottom() + params.bottomMargin;

            if (mDivider != null) {
                int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            } else if (mPaint != null) {
                int bottom = top + mLineHeight;
                canvas.drawRect(left, top, right, bottom, mPaint);
            }

        }
    }


    private void drawHo(Canvas canvas, RecyclerView recyclerView) {
        int top = recyclerView.getPaddingTop();
        int bottom = recyclerView.getHeight() - recyclerView.getPaddingBottom();
        int count = recyclerView.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = recyclerView.getChildAt(i);
            //获取到每一个item的margin的值
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;

            if (mDivider != null) {
                int right = left + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            } else if (mPaint != null) {
                int right = left + mLineHeight;
                canvas.drawRect(left, top, right, bottom, mPaint);
            }

        }
    }

    /**
     * 调用getItemOffset方法来计算每个Item的Decoration合适的尺寸
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (mDivider != null) {
            if (mOrientation == HORIZONTAL_LIST) {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            } else {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            }
        } else {
            if (mOrientation == HORIZONTAL_LIST) {
                outRect.set(0, 0, mLineHeight, 0);
            } else {
                outRect.set(0, 0, 0, mLineHeight);
            }
        }

    }
}
