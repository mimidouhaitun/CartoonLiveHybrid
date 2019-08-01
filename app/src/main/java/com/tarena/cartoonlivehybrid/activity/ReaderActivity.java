package com.tarena.cartoonlivehybrid.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tarena.cartoonlivehybrid.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_reader)
public class ReaderActivity extends Activity {
ArrayList<String> list;
    @ViewById(R.id.imageView)
    ImageView imageView;
int currentIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list= (ArrayList<String>) getIntent().getSerializableExtra("list");
        Log.i("","");
    }
    @AfterViews
    public void showFirst(){
        String imagePath=list.get(currentIndex);
        Glide.with(this).load(imagePath).into(imageView);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
              int x= (int) event.getX();
                int width=v.getWidth();
                if (x>width/2)
                {
                    currentIndex++;
                }else
                {
                    currentIndex--;
                }
                Glide.with(ReaderActivity.this).load(list.get(currentIndex)).into(imageView);

                return false;
            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        list= (ArrayList<String>) intent.getSerializableExtra("list");
        Log.i("","");
    }
}
