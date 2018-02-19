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
import android.widget.TextView;

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
    private static final String TAG = "LucklyPopopWindow";
    private Context mContext;
    //PopupWindow的contentView
    private View mContentView;
    //RecyclerView
    private RecyclerView mRecyclerView;
    //Adapter
    private ListDataAdapter mAdapter;
    //三角形的宽度
    private int mTriangleWidth = 40;
    //三角形的高度
    private int mTrianleHeight = 30;
    //圆角的半径
    private int mRadius = 20;
    //字体的颜色
    private int mTextColor = Color.BLACK;
    //背景颜色
    private int mBackgroundColor = Color.WHITE;
    //背景为灰色的程度
    private float mDarkBackgroundDegree = 0.6f;
    //自绘制三角形圆角背景
    private PopouBackView mPopouBackView;
    //分割线横向布局
    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    //分割线纵向布局
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;

    public TextView mCancelTv;

    private List<DataBeans> mBeansList = new ArrayList<>();

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
        mCancelTv = (TextView) mContentView.findViewById(R.id.cancelTv);
        mCancelTv.setVisibility(View.GONE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ListDataAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setTextColor(mTextColor);

        setContentView(mContentView);


        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setFocusable(true);
        setAnimationStyle(R.style.AlphaPopupWindow);
        setOutsideTouchable(true);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });

        mCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    public void showInBottom(final View parentView) {
        mCancelTv.setVisibility(View.VISIBLE);
        int width = ScreenUtils.getScreenWidth(mContext) * 10 / 20;
        setWidth(width);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setAnimationStyle(R.style.BottomPopupWindow);
        ColorDrawable dw = new ColorDrawable(-00000);
        this.setBackgroundDrawable(dw);
        darkenBackground(mDarkBackgroundDegree);//设置背景框为灰色

        setImageDisable(true);
        showAtLocation(parentView, Gravity.BOTTOM, 0, 100);


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
        mBeansList.clear();
        if (data.length == images.length) {
            Bitmap bitmap = null;
            for (int i = 0; i < images.length; i++) {
                bitmap = BitmapFactory.decodeResource(mContext.getResources(), images[i]);
                DataBeans dataBeans = new DataBeans(bitmap, data[i]);
                mBeansList.add(dataBeans);
            }
            setData(mBeansList);
        }
    }

    public void setData(String[] data, Bitmap[] images) {
        mBeansList.clear();
        if (data.length == images.length) {
            Bitmap bitmap = null;
            for (int i = 0; i < images.length; i++) {
                bitmap = images[i];
                DataBeans dataBeans = new DataBeans(bitmap, data[i]);
                mBeansList.add(dataBeans);
            }
            setData(mBeansList);
        }
    }

    public void setData(String[] data) {
        mBeansList.clear();
        for (int i = 0; i < data.length; i++) {
            DataBeans dataBeans = new DataBeans();
            dataBeans.setData(data[i]);
            mBeansList.add(dataBeans);
        }
        setData(mBeansList);
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
     * 添加分割线
     *
     * @param itemDecoration
     */
    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    /**
     * 添加默认的分割线
     */
    public void addItemDecoration(int oritation, int color, int lineHeight) {
        mRecyclerView.addItemDecoration(new LinnerItemDivider(oritation, color, lineHeight));
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

    public void setTextSize(int textSize) {
        if (mAdapter != null) {
            mAdapter.setTextSize(textSize);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setCancelTextColor(int textColor) {
        mCancelTv.setVisibility(textColor);
    }

    public void setCancelTextSize(int size) {
        mCancelTv.setTextSize(size);
    }

    public void setCancelText(String text) {
        mCancelTv.setText(text);
    }

    /**
     * 设置不显示图片
     */
    public void setImageDisable(boolean imageDisable) {
        if (mAdapter != null) {
            mAdapter.setImageDisable(imageDisable);
        }

    }

    /**
     * 设置图片的大小
     *
     * @param widthDp
     * @param heightDp
     */
    public void setImageSize(int widthDp, int heightDp) {
        if (mAdapter != null) {
            mAdapter.setImageSize(widthDp, heightDp);
        }
    }

    /**
     * 改变背景颜色
     */
    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgcolor;
        if (bgcolor == 1) {
            ((Activity) mContext).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
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

    /**
     * 设置箭头的宽度
     *
     * @param triangleWidth
     */
    public void setTriangleWidth(int triangleWidth) {
        mTriangleWidth = triangleWidth;
        update();
    }

    /**
     * 设置剪头的高度
     *
     * @param trianleHeight
     */
    public void setTrianleHeight(int trianleHeight) {
        mTrianleHeight = trianleHeight;
        update();
    }

    /**
     * 设置圆角矩形半径
     *
     * @param radius
     */
    public void setRadius(int radius) {
        mRadius = radius;
        update();
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
     */
    public void showAtLocation(View parentView, View positionView) {
        int[] contentPosition = PopupWindowUtils.calculatePopupWindowPos(mContentView, positionView, mTrianleHeight, getWidth());
        int[] centerPosition = PopupWindowUtils.getPositionViewCenterPos(positionView);

        mRecyclerView.setBackground(null);
        mRecyclerView.setAlpha(1.0f);
        mPopouBackView = new PopouBackView(mContext);
        mPopouBackView.setContentPosition(contentPosition);
        mPopouBackView.setPosCenterPosition(centerPosition);
        mPopouBackView.setRadius(mRadius);
        mPopouBackView.setPosViewHeight(positionView.getMeasuredHeight());
        mPopouBackView.setViewWidth(getWidth());//注意这里传入的参数为popop的宽度
        mPopouBackView.setViewHeight(mContentView.getMeasuredHeight());
        mPopouBackView.setShowDown(PopupWindowUtils.isShowDown(mContentView, positionView, mTrianleHeight));
        mPopouBackView.setTranWidth(mTriangleWidth);
        mPopouBackView.setTranHeight(mTrianleHeight);
        mPopouBackView.setBackColor(mBackgroundColor);

        Bitmap bitmap = mPopouBackView.convertViewToBitmap();
        Drawable drawable = new BitmapDrawable(null, bitmap);
        update();
        setBackgroundDrawable(drawable);
        darkenBackground(mDarkBackgroundDegree);//设置背景框为灰色
        showAtLocation(parentView, Gravity.TOP | Gravity.START, contentPosition[0], contentPosition[1]);

    }

    public ViewGroup.LayoutParams setViewMargin(boolean isDp, int left, int right, int top, int bottom) {
        if (mRecyclerView == null) {
            return null;
        }

        int leftPx = left;
        int rightPx = right;
        int topPx = top;
        int bottomPx = bottom;
        ViewGroup.LayoutParams params = mRecyclerView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        //获取view的margin设置参数
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            marginParams = new ViewGroup.MarginLayoutParams(params);
        }

        //根据DP与PX转换计算值
        if (isDp) {
            leftPx = ScreenUtils.dp2px(mContext, left);
            rightPx = ScreenUtils.dp2px(mContext, right);
            topPx = ScreenUtils.dp2px(mContext, top);
            bottomPx = ScreenUtils.dp2px(mContext, bottom);
        }
        //设置margin
        marginParams.setMargins(leftPx, topPx, rightPx, bottomPx);

        mRecyclerView.setLayoutParams(marginParams);
        mCancelTv.setLayoutParams(marginParams);
        return marginParams;
    }

    public void setViewPadding(int left, int top, int right, int bottom) {
        if (mRecyclerView != null) {
            mRecyclerView.setPadding(left, top, right, bottom);
        }

    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }
}
