package com.wu.callback;

public interface CallBack {
    /**
     类A的a()方法调用类B的b()方法
     类B的b()方法执行完毕主动调用类A的callback()方法
     这样一种调用方式组成了上图，也就是一种双向的调用方式
     */

    public void callBack(String param);

}
