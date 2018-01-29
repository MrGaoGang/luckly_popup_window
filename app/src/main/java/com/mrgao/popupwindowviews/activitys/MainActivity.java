package com.mrgao.popupwindowviews.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mrgao.luckly_popupwindow.LucklyPopopWindow;
import com.mrgao.luckly_popupwindow.utils.LinnerItemDivider;
import com.mrgao.popupwindowviews.R;
import com.mrgao.popupwindowviews.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    LucklyPopopWindow mLucklyPopopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self);

        mRecyclerView = $(R.id.dividerRecycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.addItemDecoration(new LinnerItemDivider(LinnerItemDivider.VERTICAL_LIST, Color.RED, 1));
        mAdapter = new RecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        initData();
    }


    private void initData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("数据" + i);
        }
        mAdapter.addAll(strings);
        mLucklyPopopWindow = new LucklyPopopWindow(this);
        //给popupWindow添加数据
        mLucklyPopopWindow.setData(getResources().getStringArray(R.array.popupArray), new int[]{R.mipmap.add, R.mipmap.delete, R.mipmap.modify, R.mipmap.update});

        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, int position) {
                //必须设置宽度
                mLucklyPopopWindow.setWidth(150);
                //监听事件
                mLucklyPopopWindow.setOnItemClickListener(new LucklyPopopWindow.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        //添加分割线(可选)
                        mLucklyPopopWindow.addItemDecoration(LucklyPopopWindow.VERTICAL,Color.GRAY,1);
                        //设置image不显示(可选)
                         mLucklyPopopWindow.setImageDisable(true);
                        //设置image的大小(可选)
                        mLucklyPopopWindow.setImageSize(20,20);
                        //显示popopWindow

                        mLucklyPopopWindow.dismiss();
                    }
                });

                mLucklyPopopWindow.show(getWindow().getDecorView(), view);
            }
        });
    }



    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

}
