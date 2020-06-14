package com.zzh.test;

public class SinglePattern {
    //私有构造
    private SinglePattern(){
    }
    //自行实例化
    private static SinglePattern singlePattern=new SinglePattern();
    //对外提供访问的方法,由于外面无法参加对象，那么只能通过类名调用方法
    //因此这个方法必须是静态方法
    
    public static SinglePattern getInstance(){
        return singlePattern;
    }

}
