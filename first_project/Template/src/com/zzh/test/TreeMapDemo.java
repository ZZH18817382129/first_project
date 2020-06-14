package com.zzh.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @program: first_project
 * @Author: ZehongZhao
 * @CreateTime: 2020/4/26
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        Map<String,String> map=new TreeMap<String,String>();
        map.put("111",null);
//        map.put(null,"bbb");
        map.put("222",null);
        //第一种方式
        System.out.println("第一种");
        Set<String> keySet=map.keySet();
        for (String key:keySet) {
            String value=map.get(key);
            System.out.println(key+"----"+value);
        }
        //第二种方式
        System.out.println("第二种");
        Set<Map.Entry<String,String>>entrySet=map.entrySet();
        Iterator<Map.Entry<String,String>> it=entrySet.iterator();
        while(it.hasNext()){
            Map.Entry<String,String>entry=it.next();
            String key=entry.getKey();
            String value=entry.getValue();
            System.out.println(key+"----"+value);
        }

    }
}
