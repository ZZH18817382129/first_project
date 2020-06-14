package com.zzh.test;

import java.util.Arrays;

public class DeleteNumToArray {
    public static void main(String[] args) {
        int[]arr={1,2,3,4,5};
        int temp=2;
        int index=-1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==temp){
                index=i;
                break;
            }
        }
        if (index<0){
            System.out.println(temp+"不在数组中");
        }else{
            for (int i = index; i < arr.length-1; i++) {
                arr[i]=arr[i+1];
            }
            arr[arr.length-1]=0;
        }
        int[]newArr= Arrays.copyOf(arr,arr.length-1);
        System.out.println(Arrays.toString(newArr));
    }
}
