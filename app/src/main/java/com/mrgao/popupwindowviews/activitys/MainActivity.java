package com.mrgao.popupwindowviews.activitys;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mrgao.luckly_popupwindow.LucklyPopopWindow;
import com.mrgao.luckly_popupwindow.beans.DataBeans;
import com.mrgao.luckly_popupwindow.utils.LinnerItemDivider;
import com.mrgao.luckly_popupwindow.utils.ScreenUtils;
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
         //initData();
        initBottomData();
    }


    private void initData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("数据" + i);
        }
        mAdapter.addAll(strings);
        mLucklyPopopWindow = new LucklyPopopWindow(this);

        mLucklyPopopWindow.setViewMargin(false, 20, 0, 0, 0);
        mLucklyPopopWindow.setViewPadding(0, 20, 10, 10);
        //给popupWindow添加数据
       // mLucklyPopopWindow.setData(getResources().getStringArray(R.array.popupArray), new int[]{R.mipmap.add, R.mipmap.delete,R.mipmap.modify,R.mipmap.update});
        mLucklyPopopWindow.setData(getData());

        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, int position) {
                //必须设置宽度
                mLucklyPopopWindow.setWidth(ScreenUtils.dp2px(getApplicationContext(),160));
                //添加分割线(可选)
                mLucklyPopopWindow.addItemDecoration(LucklyPopopWindow.VERTICAL, Color.GRAY, 1);
                //设置image不显示(可选)
                //mLucklyPopopWindow.setImageDisable(true);
                //设置image的大小(可选)
                mLucklyPopopWindow.setImageSize(20, 20);

                //监听事件
                mLucklyPopopWindow.setOnItemClickListener(new LucklyPopopWindow.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {


                        //显示popopWindow

                        mLucklyPopopWindow.dismiss();
                    }
                });

                mLucklyPopopWindow.showAtLocation(getWindow().getDecorView(), view);
            }
        });
    }

    private void initBottomData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("数据" + i);
        }
        mAdapter.addAll(strings);
        mLucklyPopopWindow = new LucklyPopopWindow(this);

        //mLucklyPopopWindow.setViewMargin(true, 40, 40, 10, 10);

        mLucklyPopopWindow.setViewPadding(20,0,20,0);
        //给popupWindow添加数据
        mLucklyPopopWindow.setRadius(18);
        mLucklyPopopWindow.setDarkBackgroundDegree(0.3f);
        //添加数据

        mLucklyPopopWindow.setData(getData());
        //添加分割线
        mLucklyPopopWindow.addItemDecoration(LucklyPopopWindow.VERTICAL,getResources().getColor(android.R.color.darker_gray),1);
       //点击事件
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mLucklyPopopWindow.showInBottom(getWindow().getDecorView());
                mLucklyPopopWindow.setOnItemClickListener(new LucklyPopopWindow.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
                        mLucklyPopopWindow.dismiss();
                    }
                });
            }
        });



    }

    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }


    private List<DataBeans> getData(){
        List<DataBeans> list=new ArrayList<>();
        DataBeans search=new DataBeans();
        search.setData("查询");
        search.setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.update));
        search.setTextColor(getResources().getColor(R.color.pink));
        list.add(search);


        DataBeans add=new DataBeans();
        add.setData("增加");
        add.setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.add));
        add.setTextColor(getResources().getColor(R.color.yellow));
        list.add(add);

        DataBeans modify=new DataBeans();
        modify.setData("修改");
        modify.setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.modify));
        modify.setTextColor(getResources().getColor(R.color.z));
        list.add(modify);

        DataBeans delete=new DataBeans();
        delete.setData("删除");
        delete.setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.delete));
        delete.setTextColor(getResources().getColor(R.color.red));
        list.add(delete);

        return list;
    }

}
