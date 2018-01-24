package com.mrgao.popupwindowviews.activitys;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mrgao.popupwindowviews.R;
import com.mrgao.popupwindowviews.utils.PopouBackView;

public class SelfJianAutoActivity extends AppCompatActivity {
    public String TAG = "SelfJianAutoActivity";
    LinearLayout mLinearLayout;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_jian_auto);
        mLinearLayout = (LinearLayout) findViewById(R.id.selflin);
        mImageView = (ImageView) findViewById(R.id.image);

        PopouBackView popouBackView = new PopouBackView(this);
        popouBackView.setContentPosition(new int[]{200, 300});
        popouBackView.setPosCenterPosition(new int[]{250, 200});
        popouBackView.setRadius(20);
        popouBackView.setPosViewHeight(80);
        popouBackView.setTranWidth(30);
        popouBackView.setViewWidth(200);
        popouBackView.setViewHeight(300);
        popouBackView.setShowDown(true);
        popouBackView.setTranHeight(30);


        Bitmap bitmap = popouBackView.convertViewToBitmap();
        if (bitmap == null) {
            Log.i(TAG, bitmap + "Bitmap是空");
        } else {
            Log.i(TAG, bitmap + "Bitmap的宽度" + bitmap.getWidth()+"  高度"+bitmap.getHeight());
        }
        Drawable drawable = new BitmapDrawable(null, bitmap);
        if (bitmap == null) {
            Log.i(TAG, "Drawable是空");
        } else {
            Log.i(TAG, "Drawable的宽度" + drawable.getIntrinsicWidth());
        }
        mImageView.setImageBitmap(bitmap);
       // mImageView.setBackground(drawable);

    }
}
