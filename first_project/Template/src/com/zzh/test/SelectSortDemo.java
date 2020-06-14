package com.zzh.test;

import java.util.Arrays;

/**
 * @Author: ZehongZhao
 * @Description: 选择排序
 * @Date: Created in 15:27 2020/4/22
 */
public class SelectSortDemo {
    public static void main(String[] args) {
        int[]arr={4,3,6,7,2,1};
        for (int i = 0; i <arr.length ; i++) {
            int index=i;//0 1
            for (int j = 1; j <arr.length ; j++) {
                //找最小
                if (arr[j]<arr[index]){
                    index=j;
                }
            }
            System.out.println(index+" "+arr[index]);
            int temp=arr[i];
            arr[i]=arr[index];
            arr[index]=temp;
        }
        System.out.println(Arrays.toString(arr));
    }
}
