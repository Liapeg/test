package multiThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/12 9:42
 */
public class T02CountdownLatchTest {

    static boolean mark = true;
    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        for(int i= 0;i < 20;i++){
            new Thread(() ->{

                try {
                    lock.lock();
                    testCountDownLatch();
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    lock.unlock();
                }

            }).start();
        }

    }

    public static void testCountDownLatch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        if(mark){
            mark = false;
            System.out.println("----------------" + mark);
            countDownLatch.countDown();
        }else {
            countDownLatch.countDown();
        }
        countDownLatch.await();
        System.out.println("-----结束了");

    }
}

   