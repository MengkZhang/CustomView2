package com.zhang.handlerleak;

public class Client {
    public static void main(String[] args) {
        //要想new一个InnerClass，必须先new一个OutClass
        //OutClass.InnerClass inn = new OutClass.InnerClass();//这句代码是错误的，会编译不过
        OutClass outClass = new OutClass();
        //这里就新建了一个依赖于out这个变量的内部类
        OutClass.InnerClass innerClass = outClass.new InnerClass();
    }
}
