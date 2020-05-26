package main.java.juc;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Lock {

    ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue<>();
    Thread lockHolder;
    public volatile int state = 0;

    public boolean acquire() {

        final Thread currentThread = Thread.currentThread();
        if (getState() == 0) {
            //公平，当前线程是在队首
            if ((waiters.isEmpty() || waiters.peek() == currentThread) && cas(0, 1)) {
                setHolder(currentThread);
//                System.out.println(currentThread.getName() + " acquire lock");
                return true;
            }
        }

        return false;
    }

    public void lock() {
        final Thread currentThread = Thread.currentThread();

        if (acquire()) {
            return;
        }
        //加入队列尾，公平锁
        waiters.add(currentThread);

        for (; ; ) {
            if (acquire()) {
                waiters.poll();
                break;
            }

            LockSupport.park(currentThread);
        }
    }

    public void unlock() {
        //非加锁线程不能解锁
        if (Thread.currentThread() != lockHolder) {
//            System.out.println(lockHolder);
            throw new RuntimeException("lockHolder is not currentThread");
        }
        if (cas(1, 0)) {
//            System.out.println(lockHolder.getName() + " unlock");
            setHolder(null);
        }
        //唤醒队首线程
        if (waiters.size() != 0 && waiters.peek() != null) {
            LockSupport.unpark(waiters.peek());
        }
    }

    public void setHolder(Thread thread) {
        this.lockHolder = thread;
    }

    public int getState() {
        return state;
    }


    public final boolean cas(int except, int actual) {
        return unsafe.compareAndSwapInt(this, stateOffset, except, actual);
    }


    private static final Unsafe unsafe = UnsafeInstance.getInstance();

    private static final long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(Lock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static volatile int count = 1000;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1000);
        final Lock mutex = new Lock();
        final java.util.concurrent.locks.Lock rmu = new ReentrantLock();
        for(int i = 0; i < 1000; i++){
            final Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        //模拟操作
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                  /*  //减库存
                    count--;
                    mutex.unlock();
                    latch.countDown();*/
                }
            });
            thread.setName("qwer-"+System.currentTimeMillis()+"");
            thread.start();
        }

        latch.await();
        System.out.println(count);
    }
}
