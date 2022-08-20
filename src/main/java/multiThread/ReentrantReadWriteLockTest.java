package multiThread;

import cn.hutool.Hutool;
import cn.hutool.bloomfilter.filter.AbstractFilter;
import org.springframework.retry.backoff.Sleeper;
import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.sun.deploy.trace.TraceLevel.SECURITY;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/15 10:46
 */
public class ReentrantReadWriteLockTest {

    static ReentrantLock  lock = new ReentrantLock();
    /**
     * volatile关键字 实现线程之间变量可见性 有序性 并不会保证原子性  即多个线程修改running会有一致性问题
     */
    private volatile static boolean  running = false;
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(()-> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException exception){

            }finally {
                lock.unlock();
            }
            System.out.println("t1 end!");
        });

        t1.start();

        TimeUnit.SECONDS.sleep(1);


        Thread t2 = new Thread(()-> {
            System.out.println("t2 start!");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("t2 end!");
        });

        t2.start();

        TimeUnit.SECONDS.sleep(1);

        t2.interrupt();


        Unsafe unsafe = Unsafe.getUnsafe();

        //synchronized既保证了原子性也保证了可见性

        ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();

        new Thread(test::m1, "1").start();
        new Thread(test::m2, "2").start();

    }



        private synchronized  void m1(){
            try {

                TimeUnit.SECONDS.sleep(2);
                System.out.println("m1-----------");
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }



        private void m2(){
        try {


            TimeUnit.SECONDS.sleep(2);
            System.out.println("m2---------");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        }




}

   