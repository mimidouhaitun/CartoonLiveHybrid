package com.tarena.cartoonlivehybrid.parser;

import com.alibaba.fastjson.JSON;
import com.tarena.cartoonlivehybrid.entity.ImageInfoEntity;

/**
 * Created by pjy on 2017/7/25.
 */

public class ImageInfoParser {
    public static ImageInfoEntity parser(String json)
    {
        return JSON.parseObject(json,ImageInfoEntity.class);
    }
}
