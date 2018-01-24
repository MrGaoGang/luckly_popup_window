package com.mrgao.popupwindowviews.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mrgao.popupwindowviews.LucklyPopopWindow;
import com.mrgao.popupwindowviews.R;

public class ShowDownActivity extends AppCompatActivity {

    LucklyPopopWindow mLucklyPopopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_down);
        ((Button) findViewById(R.id.show)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initView();
            }
        });

    }

    private void initView() {
        mLucklyPopopWindow = new LucklyPopopWindow(this);
       // mLucklyPopopWindow.setData(getResources().getStringArray(R.array.popupArray));
        mLucklyPopopWindow.setOnItemClickListener(new LucklyPopopWindow.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(ShowDownActivity.this, "点击的位置" + position, Toast.LENGTH_SHORT).show();
                mLucklyPopopWindow.dismiss();
            }
        });
        mLucklyPopopWindow.setWidth(300);
        mLucklyPopopWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }
}
