package com.mrgao.popupwindowviews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mrgao.popupwindowviews.activitys.SelfActivity;
import com.mrgao.popupwindowviews.activitys.SelfJianAutoActivity;
import com.mrgao.popupwindowviews.activitys.ShowDownActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.downBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowDownActivity.class));
            }
        });
        ((Button)findViewById(R.id.selfBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SelfActivity.class));
            }
        });
        ((Button)findViewById(R.id.jiantou)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SelfJianAutoActivity.class));
            }
        });
    }
}
