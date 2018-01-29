package com.mrgao.luckly_popupwindow.utils;

import android.content.Context;
import android.graphics.Bitmap;
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
    public static int[] calculatePopupWindowPos(View contentView, View positionView, int triangleHeight, int viewWidth) {
        int[] contentPos = new int[2];
        int[] positionPos = new int[2];
        //获取到哪个位置view在屏幕中 的位置
        positionView.getLocationOnScreen(positionPos);

        int screenWidth = ScreenUtils.getScreenWidth(positionView.getContext());

        int posViewHeight = positionView.getMeasuredHeight();
        int posViewWidth = positionView.getMeasuredWidth();

        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);


        int contentViewHeight = contentView.getMeasuredHeight();

        boolean isShowDown = isShowDown(contentView, positionView, triangleHeight);
        if (isShowDown) {
            if (viewWidth <= posViewWidth) {
                contentPos[0] = positionPos[0] + (posViewWidth - viewWidth) / 2;
            }
            //如果positionView的位置在最左边，且宽度没有内容那么宽，那么就取一半，
            //为什么不取0，那是因为想给显示的popup左边留一部分控件
            else if (positionPos[0] < viewWidth) {
                contentPos[0] = positionPos[0] / 2;
            }
            //如果positionView的右边可以容得下PopupWindow，那么PopupWindow显示在positionView的中间
            else if (screenWidth - positionPos[0] > viewWidth) {
                contentPos[0] = positionPos[0] - Math.abs(viewWidth - posViewWidth) / 2;
            } else {
                //表示在最右边，给右边留下posViewWidth/2的空白区域
                contentPos[0] = screenWidth - viewWidth - posViewWidth / 2;
            }
            contentPos[1] = positionPos[1] + posViewHeight + triangleHeight;

        } else {
            //如果positionView的位置在最左边，且宽度没有内容那么宽，那么就取一半，
            //为什么不取0，那是因为想给显示的popup左边留一部分控件
            if (positionPos[0] < viewWidth) {
                contentPos[0] = positionPos[0] / 2;
            }
            //如果positionView的右边可以容得下PopupWindow，那么PopupWindow显示在positionView的中间
            else if (screenWidth - positionPos[0] > viewWidth) {
                contentPos[0] = positionPos[0] - Math.abs(viewWidth - posViewWidth) / 2;
            } else {
                //表示在最右边，给右边留下posViewWidth/2的空白区域
                contentPos[0] = screenWidth - viewWidth - posViewWidth / 2;
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
     *
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

    /**
     * 将背景毛玻璃化
     *
     * @param context
     * @param sentBitmap
     * @param radius
     * @return
     */
    public static Bitmap fastBlur(Context context, Bitmap sentBitmap, int radius) {

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int temp = 256 * divsum;
        int dv[] = new int[temp];
        for (i = 0; i < temp; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
                        | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);
        return (bitmap);
    }
}
