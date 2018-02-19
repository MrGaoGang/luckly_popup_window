package com.mrgao.luckly_popupwindow.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by mr.gao on 2018/2/14.
 * Package:    com.mrgao.luckly_popupwindow.utils
 * Create Date:2018/2/14
 * Project Name:PopupWindowViews
 * Description:
 */

public class RoundRadiusView extends View {
    private Paint mPaint;
    private int mBackColor = Color.WHITE;
    private int mRadius = 20;
    private int mWidth;
    private int mHeight;
    private RectF mRectF;

    public RoundRadiusView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);

        mRectF = new RectF();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mPaint.setColor(mBackColor);
        mRectF.set(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint);

    }

    /**
     * 将view转换成bitmap
     *
     * @return
     */
    public Bitmap convertViewToBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
        return bitmap;
    }

    public void setBackColor(int backColor) {
        mBackColor = backColor;

    }

    public void setRadius(int radius) {
        mRadius = radius;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public void setHeight(int height) {
        mHeight = height;
    }
}
