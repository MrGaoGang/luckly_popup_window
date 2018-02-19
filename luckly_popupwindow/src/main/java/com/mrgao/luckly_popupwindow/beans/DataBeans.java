package com.mrgao.luckly_popupwindow.beans;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by mr.gao on 2018/1/24.
 * Package:    com.mrgao.popupwindowviews.beans
 * Create Date:2018/1/24
 * Project Name:PopupWindowViews
 * Description:
 */

public class DataBeans {
    Bitmap mBitmap;
    String mData;
    int mTextColor = Color.WHITE;


    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    public DataBeans() {
    }

    public DataBeans(Bitmap bitmap, String data) {
        mBitmap = bitmap;
        mData = data;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }
}
