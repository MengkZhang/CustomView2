package com.zhang.piclrucache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

/**
 * 自定义图片加载工具类,实现三级缓存
 * 加载图片的原理：imageView.setImageBitmap(bitmap)
 */
public class MyBitmapUtils {
    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public MyBitmapUtils() {
        mLocalCacheUtils = new LocalCacheUtils();
        mMemoryCacheUtils = new MemoryCacheUtils();
        mNetCacheUtils = new NetCacheUtils(mLocalCacheUtils,mMemoryCacheUtils);
    }

    public void display(ImageView ivPic,String url) {
        ivPic.setImageResource(R.mipmap.ic_launcher);
        Bitmap bitmap;

        //读取内存缓存
        bitmap = mMemoryCacheUtils.getBitmapFromMemory(url);
        if (bitmap != null) {
            ivPic.setImageBitmap(bitmap);
            System.out.println("从内存获取图片啦.....");
            Log.e("===z","从内存获取图片啦.....");
            return;
        }
        //读取磁盘缓存
        bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
        if (bitmap != null) {
            ivPic.setImageBitmap(bitmap);
            System.out.println("从本地获取图片啦.....");
            Log.e("===z","从本地获取图片啦.....");
            return;
        }

        //读取网络缓存
        mNetCacheUtils.getBitmapFromNet(ivPic,url);
    }

}
