package com.zhang.touchevent1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TouchEventFather extends LinearLayout {
    /**
     * 两个参数的构造方法 在布局文件中使用
     * @param context
     * @param attrs
     */
    public TouchEventFather(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("eventTest", "Father | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("eventTest", "Father | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
//        return super.onInterceptTouchEvent(ev);
        return true;//拦截事件
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("eventTest", "Father | onTouchEvent --> " + TouchEventUtil.getTouchAction(event.getAction()));
        return super.onTouchEvent(event);
    }
}
