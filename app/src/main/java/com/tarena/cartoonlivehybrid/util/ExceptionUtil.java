package com.tarena.cartoonlivehybrid.util;

import android.os.Build;
import android.util.Log;

import com.tarena.cartoonlivehybrid.MyApp;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by pjy on 2017/7/25.
 */

public class ExceptionUtil {
    public static void handleException(Throwable throwable)
    {
        if (MyApp.isRelease) {
            //得手机厂商
            String string="";
            string=string+ Build.MANUFACTURER+";";
            //得手机型号
            string=string+ Build.MODEL+";";
            //得手机操作系统版本
            string=string+ Build.VERSION.SDK_INT+";";
            //把异常信息变成字符串
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            throwable.printStackTrace(printWriter);
           string = string+stringWriter.toString();

            Log.i("出异常", string);
            //发到bmob
        }else
        {
            throwable.printStackTrace();
        }

    }
}
