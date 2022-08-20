package com.inspur;

import cn.hutool.json.JSONArray;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/8/3 14:35
 */
public class test {
    public static void main(String[] args) {
       /* String str = "database/9562338a13c54ee28dbac0d31ef035a7";
        str  = str.substring(9);
        System.out.println(str);*/

       //String

        //System.out.println(0+((9-0)>>1));
        /*ReentrantLock lock = new ReentrantLock();
        Thread[] threads = new Thread[10];
        try {
            for(int i=0;i < 10;i++){
                lock.lock();
                Thread thread = new Thread(()->{

                });
                thread.start();
            }
        }catch (Exception e){

        }finally {
            lock.unlock();
        }*/
       /* String str = "spring:session:sessions:expires:1870150e-d000-4eae-9059-594bad9c598a";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 0);
        Date today = calendar.getTime();

        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
        String endTime = dateFormat.format(today);
        today = calendar.getTime();
        String startTime = dateFormat.format(today);

        System.out.println(startTime);
        System.out.println(endTime);*/

        //ByteBuffer byteBuffer = new ByteBuffer();
        List<String> str = new ArrayList<>();
        for(int i = 0; i< 10000000; i++){
            str.add("a"+i);
        }

        List<String> strings = new ArrayList<>();
        int a =0;
        System.out.println(System.currentTimeMillis());
        for (String s : str) {
            strings.add(s);
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(a);

        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println(System.currentTimeMillis());
        str.stream().parallel().forEach((s) -> {
                    strings.add(s);
                }
        );
        System.out.println(System.currentTimeMillis());
        System.out.println(atomicInteger.intValue());





    }

}

