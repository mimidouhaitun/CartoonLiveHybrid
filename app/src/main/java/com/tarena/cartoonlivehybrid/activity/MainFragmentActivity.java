package com.tarena.cartoonlivehybrid.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.tarena.cartoonlivehybrid.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import static com.tarena.cartoonlivehybrid.R.id.fragment_container;

/**
 * Created by pjy on 2017/7/25.
 */


public class MainFragmentActivity extends FragmentActivity {
    CartoonFragment cartoonFragment;
    //cartoon,discover,me
    Button[] buttons=new Button[3];
    Fragment[] fragments=new Fragment[3];
    //单击的
    int clickBtnIndex=0;
    //正在显示的fragment
    int currentShowFragmentIndex=0;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        Log.i("","");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);
       buttons[0]= (Button) findViewById(R.id.btn_main_fragment_cartoon);
       buttons[1]= (Button) findViewById(R.id.btn_main_fragment_discover);
       buttons[2]= (Button) findViewById(R.id.btn_main_fragment_me);

        MyListener myListener=new MyListener();
        for(Button btn:buttons)
        {
            btn.setOnClickListener(myListener);
        }
        init();
        fragments[0]=cartoonFragment;
        fragments[1]=new DiscoverFragment();
        fragments[2]=new MeFragment();
        buttons[currentShowFragmentIndex].setSelected(true);
    }
    class MyListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            try {
                //判断单击的是那个按钮
                switch (v.getId())
                {
                    case R.id.btn_main_fragment_cartoon:
                        clickBtnIndex=0;
                        break;
                    case R.id.btn_main_fragment_discover:
                        clickBtnIndex=1;
                        break;
                    case R.id.btn_main_fragment_me:
                        clickBtnIndex=2;
                        break;
                }
                //判断是不是当前的按钮
                if (clickBtnIndex!=currentShowFragmentIndex) {
                    //开始事务
                    FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                    //动作1：隐藏以前的fragment
                    transaction.hide(fragments[currentShowFragmentIndex]);
                    //动作2:增加新的
                    Fragment showFragment=fragments[clickBtnIndex];
                    if (showFragment.isAdded()==false)
                    {
                        transaction.add(R.id.fragment_container,showFragment);
                    }
                    //动作3:显示
                    transaction.show(showFragment);
                    //所有动作没出异常，提交动作
                    transaction.commit();
                    buttons[clickBtnIndex].setSelected(true);
                    buttons[currentShowFragmentIndex].setSelected(false);
                    currentShowFragmentIndex=clickBtnIndex;
                }
            }catch (Exception e)
            {

            }

        }
    }


    public void init()
    {
        cartoonFragment=new CartoonFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        //开始事务
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        //动作1:add
        fragmentTransaction.add(R.id.fragment_container,cartoonFragment);
        //动作2:show
        fragmentTransaction.show(cartoonFragment);
        //提交
        fragmentTransaction.commit();

    }
}
