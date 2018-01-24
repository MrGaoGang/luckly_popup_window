package com.mrgao.luckly_popupwindow.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by mr.gao on 2018/1/24.
 * Package:    com.mrgao.popupwindowviews.utils
 * Create Date:2018/1/24
 * Project Name:PopupWindowViews
 * Description:
 */

public class ScreenUtils {


    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int dp2px(Context context, float dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()) + 0.5f);
    }
}
