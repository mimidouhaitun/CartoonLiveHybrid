package com.tarena.cartoonlivehybrid;

/**
 * Created by pjy on 2017/7/25.
 */

public class UrlFactory {
   public static String server;
    public static String getIndex()
    {
        return server+"/cartoonServer/mobilepage/theme.html";
    }
    public static String getImageInfoUrl()
    {
        return server+"/cartoonServer/apis/get_chapter/?";
    }

}
