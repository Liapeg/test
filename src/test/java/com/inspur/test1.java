package com.inspur;

import sun.security.mscapi.CPublicKey;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/8/27 10:49
 */
public class test1 {

    public static void main(String[] args) {

       /* int[] arrs = new int[5];
        for(int i =0;i < 5; i++){
            arrs[i] = -1;
        }

        printArr(arrs);*/
        /*DecimalFormat df2  = new DecimalFormat("###.00");
        String curStr1 = df2.format((float)23232/10000);
        String curStr2 = df2.format((float)24232/100000000);

        System.out.println(curStr1);
        System.out.println(curStr2);*/

        String str = "13111111111,12111111111";

        System.out.println(str.split(";")[0]);


    }
    public static void printArr(int[] arr){

    }

    /*public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // arr[L...R]范围上，请让这个范围上的数，有序！
    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        // int mid = (L + R) / 2
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界，要么p2越界
        // 不可能出现：共同越界
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }*/



}

   