package com.example.fragmentpractise;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.fragmentpractise.fragment.MainmainActivity;


/**
 * Created by lhf on 2015/9/10.
 */
public class MyActivity extends FragmentActivity{

    private FragmentManager manager = getSupportFragmentManager();
    private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymain);
        transaction.replace(R.id.fragmentmain, new MainmainActivity());
        this.transaction.commit();
    }
}
