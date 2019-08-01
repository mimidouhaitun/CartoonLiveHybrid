package com.tarena.cartoonlivehybrid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tarena.cartoonlivehybrid.R;

/**
 * Created by pjy on 2017/7/25.
 */

public class MeFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate( R.layout.fragment_me,container,false);
        initViews();
        addListener();
        return view;
    }

    private void addListener() {
    }

    private void initViews() {
    }
}
