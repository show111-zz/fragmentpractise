package com.example.fragmentpractise.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lhf on 2015/9/17.
 */
public class FragmentC extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity().getApplicationContext());
        textView.setText("FragmentC");
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);

        return textView;
    }
}
