package com.zzh.test;

/**
 * @Author: ZehongZhao
 * @Description: 简单工厂模式
 * @Date: Created in 15:39 2020/4/21
 */
public class SimpleFactory {
    public static void main(String[] args) {
        Car car=Factory.getCar("Benz");
        car.run();
        car.stop();
    }
}
 interface Car{
    public void run();
    public void stop();
}
class Benz implements Car{
    public void run() {
        System.out.println("Benz开始启动了。。。。。");
    }

    public void stop() {
        System.out.println("Benz停车了。。。。。");
    }
}
class Ford implements Car {
    public void run() {
        System.out.println("Ford开始启动了。。。");
    }

    public void stop() {
        System.out.println("Ford停车了。。。。");
    }
}
class Factory{
    public static Car getCar(String type){
        Car c=null;
        if ("Benz".equals(type)){
            c= new Benz();
        }else if ("Ford".equals(type)){
            c=  new Ford();
        }else{
            System.out.println("造不出这种车");
        }
        return c;
    }
}