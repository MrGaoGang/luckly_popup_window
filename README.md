# luckly_popup_window
PupopWindow动态获取显示的位置，并添加指示箭头
<br>
效果图<br>
![](https://github.com/MrGaoGang/luckly_popup_window/blob/master/images/image.gif) ![](https://github.com/MrGaoGang/luckly_popup_window/blob/master/images/showbottom.gif)

## 引用包 [![](https://jitpack.io/v/mrgaogang/luckly_popup_window.svg)](https://jitpack.io/#mrgaogang/luckly_popup_window)

 Step 1.在根 build.gradle中添加如下依赖<br>
```Java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. 在build.gradle中添加如下依赖<br>
```Java
 dependencies {
	        compile 'com.github.mrgaogang:luckly_popup_window:v1.4.0'
	 }
```
# 常用的方法

### 1、添加数据<br>
    添加数据的时候，内容和图片的个数应该相同；如果不需要添加图片的话，那么使用第一个和第四个方法，传递的Bitmap=null即可。
```Java
     void setData(DataBeans[] strings);
     void setData(String[] data, int[] images);
     void setData(String[] data, Bitmap[] images);
     void setData(List<DataBeans> list);
```

### 2、设置LucklyPopupWindow的宽度（必须设置）<br>
    LucklyPopupWindow的宽度（必须设置);设置的单位是dp。
```Java
     void setWidth(int widthDp);
```

### 3、给每一个Item添加分割线<br>
    默认的情况是没有分割线的。需要调用以下方法。
```Java
     //可以自己添加RecyclerView的分割线
     addItemDecoration(RecyclerView.ItemDecoration itemDecoration);
     //使用内部封装好了的分割线，传入的参数分别是：方向，颜色，分割线的宽
     addItemDecoration(int oritation, int color, int lineHeight);
```


### 4、设置背景颜色<br>
    也就是设置三角形和矩形框的背景颜色
```Java
     setBackgroundColor(int backgroundColor);
```

### 5、设置PopupWindow显示时Activity其余部分显示灰色程度<br>
    取值范围0.0<=darkBackgroundDegree<=1.0f
```Java
     setDarkBackgroundDegree(float darkBackgroundDegree);
```
### 6、设置字体的颜色和大小<br>
```Java
     setTextColor(int textColor);
     setTextSize(int textSize)；
```

### 7、设置图片不显示以及设置图片大小<br>
```Java
     setImageDisable(boolean imageDisable);
     setImageSize(int widthDp,int heightDp);
```

### 8、添加监听事件<br>
```Java
     void setOnItemClickListener(LucklyPopopWindow.OnItemClickListener onItemClickListener);
```

### 9、设置箭头的宽,高,圆角矩形的半径<br>
```Java
     void setTriangleWidth(int triangleWidth);
     void setTrianleHeight(int trianleHeight);
     void setRadius(int radius);
```

### 10、在某个View下/上显示（自动判断上下）<br>
    注意：这个方法必须最后调用。
```Java
    void showAtLocation(View parentView, View positionView);
```

### 11、模仿ios底部弹窗

```Java
	mLucklyPopopWindow.showInBottom(getWindow().getDecorView());
```

# LucklyPopouWindow的使用方法。
   请见:
   https://github.com/MrGaoGang/luckly_popup_window
   欢迎Star

```Java
     mLucklyPopopWindow = new LucklyPopopWindow(this);
        //给popupWindow添加数据
        mLucklyPopopWindow.setData(getResources().getStringArray(R.array.popupArray), new int[]{R.mipmap.add, R.mipmap.delete, R.mipmap.modify, R.mipmap.update});

        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //必须设置宽度
                mLucklyPopopWindow.setWidth(150);
                //监听事件
                mLucklyPopopWindow.setOnItemClickListener(new LucklyPopopWindow.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(MainActivity.this, "点击的位置" + position, Toast.LENGTH_SHORT).show();
                        mLucklyPopopWindow.dismiss();
                    }
                });

                //添加分割线(可选)
                mLucklyPopopWindow.addItemDecoration(LucklyPopopWindow.VERTICAL,Color.GRAY,1);
                //设置image不显示(可选)
               // mLucklyPopopWindow.setImageDisable(true);
                //设置image的大小(可选)
                mLucklyPopopWindow.setImageSize(20,20);
                //显示popopWindow
                mLucklyPopopWindow.showAtLocation(getWindow().getDecorView(), view);

            }
        });
```
