package com.mrgao.popupwindowviews.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mrgao.popupwindowviews.LucklyPopopWindow;
import com.mrgao.popupwindowviews.R;
import com.mrgao.popupwindowviews.adapter.RecyclerAdapter;
import com.mrgao.popupwindowviews.utils.LinnerItemDivider;

import java.util.ArrayList;
import java.util.List;

public class SelfActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Button mAddBtn, deleteBtn;
    private RecyclerAdapter mAdapter;
    LucklyPopopWindow mLucklyPopopWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self);
        mRecyclerView = $(R.id.dividerRecycler);
        mAddBtn = $(R.id.addBtn);
        deleteBtn = $(R.id.deleteBtn);
        onClick();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.addItemDecoration(new LinnerItemDivider(LinnerItemDivider.VERTICAL_LIST, Color.RED,1));
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
        mLucklyPopopWindow=new LucklyPopopWindow(this);
        mLucklyPopopWindow.setData(getResources().getStringArray(R.array.popupArray),new int[]{R.mipmap.more,R.mipmap.more,R.mipmap.more,R.mipmap.more});
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mLucklyPopopWindow.setWidth(200);//必须设置
                mLucklyPopopWindow.setOnItemClickListener(new LucklyPopopWindow.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(SelfActivity.this, "点击的位置" + position, Toast.LENGTH_SHORT).show();
                        mLucklyPopopWindow.dismiss();
                    }
                });
                mLucklyPopopWindow.show(getWindow().getDecorView(),view);

            }
        });
    }

    private void onClick() {
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.add("添加的数据"+mAdapter.getItemCount()+"",2);

            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.remove(2);
            }
        });
    }

    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

}
