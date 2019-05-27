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
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558982301130&di=0d4e5339c6a5fec0905dae878b584ab6&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F472309f7905298221724db82d5ca7bcb0a46d458.jpg";
        MyBitmapUtils bitmapUtils = new MyBitmapUtils();
        bitmapUtils.display(mImageView,url);
    }
}
