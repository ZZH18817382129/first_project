package com.zzh.test;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: first_project
 * @Author: ZehongZhao
 * @CreateTime: 2020/4/26
 */
public class HashSetDemo {
    public static void main(String[] args) {
        Set<Integer>set=new HashSet<>();
        set.add(1);
        set.add(0);
        set.add(1);
        set.add(null);
        for (Integer a:set) {
            System.out.println(a);
        }
    }
}
