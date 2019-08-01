package com.tarena.cartoonlivehybrid;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tarena.cartoonlivehybrid.activity.LoginActivity;
import com.tarena.cartoonlivehybrid.activity.LoginActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {


//在oncreate,findViewById之后执行
    @AfterViews
    public void afterView()
    {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LoginActivity_.class));
            }
        },1000);
    }
}
