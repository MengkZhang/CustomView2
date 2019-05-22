package com.zhang.custom_viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义ViewPager
 */
public class MyViewPager extends ViewGroup {

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //遍历每个孩子，给每个孩子指定在屏幕上的位置
//        l = i * getWidth
//        t = 0
//        r =  (i+1) * getWidth
//        t =  getHeight
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.layout(i * getWidth(),0,(i + 1) * getWidth(),getHeight());
        }

    }
}
