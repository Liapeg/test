package com.inspur;

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
        ReentrantLock lock = new ReentrantLock();
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
        }
    }

}

   