package com.example.fragmentpractise;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements ViewPager.OnPageChangeListener,View.OnTouchListener{

    private ViewPager mViewPager;
    private View view1,view2,view3;
    private List<View> list;
    private LinearLayout pointLayout;
    private ImageView[] images;
    private boolean flag;
    private int count;
    private int currentItem;
    private int lastX = 0; // 获得当前的坐标
    private int currentPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        pointLayout = (LinearLayout) findViewById(R.id.llayout);
        count = pointLayout.getChildCount();
        for(int i = 0;i < count; i++){
            images[i] = (ImageView) pointLayout.getChildAt(i);
            images[i].setEnabled(true);
            images[i].setTag(i);
        }
        currentItem = 0;
        images[currentItem].setEnabled(false);

        mViewPager = (ViewPager) findViewById(R.id.viewpage);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setOnTouchListener(this);

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        list  = new ArrayList<View>();
        SharedPreferences prefs = getSharedPreferences("load",MODE_PRIVATE);
        flag = prefs.getBoolean("loading_flag",false);
        if(!flag){
            initPages(inflater);
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this,MyActivity.class);
                    startActivity(intent);
                    finish();
                }
            },0);

        }

    }

    private void initPages(LayoutInflater inflater) {
        view1 = inflater.inflate(R.layout.loading,null);
        view1.setBackgroundResource(R.mipmap.loding_1);
        view2 = inflater.inflate(R.layout.loading,null);
        view2.setBackgroundResource(R.mipmap.loding_2);
        view3 = inflater.inflate(R.layout.loading,null);
        view3.setBackgroundResource(R.mipmap.loding_3);
        list.add(view1);
        list.add(view2);
        list.add(view3);
        mViewPager.setAdapter(pageAdapter);
    }

    PagerAdapter pageAdapter = new PagerAdapter() {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

    };

    private void setCurrentPoint(int position) {
        if(position < 0 || position > count -1 ||currentItem == position){
            return;
        }

        images[currentItem].setEnabled(true);
        images[position].setEnabled(false);
        currentItem = position;
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        setCurrentPoint(i);
    }



    @Override
    public void onPageScrollStateChanged(int i) {

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = (int) motionEvent.getX();
                break;
            case MotionEvent.ACTION_UP:
                if(!flag){
                    //  从最后一页向右滑动
                    if((lastX - motionEvent.getX() >100)&&(mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() -1)){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                SharedPreferences prefs = getSharedPreferences("loading",MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putBoolean("loading_flag",true);
                                editor.commit();

                                Intent intent = new Intent(MainActivity.this,MyActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        },0);
                    }
                }
                break;
        }
        return false;
    }

}
