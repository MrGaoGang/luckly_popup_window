package com.mrgao.luckly_popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.mrgao.luckly_popupwindow.adapter.ListDataAdapter;
import com.mrgao.luckly_popupwindow.beans.DataBeans;
import com.mrgao.luckly_popupwindow.utils.LinnerItemDivider;
import com.mrgao.luckly_popupwindow.utils.PopouBackView;
import com.mrgao.luckly_popupwindow.utils.PopupWindowUtils;
import com.mrgao.luckly_popupwindow.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.gao on 2018/1/24.
 * Package:    com.mrgao.popupwindowviews
 * Create Date:2018/1/24
 * Project Name:PopupWindowViews
 * Description:
 */

public class LucklyPopopWindow extends PopupWindow {
    public static final int LOCATION_STATE_LEFT = 0;
    public static final int LOCATION_STATE_CENTER = 1;
    public static final int LOCATION_STATE_RIGHT = 2;

    private Context mContext;
    private View mContentView;//PopupWindow的contentView
    private RecyclerView mRecyclerView;
    private ListDataAdapter mAdapter;

    private int mTriangleWidth = 30;//三角形的宽度
    private int mTrianleHeight = 20;//三角形的高度
    private int mRadius = 20;//圆角的半径
    private int mTextColor = Color.BLACK;//字体的颜色
    private int mBackgroundColor = Color.WHITE;//背景颜色

    private float mDarkBackgroundDegree = 0.6f;//背景为灰色的程度


    public LucklyPopopWindow(Context context) {
        super(context);
        mContext = context;
        initContentView();
    }


    /**
     * 初始化contentView
     */
    private void initContentView() {
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.popup_layout, null);
        mRecyclerView = (RecyclerView) mContentView.findViewById(R.id.listView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new LinnerItemDivider(mContext, LinnerItemDivider.VERTICAL_LIST));
        mAdapter = new ListDataAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setTextColor(mTextColor);

        setContentView(mContentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setFocusable(true);
        setAnimationStyle(R.style.AlphaPopupWindow);
        setBackgroundDrawable(new ColorDrawable());
        setOutsideTouchable(true);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });

    }


    /**
     * 添加数据
     *
     * @param strings
     */
    public void setData(DataBeans[] strings) {
        if (mAdapter != null) {
            mAdapter.setData(strings);
        }
    }

    public void setData(String[] data, int[] images) {
        List<DataBeans> beansList=new ArrayList<>();
        if (data.length == images.length) {
            Bitmap bitmap=null;
            for (int i = 0; i < images.length; i++) {
                bitmap= BitmapFactory.decodeResource(mContext.getResources(),images[i]);
                DataBeans dataBeans=new DataBeans(bitmap,data[i]);
                beansList.add(dataBeans);
            }
            setData(beansList);
        }
    }

    public void setData(String[] data, Bitmap[] images) {
        List<DataBeans> beansList=new ArrayList<>();
        if (data.length == images.length) {
            Bitmap bitmap=null;
            for (int i = 0; i < images.length; i++) {
                bitmap=images[i];
                DataBeans dataBeans=new DataBeans(bitmap,data[i]);
                beansList.add(dataBeans);
            }
            setData(beansList);
        }
    }

    /**
     * 添加数据
     *
     * @param list
     */
    public void setData(List<DataBeans> list) {
        if (mAdapter != null) {
            mAdapter.setData(list);
        }
    }

    @Override
    public View getContentView() {
        return mContentView;
    }

    /**
     * 设置宽度
     *
     * @param widthDp
     */
    @Override
    public void setWidth(int widthDp) {
        super.setWidth(ScreenUtils.dp2px(mContext, widthDp));
        update();
    }

    /**
     * 设置背景颜色
     *
     * @param backgroundColor
     */
    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
    }

    /**
     * 设置背景为灰色的程度
     *
     * @param darkBackgroundDegree
     */
    public void setDarkBackgroundDegree(float darkBackgroundDegree) {
        if (darkBackgroundDegree >= 0.0f && darkBackgroundDegree <= 1.0f)
            mDarkBackgroundDegree = darkBackgroundDegree;
    }

    /**
     * 设置字体的颜色
     *
     * @param textColor
     */
    public void setTextColor(int textColor) {
        mTextColor = textColor;
        mAdapter.setTextColor(mTextColor);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 改变背景颜色
     */
    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgcolor;
        ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) mContext).getWindow().setAttributes(lp);

    }

    /**
     * 添加监听事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(LucklyPopopWindow.OnItemClickListener onItemClickListener) {
        if (mAdapter != null) {
            mAdapter.setOnItemClickListener(onItemClickListener);
        }
    }


    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public ListDataAdapter getAdapter() {
        return mAdapter;
    }

    public int getTriangleWidth() {
        return mTriangleWidth;
    }

    public int getTrianleHeight() {
        return mTrianleHeight;
    }

    public int getRadius() {
        return mRadius;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public float getDarkBackgroundDegree() {
        return mDarkBackgroundDegree;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * 在positionView的位置显示popupWindow;
     * 显示的时候 首先获取到背景View;然后将其设置为背景图片
     *
     * @param positionView
     */
    public void show(View parentView, View positionView) {
        int[] contentPosition = PopupWindowUtils.calculatePopupWindowPos(mContentView, positionView, mTrianleHeight);
        int[] centerPosition = PopupWindowUtils.getPositionViewCenterPos(positionView);

        PopouBackView popouBackView = new PopouBackView(mContext);
        popouBackView.setContentPosition(contentPosition);
        popouBackView.setPosCenterPosition(centerPosition);
        popouBackView.setRadius(mRadius);
        popouBackView.setPosViewHeight(positionView.getMeasuredHeight());
        popouBackView.setTranWidth(mTriangleWidth);
        popouBackView.setViewWidth(mContentView.getMeasuredWidth());
        popouBackView.setViewHeight(mContentView.getMeasuredHeight());
        popouBackView.setShowDown(PopupWindowUtils.isShowDown(mContentView, positionView, mTrianleHeight));
        popouBackView.setTranHeight(mTrianleHeight);
        popouBackView.setBackColor(mBackgroundColor);
        Bitmap bitmap = popouBackView.convertViewToBitmap();
        Drawable drawable = new BitmapDrawable(null, bitmap);
        setBackgroundDrawable(drawable);
        darkenBackground(mDarkBackgroundDegree);//设置背景框为灰色


        showAtLocation(parentView, Gravity.TOP | Gravity.START, contentPosition[0], contentPosition[1]);

    }


    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }
}
