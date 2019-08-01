package com.tarena.cartoonlivehybrid.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tarena.cartoonlivehybrid.R;
import com.tarena.cartoonlivehybrid.UrlFactory;
import com.tarena.cartoonlivehybrid.biz.DownloadBiz;

import java.util.ArrayList;

/**
 * Created by pjy on 2017/7/25.
 */

public class CartoonFragment extends Fragment {
    View view;
    MyReceiver myReceiver;
    WebView webView;

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(myReceiver);
        webView.removeAllViews();
        webView.destroy();
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // view=View.inflate(getActivity(), R.layout.fragment_cartoon,null);
       view=inflater.inflate(R.layout.fragment_cartoon,null);
        myReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter("downloadOver");
        getActivity().registerReceiver(myReceiver,intentFilter);
        //远程同学，面试用公网ip 124.207.192.18
        try {

            String url= UrlFactory.getIndex();

             webView= (WebView) view.findViewById(R.id.webView);
            webView.loadUrl(url);
            WebSettings webSettings=webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                   //<a href="tarena:download/30#1">第1集下载</a>
                    String tag="tarena:download/";
                    if (url.contains(tag))
                    {
                    String data=url.substring(tag.length());
                        String cartoonId=data.split("#")[0];
                        String chapterId=data.split("#")[1];
                        DownloadBiz.getImage(getActivity(),cartoonId,chapterId);
                        return true;//这个超链接android已经处理了,webview不管了
                    }
                    return super.shouldOverrideUrlLoading(view, url);
                }
            });
        }catch (Exception e)
        {

        }
        return view;
    }
    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<String> list= (ArrayList<String>)
                    intent.getSerializableExtra("list");
            //启动ReaderActivity看漫画
            Intent toReader=new Intent(getActivity(),ReaderActivity_.class);
            toReader.putExtra("list",list);
            getActivity().startActivity(toReader);
        }
    }
}
