package com.example.fragmentpractise.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fragmentpractise.R;
import com.example.fragmentpractise.adapter.MyFragmentAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhf on 2015/9/17.
 */
public class MainmainActivity extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private HorizontalScrollView mHorzontalScrollView;
    private RadioGroup group;
    private RadioButton button1,button2,button3,button4;
    private ImageView imageView;
    private ViewPager pager;
    private View view;
    private List<Fragment> fragmentList;
    private MyFragmentAdapter adapter;
    private float mCurrentCheckedRadioLeft;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_main,null);
        initView();
        initListener();
        return view;
    }

    private void initListener() {
        group.setOnCheckedChangeListener(this);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ((RadioButton) group.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new FragmentA());
        fragmentList.add(new FragmentB());
        fragmentList.add(new FragmentC());
        fragmentList.add(new FragmentD());

        mHorzontalScrollView = (HorizontalScrollView) view.findViewById(R.id.horizontalscrollview);
        group = (RadioGroup) view.findViewById(R.id.radio);
        button1 = (RadioButton) view.findViewById(R.id.btn1);
        button2 = (RadioButton) view.findViewById(R.id.btn2);
        button3 = (RadioButton) view.findViewById(R.id.btn3);
        button4 = (RadioButton) view.findViewById(R.id.btn4);
        imageView = (ImageView) view.findViewById(R.id.image);
        pager = (ViewPager) view.findViewById(R.id.pager);

        adapter = new MyFragmentAdapter(getChildFragmentManager(),fragmentList);
        pager.setAdapter(adapter);

        button1.setChecked(true);
        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
       switch (checkedId){
           case R.id.btn1:
               imageTranslationAnimation(getResources().getDimension(R.dimen.rdo1));
               pager.setCurrentItem(0);
               break;
           case R.id.btn2:
                imageTranslationAnimation(getResources().getDimension(R.dimen.rdo2));
               pager.setCurrentItem(1);
               break;
           case R.id.btn3:
                imageTranslationAnimation(getResources().getDimension(R.dimen.rdo3));
               pager.setCurrentItem(2);
               break;
           case R.id.btn4:
                imageTranslationAnimation(getResources().getDimension(R.dimen.rdo4));
               pager.setCurrentItem(3);
               break;
           default:
               break;
        }
        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();
        mHorzontalScrollView.scrollTo((int) (mCurrentCheckedRadioLeft-getResources().getDimension(R.dimen.rdo2)),0);

    }

    private void imageTranslationAnimation(float f) {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft,f,0f,0f);
        animationSet.addAnimation(translateAnimation);
        translateAnimation.setFillBefore(false);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(100);
        pager.startAnimation(animationSet);
    }

    public float getCurrentCheckedRadioLeft() {
        if(button1.isChecked()){
            return getResources().getDimension(R.dimen.rdo1);
        }else if(button2.isChecked()){
            return getResources().getDimension(R.dimen.rdo2);
        }else if(button3.isChecked()){
            return getResources().getDimension(R.dimen.rdo3);
        }else if(button4.isChecked()){
            return getResources().getDimension(R.dimen.rdo4);
        }
        return 0f;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManger = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManger.setAccessible(true);
            childFragmentManger.set(this,null);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
