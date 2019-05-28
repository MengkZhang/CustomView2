package com.zhang.piclrucache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * 图片三级缓存
 */
public class MainActivity extends AppCompatActivity {

    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.image_view);
        String url = "https://upload-images.jianshu.io/upload_images/5914881-8e0f627c5963bd0d.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp";
        MyBitmapUtils bitmapUtils = new MyBitmapUtils();
        bitmapUtils.display(mImageView,url);
    }
}
