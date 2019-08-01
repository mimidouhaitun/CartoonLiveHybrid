package com.tarena.cartoonlivehybrid;

import android.app.Application;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import org.xutils.x;

/**
 * Created by pjy on 2017/7/25.
 */

public class MyApp extends Application {
    //true 发布了 false:开发中
    public  static  boolean isRelease=true;
    @Override
    public void onCreate() {
        super.onCreate();
        readConfig();
        //初始化xutils
        x.Ext.init(this);
    }

    private void readConfig() {
        try{
            Resources res=getResources();
            XmlResourceParser xmlResourceParser= res.getXml(R.xml.config);
            int eventType=xmlResourceParser.getEventType();
            while(eventType!=XmlResourceParser.END_DOCUMENT)
            {
                if (eventType==XmlResourceParser.START_TAG)
                {
                    String tagName=xmlResourceParser.getName();
                    if ("webServer".equals(tagName))
                    {
                        UrlFactory.server=xmlResourceParser.nextText();
                        return;
                    }
                }
                eventType=xmlResourceParser.next();
            }
        }catch (Exception e)
        {e.printStackTrace();}
    }
}
