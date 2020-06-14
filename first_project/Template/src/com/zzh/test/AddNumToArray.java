package com.zzh.test;

import java.util.Arrays;

public class AddNumToArray {
    public static void main(String[] args) {
        // 原数组
        int[] arr = {1,3,7,9,13,20};
        //对数组进行扩容
        int[]newArr= Arrays.copyOf(arr,arr.length+1);
        //添加的元素
        int temp=12;
        //找位置
        int index=-1;
        for (int i = 0; i < newArr.length-1; i++) {
            if (newArr[i]>temp){
                index=i;
                break;
            }
        }
        if (index<0){//没有找到位置
            newArr[newArr.length-1]=temp;
        }else{
            for (int i = newArr.length-1; i >index ; i--) {
                newArr[i]=newArr[i-1];
            }
            //插入这个数
            newArr[index]=temp;
        }
        System.out.println(Arrays.toString(newArr));
    }
}
