package com.zzh.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @program: first_project
 * @Author: ZehongZhao
 * @CreateTime: 2020/4/26
 */
public class HasMapDemo {
    public static void main(String[] args) {
        Map<String,String>map=new HashMap<>();
        map.put("111","aaa");
        map.put("222","bbb");
        map.put("333","ccc");
        map.put(null,"ddd");
        //第一种方式
        System.out.println("第一种");
        Set<String>keySet=map.keySet();
        for (String key:keySet) {
            String value=map.get(key);
            System.out.println(key+"----"+value);
        }
        //第二种方式
        System.out.println("第二种");
        Set<Map.Entry<String,String>>entrySet=map.entrySet();
        Iterator<Map.Entry<String,String>>it=entrySet.iterator();
        while(it.hasNext()){
            Map.Entry<String,String>entry=it.next();
            String key=entry.getKey();
            String value=entry.getValue();
            System.out.println(key+"----"+value);
        }

    }
}
