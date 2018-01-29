package com.mrgao.luckly_popupwindow.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;


/**
 * Created by mr.gao on 2018/1/24.
 * Package:    com.mrgao.popupwindowviews.utils
 * Create Date:2018/1/24
 * Project Name:PopupWindowViews
 * Description:
 */

public class PopouBackView extends View {
    private Paint mPaint;

    private boolean isShowDown;//是否显示在下方
    private int[] contentPosition;//popopwindow的左上角位置
    private int viewWidth;//popopwindow的宽度
    private int viewHeight;//popopwindow的高度
    private int[] posCenterPosition;//posView的中心点的坐标
    private int mRadius = 10;//popopwindow圆角半径
    private int mPosViewHeight;//posView的高度
    private int mTranWidth;//三角形的宽
    private int mTranHeight;//三角形的高
    private int mBackColor;
    private Path mPath;

    private int mDrawMargin = 0;

    public PopouBackView(Context context) {
        super(context);
        init();

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(mBackColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mPaint.setColor(mBackColor);

        int triangleX = posCenterPosition[0] - contentPosition[0];
        int halfTriangleWidth = mTranWidth / 2;
        if (isShowDown) {
            canvas.drawRoundRect(new RectF(mDrawMargin, mDrawMargin + mTranHeight, canvas.getWidth() - mDrawMargin, canvas.getHeight() - mDrawMargin - ScreenUtils.dp2px(getContext(), 10)), mRadius, mRadius, mPaint);

            mPath.moveTo(triangleX, mDrawMargin);
            mPath.lineTo(triangleX + halfTriangleWidth, mDrawMargin + mTranHeight * 2);
            mPath.lineTo(triangleX - halfTriangleWidth, mDrawMargin + mTranHeight * 2);
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        } else {
            canvas.drawRoundRect(new RectF(mDrawMargin, mDrawMargin, canvas.getWidth() - mDrawMargin, canvas.getHeight() - mDrawMargin - mTranHeight), mRadius, mRadius, mPaint);

            mPath.moveTo(triangleX, canvas.getHeight() - mDrawMargin);
            mPath.lineTo(triangleX + halfTriangleWidth, canvas.getHeight() - mDrawMargin - mTranHeight * 2);
            mPath.lineTo(triangleX - halfTriangleWidth, canvas.getHeight() - mDrawMargin - mTranHeight * 2);
            mPath.close();
            canvas.drawPath(mPath, mPaint);

        }

    }


    public void setBackColor(int backColor) {
        mBackColor = backColor;
    }

    /**
     * 将view转换成bitmap
     *
     * @return
     */
    public Bitmap convertViewToBitmap() {

        Bitmap bitmap = Bitmap.createBitmap(viewWidth, viewHeight + mPosViewHeight + mTranHeight + 2 * mDrawMargin, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
        return bitmap;
    }

    public void setShowDown(boolean showDown) {
        isShowDown = showDown;
    }

    public void setContentPosition(int[] contentPosition) {
        this.contentPosition = contentPosition;
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public void setPosCenterPosition(int[] posCenterPosition) {
        this.posCenterPosition = posCenterPosition;
    }

    public void setRadius(int radius) {
        mRadius = radius;
    }


    public void setPosViewHeight(int posViewHeight) {
        mPosViewHeight = posViewHeight;
    }

    public void setTranWidth(int tranWidth) {
        mTranWidth = tranWidth;
    }

    public void setTranHeight(int tranHeight) {
        mTranHeight = tranHeight;
    }
}
