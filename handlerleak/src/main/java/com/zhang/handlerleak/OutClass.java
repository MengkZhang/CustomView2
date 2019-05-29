package com.zhang.handlerleak;

public class OutClass {
    private int a;

    private void setA() {

    }

    ////内部类可以随意使用外部类的方法和属性，不论是否是private的
    public class InnerClass {
        private void callA() {
            setA();
            a = 2;
        }
    }

}
