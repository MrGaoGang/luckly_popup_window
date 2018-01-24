package com.mrgao.popupwindowviews.utils;

import android.view.View;

/**
 * Created by mr.gao on 2018/1/24.
 * Package:    com.mrgao.popupwindowviews.utils
 * Create Date:2018/1/24
 * Project Name:PopupWindowViews
 * Description:
 */

public class PopupWindowUtils {

    /**
     * 参数一个是显示内容的view,一个是要在哪个位置显示的view
     *
     * @param contentView
     * @param positionView
     * @return
     */
    public static int[] calculatePopupWindowPos(View contentView, View positionView, int triangleHeight) {
        int[] contentPos = new int[2];
        int[] positionPos = new int[2];
        //获取到哪个位置view在屏幕中 的位置
        positionView.getLocationOnScreen(positionPos);

        int screenWidth = ScreenUtils.getScreenWidth(positionView.getContext());

        int posViewHeight = positionView.getMeasuredHeight();
        int posViewWidth = positionView.getMeasuredWidth();

        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int contentViewWith = contentView.getMeasuredWidth();
        int contentViewHeight = contentView.getMeasuredHeight();

        boolean isShowDown = isShowDown(contentView,positionView,triangleHeight);
        if (isShowDown) {
            //如果positionView的位置在最左边，且宽度没有内容那么宽，那么就取一半，
            //为什么不取0，那是因为想给显示的popup左边留一部分控件
            if (positionPos[0] < contentViewWith) {
                contentPos[0] = positionPos[0] / 2;
            }
            //如果positionView的右边可以容得下PopupWindow，那么PopupWindow显示在positionView的中间
            else if (screenWidth - positionPos[0] > contentViewWith) {
                contentPos[0] = positionPos[0] - (contentViewWith - posViewWidth) / 2;
            } else {
                //表示在最右边，给右边留下posViewWidth/2的空白区域
                contentPos[0] = screenWidth - contentViewWith - posViewWidth / 2;
            }
            contentPos[1] = positionPos[1] + posViewHeight + triangleHeight;

        } else {
            //如果positionView的位置在最左边，且宽度没有内容那么宽，那么就取一半，
            //为什么不取0，那是因为想给显示的popup左边留一部分控件
            if (positionPos[0] < contentViewWith) {
                contentPos[0] = positionPos[0] / 2;
            }
            //如果positionView的右边可以容得下PopupWindow，那么PopupWindow显示在positionView的中间
            else if (screenWidth - positionPos[0] > contentViewWith) {
                contentPos[0] = positionPos[0] - (contentViewWith - posViewWidth) / 2;
            } else {
                //表示在最右边，给右边留下posViewWidth/2的空白区域
                contentPos[0] = screenWidth - contentViewWith - posViewWidth / 2;
            }
            contentPos[1] = positionPos[1] - contentViewHeight - triangleHeight;
        }

        return contentPos;
    }

    /**
     * 计算positionView的中心位置
     *
     * @param positionView
     * @return
     */
    public static int[] getPositionViewCenterPos(View positionView) {
        int[] positionPos = new int[2];
        //获取到哪个位置view在屏幕中 的位置
        positionView.getLocationOnScreen(positionPos);
        int posViewHeight = positionView.getMeasuredHeight();
        int posViewWidth = positionView.getMeasuredWidth();
        positionPos[0] = positionPos[0] + posViewWidth / 2;
        positionPos[1] = positionPos[1] + posViewHeight / 2;
        return positionPos;
    }

    /**
     * 是否在下方显示
     * @param contentView
     * @param positionView
     * @param triangleHeight
     * @return
     */
    public static boolean isShowDown(View contentView, View positionView, int triangleHeight) {

        int[] positionPos = new int[2];
        //获取到哪个位置view在屏幕中 的位置
        positionView.getLocationOnScreen(positionPos);
        int screenHeight = ScreenUtils.getScreenHeight(positionView.getContext());

        int posViewHeight = positionView.getMeasuredHeight();

        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int contentViewHeight = contentView.getMeasuredHeight();

        boolean isShowDown = (screenHeight - positionPos[1] - posViewHeight) > contentViewHeight + triangleHeight;
        if (isShowDown) {
            return true;
        } else {
            return false;
        }

    }
}
