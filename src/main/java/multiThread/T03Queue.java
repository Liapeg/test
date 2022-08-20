package multiThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/6/28 17:08
 */
public class T03Queue {


    static Vector<String> list = new Vector<>();
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    static
    {
        for(int i=0;i<10000;i++){
            list.add("票"+i);
        }
    }

    static Random r = new Random();
    static BlockingDeque blockingDeque = new LinkedBlockingDeque();
    public static void main(String[] args) throws IOException {

        new Thread(()->{
            for(int i =0; i< 1000;i++){
                try {
                    blockingDeque.put("a" + i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();


        for(int i=0;i<5;i++){
            new Thread(()->{
                for(;;){
                    try {
                        System.out.println(blockingDeque.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }

    }

}

   