package com.zzh.test;

import java.util.Set;
import java.util.TreeSet;

/**
 * @program: first_project
 * @Author: ZehongZhao
 * @CreateTime: 2020/4/26
 */
public class TreeSetDemo {
    public static void main(String[] args) {
        Set<Integer>set=new TreeSet<>();
        set.add(1);
        set.add(0);
        set.add(1);
//        set.add(null);
        for (Integer a:set) {
            System.out.println(a);
        }
    }
}
