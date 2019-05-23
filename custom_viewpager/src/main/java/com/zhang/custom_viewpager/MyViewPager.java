package com.zhang.custom_viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 自定义ViewPager
 */
public class MyViewPager extends ViewGroup {

    /**
     * 手势识别器
     * 1，定义出来
     * 2，实例化--重写想要的方法
     * 3，在onTouchEvent()中把事件传递给手势识别器
     */
    private GestureDetector mDetector;
    /**
     * 当前页面下标位置
     */
    private int currentIndex;

    private MyScroller mScroller;//自己写的
//    private Scroller mScroller;//系统的

    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mScroller = new MyScroller();
//        mScroller = new Scroller(context);

        //实例化手势识别器
        mDetector = new GestureDetector(context, new MyGestureListener());
    }

    private float startX;

    private float eStartX;
    private float eStartY;

    /**
     * 事件拦截方法
     * 返回true,拦截事件，将会触发当前控件的onTouchEvent()方法
     * 返回false，不拦截事件，事件继续传递给子View
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //把事件传递给手势识别器
        mDetector.onTouchEvent(ev);
        boolean result = false;//默认传递事件
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                eStartX = ev.getX();
                eStartY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float eEndX = ev.getX();
                float eEndY = ev.getY();
                float distanceX = Math.abs((eEndX - eStartX));
                float distanceY = Math.abs((eEndY - eStartY));
                //如果X轴滑动的距离大于Y轴滑动的距离，则拦截事件
                if (distanceX > distanceY && distanceX > 10) {
                    result = true;
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        //3,把事件传递给手势识别器
        mDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录坐标
                startX = event.getX();
                showLog("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                showLog("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                showLog("ACTION_UP");
                //结束坐标
                float endX = event.getX();

                //下标位置
                int tempIndex = currentIndex;
                if ((startX - endX) > getWidth() / 2) {
                    //显示下一个页面
                    tempIndex++;
                } else if ((endX - startX) > getWidth() / 2) {
                    //显示上一个页面
                    tempIndex--;
                }

                //根据下标位置移动到指定页面
                scrollToPager(tempIndex);

                break;
            default:
                break;
        }
        return true;//自己处理
    }

    /**
     * 根据下标位置移动到指定页面 过滤非法值
     *
     * @param tempIndex
     */
    private void scrollToPager(int tempIndex) {
        if (tempIndex < 0) {
            tempIndex = 0;
        }
        if (tempIndex > getChildCount() - 1) {
            tempIndex = getChildCount() - 1;
        }
        //把过滤后的值赋值给当前页面下标
        currentIndex = tempIndex;

//        scrollTo(currentIndex * getWidth(),0);

        int distanceX = currentIndex * getWidth() - getScrollX();

        mScroller.startScroll(getScrollX(), getScrollY(), distanceX, 0);

        invalidate();//这个方法会导致onDraw()和computeScroll()执行

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            float currentX = mScroller.getCurrX();
            scrollTo((int) currentX, 0);
            invalidate();
        }
    }

    class MyGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        /**
         * @param e1
         * @param e2
         * @param distanceX：在X轴滑动的距离
         * @param distanceY：在Y轴滑动的距离
         * @return
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            scrollBy((int) distanceX, (int) distanceY);//这样可以同时在X轴和Y轴滑动
            //限制第一页和最后一页不能滑动
            if (currentIndex == 0 || currentIndex == getChildCount() - 1) {
                return false;
            }
            scrollBy((int) distanceX, 0);//只能在X轴滑动

            return true;//自己处理滑动事件
        }

        @Override
        public void onLongPress(MotionEvent e) {
            showLog("长按");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    private void showLog(String msg) {
        Log.e("===z", msg);
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
            childView.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }

    }

    /**
     * @param widthMeasureSpec  : 包含两个属性：父类建议的宽和测量模式
     * @param heightMeasureSpec ：父类建议的高和测量模式
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        /**
         * 测量模式有这三种
         *         MeasureSpec.AT_MOST;       ：最大值模式，当控件宽高设置为wrap_content时
         *         MeasureSpec.EXACTLY        ：精确值模式，当控件match_parent时
         *         MeasureSpec.UNSPECIFIED;   ：未指定模式，View想多大就多大，通常在绘制自定义View时才会用
         */


        //获取下一级子View的建议宽和测量模式
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, mode);

        //获取子View并测量
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
