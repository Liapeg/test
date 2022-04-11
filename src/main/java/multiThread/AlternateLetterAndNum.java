package multiThread;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/4 16:55
 */
public class AlternateLetterAndNum {

    static Thread t1= null;
    static Thread t2= null;
    /*public static void main(String[] args) throws Exception{
        //1、CountDownLatch countDownLatch = new CountDownLatch(1); 不行
        //2、LockSupport----最简单
        //3、reentrantlock
        //4、reentrantlock 的condition
        //打印数字1-26
        t1 =new Thread(()-> {
            for (int i=0; i< 26; i++){
                System.out.print(i);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        //打印字母A-Z
        t2 = new Thread(()->{
            for(int j =0;j < 26;j++){
                LockSupport.park();
                System.out.print((char) (j+65));
                LockSupport.unpark(t1);
            }
        });
        t1.start();
        //TimeUnit.SECONDS.sleep(1);
        t2.start();




    }*/


    public static void main(String[] args) throws Exception{
        //1、CountDownLatch countDownLatch = new CountDownLatch(1); 不行
        //2、LockSupport

        //VarHandle
        ReentrantLock lock = new ReentrantLock();
        ReentrantLock lock1 = new ReentrantLock();
        //打印数字1-26
        t1 =new Thread(()-> {
            for (int i=0; i< 26; i++){
                try {
                    lock.lock();
                    System.out.print(i);
                    lock1.notifyAll();
                    lock.wait();
                }catch (Exception exception){
                }finally {
                    lock.unlock();
                }

            }
        });

        //打印字母A-Z
        t2 = new Thread(()->{
            for(int j =0;j < 26;j++){
                try {
                    lock1.lock();
                    System.out.print((char) (j+65));
                    lock.notifyAll();
                    lock1.wait();
                }catch (Exception ex){

                }finally {
                    lock1.unlock();
                }
            }
        });
        t1.start();
        t2.start();




    }

}

   