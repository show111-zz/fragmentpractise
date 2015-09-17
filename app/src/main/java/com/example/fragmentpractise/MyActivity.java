package com.example.fragmentpractise;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.fragmentpractise.fragment.MainmainActivity;

/**
 * Created by lhf on 2015/9/10.
 */
public class MyActivity extends FragmentActivity{

    private FragmentManager manager = getFragmentManager();
    private FragmentTransaction transaction = manager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymain);
        transaction.replace(R.id.fragmentmain, new MainmainActivity());
        transaction.commit();
    }
}
