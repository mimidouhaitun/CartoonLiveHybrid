package com.tarena.cartoonlivehybrid.biz;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import com.tarena.cartoonlivehybrid.UrlFactory;
import com.tarena.cartoonlivehybrid.entity.ImageInfoEntity;
import com.tarena.cartoonlivehybrid.parser.ImageInfoParser;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by pjy on 2017/7/25.
 */

public class DownloadBiz {
    public  static void getImage(final Context context,
                                 final String cartoonId,
                                 final String chapterId)
    {
        //1,拼出url
        String url= UrlFactory.getImageInfoUrl();
        //2,准备发的数据
        url=url+"cartoonId="+cartoonId+"&chapterId="+chapterId;
        //3,发送
        RequestParams requestParams=new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //4,回调
                Log.i("test",result);
                ImageInfoEntity imageInfoEntity= ImageInfoParser.parser(result);
                downloadImage(context,cartoonId,chapterId,imageInfoEntity);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("test",ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    public  static  void downloadImage(final Context context ,
                                       String cartoonId,
                                       String chapterId,
                                       final ImageInfoEntity
                                               imageInfoEntity)
    {
        //cartoon/1/1/0001.png
        //cartoon/1/1/0002.png
        //cartoon/1/2
        //cartoon/2/1
        String sdcardRoot= Environment.getExternalStorageDirectory().getAbsolutePath();
        String directory=sdcardRoot+"/cartoon/"+cartoonId+"/"+chapterId;
        //创建子文件夹
        File file=new File(directory);
        if (!file.exists())
        {
            file.mkdirs();
        }
        final ArrayList<String> downloadFileList=new ArrayList<>();
        //for
        for (int i=0;i<imageInfoEntity.getData().size();i++) {
            String imageSrc=imageInfoEntity.getData().get(i).getImageSrc();
            //得文件名 "/cartoonServer/upLoadComicData/30/1/0002.jpg"
            int index=imageSrc.lastIndexOf("/");
            String fileName=imageSrc.substring(index+1);
            //拼出path
            String path=directory+"/"+fileName;
            String url=UrlFactory.server+imageSrc;
            //联网
            RequestParams requestParams=new RequestParams(url);
            requestParams.setSaveFilePath(path);

            x.http().get(requestParams, new Callback.CommonCallback<File>() {
                @Override
                public void onSuccess(File result) {
                    downloadFileList.add(result.getAbsolutePath());
                    Log.i("","ok");
                    //判断图片是否下载完,下载完，发广播
                    if (downloadFileList.size()==imageInfoEntity.getData().size()){
                        Intent intent=new Intent("downloadOver");
                        //发广播带数据
                        intent.putExtra("list",downloadFileList);
                        context.sendBroadcast(intent);
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.i("","onError");
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });


        }
    }
}
