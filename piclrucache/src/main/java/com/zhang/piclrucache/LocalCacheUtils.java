package com.zhang.piclrucache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 三级缓存之本地缓存
 */
class LocalCacheUtils {
    private static final String CACHE_PATH= Environment.getExternalStorageDirectory().getAbsolutePath()+"/WerbNews";

    /**
     * 从网络中获取图片后 保存到本地
     * @param url
     * @param bitmap
     */
    public void setBitmapToLocal(String url, Bitmap bitmap) {
//        String fileName = url;//把图片的url当做文件名,并进行MD5加密
        String fileName = Base64.encodeToString(url.getBytes(), Base64.DEFAULT);
        try {
            File file = new File(CACHE_PATH, fileName);
            //通过得到文件的父文件,判断父文件是否存在
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }

            //把图片保存至本地
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitmapFromLocal(String url) {
//        String fileName = url;//把图片的url当做文件名,并进行MD5加密
        String fileName = Base64.encodeToString(url.getBytes(), Base64.DEFAULT);
        try {
            File file = new File(CACHE_PATH, fileName);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
