package com.zzh.test;

public class Person {
    private String name;
    private int age;

    public Person() {
        System.out.println("构造方法");
    }

    static{
        System.out.println("静态代码块");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
