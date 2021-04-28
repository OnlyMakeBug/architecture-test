package com.zj.test.java.lang.thread;

import com.zj.test.util.TestHelper;

/**
 * author: zhoujian
 * corporation: none
 * create-time: 2020年8月25日 下午12:15:35
 * description: object.wait(),notify(),notifyAll()测试
 * finished: 0
 *
 * 测试： 假设每个售票机线程，售票100张。然后使用wait()放弃锁，看看wait对线程的影响。
 *
 * -----------------------------------------------------------------------------------------
 * 测试结果总结：wait会放弃锁，wait调用的代码结构如下：
 * 1.如果synchronized修饰的是代码块，则代码块中wait()后面的代码不会执行。
 * 且synchronized代码块后的代码也不会执行。即：
 * public void methodName() {
 *      synchronized(lock){
 *      ... lock.wait();
 *      ... // code fter wait(),Cannot reach
 * }
 *      ... // code after synchronized code block,Cannot reach
 * }// 但是当通过notify或notifyAll重新获得锁后，没执行的代码会被执行。
 *
 * 2.如果synchronized修饰的是方法，则wait()方法后面的代码不会执行。即
 * public synchronized void methodName(){
 *      ...
 *      this.wait()
 *      ... // code after wait(),Cannot reach
 * }// 但是当通过notify或notifyAll重新获得锁后，没执行的代码会被执行。
 *
 * ------------------------------------------------------------------
 *
 * 3.wait()
 * 本线程主动放弃锁，进入等待，并唤醒某个正常的线程，让其获取锁从而获得执行代码的机会。
 *
 * 调用wait()会通知jvm唤醒某个竞争该对象锁的线程X。唤醒的是那些正常状态的线程，而不是执行wait()进入睡眠的程序。
 * 调用wait()后的线程，线程会一直等待，不会主动抢夺锁，除非被其他线程调用notify或notifyAll唤醒。
 *
 * 注意：因为wait()后面的代码不会执行，所以notify()或notifyAll()的调用应在wait()之前。
 *
 * 4.notify()和notifyAll()比较
 * 两者都是唤醒因调用wait()而睡眠的线程，从而参与cpu资源的抢夺。
 *
 * 不同点：
 * notify：唤醒某个睡眠线程，本线程释放锁后直接将锁分配给这个线程。
 * 这也就意味着，那些正常抢夺锁的线程将会继续等待。
 * 这也就是说调用notify()方法，只有因调用wait()而睡眠的线程，才有获得锁的机会。
 *
 * 缺点: 对于正常抢夺资源的线程不公平。
 * --------------------------------------------
 *
 * notifyAll：唤醒所有睡眠线程，本线程释放锁后，根据算法将锁分配到某个线程，
 * 这也就是意味着调用该方法后，所有正常抢占锁和因调用wait()而睡眠的线程，都有得到锁的机会。
 *
 * 优点：对于正常抢夺资源的线程而言，比notify()更加的公平。但也只是相对的公平。
 * 实际上notify和notifyAll对于正常线程而言，都不公平。
 *
 * 5.wait,notify,notifyAll优缺点：
 *
 * 优点：
 * 1.弥补synchronized无法手动释放锁、分配锁的缺陷。
 *
 * 2.控制synchronized代码块的执行进程：
 * synchronized碎片化：synchronized块作为一个整体,当某个线程获取锁后，会将synchronized块整个执行一遍。
 * 而wait的使用可以中断synchronized块中代码的执行，控制synchronized块的执行进程。
 * 虽然Thread.sleep()可以达到效果，但是会一直占用锁，其他线程无法获取锁。而wait()则可以解决此问题，
 * 但是需要在合适的地方调用notify/notifyAll来恢复线程的执行。
 *
 * 3.加强对锁的管理:
 * wait,notify,notifyAll的组合使用，可用来指定哪些线程能够获取锁，哪些线程不能。
 *
 * 缺点： 1.使用不当会导致同步问题。
 */

/**
 * 车票池实现 - 多个售票机线程公用
 */
class TicketPool implements Runnable {

    /**
     * 剩余票数
     */
    int tickCount = 300;

    /**
     * 锁
     */
    Object lock = new Object();


    @Override
    public void run() {

        /** wait(),notify(),notifyAll()都需要在synchronized作用范围内使用 */
		/*try {
			 // Exception in thread "Thread-0" Exception in thread "Thread-1" Exception in thread "Thread-2" java.lang.IllegalMonitorStateException
			lock.wait();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

        // Exception in thread "Thread-2" Exception in thread "Thread-0" Exception in thread "Thread-1" java.lang.IllegalMonitorStateException
        //lock.notify();

        // Exception in thread "Thread-0" Exception in thread "Thread-2" Exception in thread "Thread-1" java.lang.IllegalMonitorStateException
        // lock.notifyAll();

        TestHelper.println("[" + Thread.currentThread().getName() + "] - code before synchronized");

        synchronized (lock) {
            TestHelper.println("线程 [" + Thread.currentThread().getName() + "] 获得锁，开始售票服务");
            for (int i = 0; i < 100; i++) {
                tickCount--;
                TestHelper.println("[" + Thread.currentThread().getName() + "] - " + "售票成功,剩余总票数: " + (tickCount));
                if (tickCount == 0) {
                    TestHelper.println("[ " + Thread.currentThread().getName() + "] - " + "票已卖完...");

                    // 票卖光了，唤醒所有线程，让他们把继续执行后面的代码
                    lock.notifyAll();
                    //System.exit(0);
                }

                try {
                    // 降低卖票速度，便于观察控制台
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 某售票机线程持续卖票100张,会进入等待,并唤醒其他要卖票的售票机线程
                if (i == 99) {
                    TestHelper.println("[" + Thread.currentThread().getName() + "] - 100张已卖完, going to wait...");
                    // 唤醒某个售票机线程进行售票,需要调用wait()本线程才会真正释放锁
                    lock.notify();

                    // 释放锁，立即释放cpu资源，并根据算法使得某个竞争锁的线程获取锁并开始执行 本线程会一直等待，不再主动获取锁。
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    TestHelper.println("[" + Thread.currentThread().getName() + "] - after wait");
                }
            }
            TestHelper.println("[" + Thread.currentThread().getName() + "] -  code after for");
        }
        TestHelper.println("[" + Thread.currentThread().getName() + "] - code after synchronized");
    }
}

public class WaitNotifyNotifyAllTest {

    public static void main(String[] args) {
        TestHelper.startTest("wait,notify,notifyAll测试");

        // 售票机线程
        Runnable runnable = new TicketPool();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
