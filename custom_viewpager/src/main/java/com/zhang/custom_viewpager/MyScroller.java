package com.zhang.custom_viewpager;

import android.os.SystemClock;

class MyScroller {
    /**
     * X轴起始坐标
     */
    private float startX;
    /**
     * Y轴起始坐标
     */
    private float startY;
    /**
     * 在X轴移动的距离
     */
    private int distanceX;
    /**
     * 在Y轴移动的距离
     */
    private int distanceY;
    /**
     * 开始时间
     */
    private long startTime;
    /**
     * 是否 移动完成
     * FALSE没有移动完成
     * TRUE移动完成
     */
    private boolean isFinish;
    /**
     * 总时间 写死
     */
    private long totalTime = 500;

    private float currX;

    public float getCurrX() {
        return currX;
    }

    void startScroll(float startX, float startY, int distanceX, int distanceY) {
        this.startX = startX;
        this.startY = startY;
        this.distanceX = distanceX;
        this.distanceY = distanceY;
        this.startTime = SystemClock.uptimeMillis();
        this.isFinish = false;
    }

    /**
     * 速度
     * 求移动一小段的距离
     * 求移动一小段对应的坐标
     * 求移动一小段对应的时间
     * true:正在移动
     * false:移动结束
     *
     * @return
     */
    boolean computeScrollOffset() {
        if (isFinish) {
            return false;
        }
        long endTime = SystemClock.uptimeMillis();
        //一小段花的时间
        long passTime = endTime - startTime;
        if (passTime < totalTime) {
            //移动还没有结束
            //计算平均速度
//            float v = distanceX / totalTime;
            //移动这个一小段对应的距离
            float distanceSmallX = passTime * distanceX / totalTime;

            currX = startX + distanceSmallX;

        } else {
            //移动结束
            isFinish = true;
            currX = startX + distanceX;
        }

        return true;
    }
}
