package com.zj.test.java.util.concurrent.locks;

import com.zj.test.util.TestHelper;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.StampedLock;

/**
 *
 * <p>
 *     StampedLock测试
 * </p>
 *
 * @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/5/18 9:53
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 *
 *
 * 1.为什么需要StampLock?
 * 写线程饥饿：ReentrantReadWriteLock在大多数的场景下，读操作是大量的，写操作是少量的，当有大量读操作时，可能导致写线程一直阻塞，导致饥饿，就算使用公平锁
 * 也不能解决。
 *
 * 2.StampedLock与可重入读写锁
 * 在支持可重入读写锁的基础上，增加了乐观读锁模式，该模式下，线程在进行读操作时，其他线程仍然可以写。
 *
 * 3.StampedLock特点
 * StampedLock的主要特点概括一下，有以下几点：
 * 1.所有获取锁的方法，都返回一个邮戳（Stamp），Stamp为0表示获取失败，其余都表示成功；
 * 2.所有释放锁的方法，都需要一个邮戳（Stamp），这个Stamp必须是和成功获取锁时得到的Stamp一致；
 * 3.StampedLock是不可重入的；无论是读内写，还是写内读，内部永远无法获取到锁。（如如果一个线程已经持有了写锁，再去获取写锁的话就会造成死锁）
 * 4.StampedLock有三种访问模式：
 * ①Reading（读模式）：功能和ReentrantReadWriteLock的读锁类似
 * ②Writing（写模式）：功能和ReentrantReadWriteLock的写锁类似
 * ③Optimistic reading（乐观读模式）：这是一种优化的读模式。
 * 5.StampedLock支持读锁和写锁的相互转换
 * 我们知道RRW中，当线程获取到写锁后，可以降级为读锁，但是读锁是不能直接升级为写锁的。
 * StampedLock提供了读锁和写锁相互转换的功能，使得该类支持更多的应用场景。
 * 6.无论写锁还是读锁，都不支持Conditon等待
 * 我们知道，在ReentrantReadWriteLock中，当读锁被使用时，如果有线程尝试获取写锁，该写线程会阻塞。
 * 但是，在Optimistic reading中，即使读线程获取到了读锁，写线程尝试获取写锁也不会阻塞，这相当于对读模式的优化，但是可能会导致数据不一致的问题。
 * 所以，当使用Optimistic reading获取到读锁时，必须对获取结果进行校验。
 */
public class StampedLockTest {

    private static StampedLock stampedLock = new StampedLock();

    /**
     * 读
     */
    private static void read() {
        long stamp = stampedLock.readLock();
        TestHelper.println("正在读...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stampedLock.unlockRead(stamp);
        TestHelper.println("读完成");
    }

    /**
     * 写
     */
    private static void write() {
        long stamp = stampedLock.writeLock();
        TestHelper.println("正在写...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stampedLock.unlockWrite(stamp);
        TestHelper.println("写完成");

    }

    /**
     * 乐观读模式
     *
     * 【乐观模式原理】
     * 乐观读不会占用锁，因此”写线程“可以轻易的获得写锁并进行写操作，解决了”写解饿“问题。
     *
     * 【乐观读具体操作】
     * 正确的乐观读操作是：尝试乐观读(tryOptimisticRead)后通过validate验证是否有写线程正在写，
     * 如果没有，则使用乐观读，否则使用普通读。
     */
    private static void optimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        TestHelper.println("code after tryOptimisticRead");

        // 等待一定时间，用来模拟中间发生写操作
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // validate：如果期间没有发生写操作，返回true
        if (!stampedLock.validate(stamp)) {
            TestHelper.println("wait write lock to release");
            stamp = stampedLock.readLock();
            TestHelper.println("普通读");
            stampedLock.unlockRead(stamp);
        } else {
            TestHelper.println("乐观读");
        }

    }

    /**
     * <p>
     *     1.测试: StampLock读写是否互斥?
     * </p>
     *
     * 【结论】
     * 是的，读写互斥。
     *
     */
    @Test
    public void readAndWriteExclusivityTest() {
        // 1.读线程
        new Thread(() -> {
            read();
        }).start();

        // 2.保证读线程已经获取到了锁
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3.写线程
        new Thread(() -> {
            write();
        }).start();

        // 4.保证写线程已经获取到了锁
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 5.读线程
        new Thread(() -> {
            read();
        }).start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     *     2.测试: StampLock乐观读锁细节测试
     * </p>
     *
     * 【结论】
     *
     */
    @Test
    public void optimisticReadTest() {
        /* ----------------------------------------- 测试1：乐观读，中间没有读操作 ---------------------------------------- */
        /*new Thread(() -> {
            optimisticRead();
        }).start();*/

        /* ----------------------------------------- 测试2: 乐观读，中间加入写操作 ---------------------------------------- */
        /*
        测试输出：
        [Thread-0] - code after tryOptimisticRead
        [Thread-1] - 正在写...
        [Thread-0] - wait write lock to release
        [Thread-1] - 写完成
        [Thread-0] - 普通读

        结论：
        1.在调用stampedLock.tryOptimisticRead后，如果有线程调用了stampedLock.writeLock,则stampedLock.validate(stamp)将为false，
        可能存在数据不同步问题，需要调用在调用stampedLock.readLock获取普通读锁。
         */
       /* new Thread(() -> {
            optimisticRead();
        }).start();

        // 确保调用了tryOptimisticRead
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            write();
        }).start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        /* ----------------------------------------- 测试3：读操作是否会影响乐观读？ ---------------------------------------- */
        // 结论：不会
        new Thread(() -> {
            optimisticRead();
        }).start();

        // 确保调用了tryOptimisticRead
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            read();
        }).start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     *     3.测试: StampedLock读读是否共享测试
     * </p>
     *
     * 【出入参记录】
     * [Thread-1] - 正在读...
     * [Thread-0] - 正在读...
     * [Thread-0] - 读完成
     * [Thread-1] - 读完成
     *
     * 【结论】
     * 是共享的。
     *
     */
    @Test
    public void readersAreSharedTest() {
        // 线程1
        new Thread(() -> {
            read();
        }).start();

        // 线程2
        new Thread(() -> {
            read();
        }).start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     *     4.测试: 如果unlock不同的stamp,会怎样？
     * </p>
     *
     * 【结论】
     * 会报错并导致代码中断:
     * java.lang.IllegalMonitorStateException
     * 	at java.util.concurrent.locks.StampedLock.unlockRead(StampedLock.java:563)
     * 	at com.zj.test.java.util.concurrent.locks.StampedLockTest.test(StampedLockTest.java:263)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.lang.reflect.Method.invoke(Method.java:498)
     * 	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
     * 	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     * 	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
     * 	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     * 	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
     * 	at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
     * 	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
     * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
     * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
     * 	at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
     * 	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
     * 	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
     * 	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
     * 	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
     * 	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
     * 	at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
     * 	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     * 	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
     * 	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
     * 	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
     * 	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
     *
     */
    @Test
    public void test() {
        StampedLock stampedLock = new StampedLock();

        long stamp = stampedLock.readLock();
        TestHelper.println("stamp", stamp);
        TestHelper.println("正在读");
        stampedLock.unlockRead(110);
        TestHelper.println("code after unlockRead");
    }

    /**
     * <p>
     *     5.测试: 乐观读和写操作相互影响测试
     * </p>
     *
     *
     */
    @Test
    public void test5() {
        /* ----------------------------------------- 测试1：乐观读过程中是否可写 ---------------------------------------- */
        /*
        出参：
        [Thread-0] - code after tryOptimisticRead
        [Thread-1] - 正在写...
        [Thread-0] - wait write lock to release
        [Thread-1] - 写完成
        [Thread-0] - 普通读

        结论：可以，乐观读过程中如果有其他线程进行了写操作，乐观读就会成为普通读，会等待其他线程释放写锁。
        */
        /*new Thread(() -> {
            optimisticRead();
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            write();
        }).start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        /* ----------------------------------------- 测试2：写的过程能否乐观读 ---------------------------------------- */
        /*
        结论：可以
        如果写的过程进行读乐观读操作，读操作在执行到stampedLock.validate(stamp)时，由于有线程正在写，因此validate的结果为false,
        此时为了获取更新的数据，应该通过获取读锁来等待写操作完成(此时乐观读就变成了普通读)，否则可能导致数据不同步。
         */
        new Thread(() -> {
            write();
        }).start();

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 执行此测试时，将optimisticRead方法中休眠5秒的代码注释掉
        new Thread(() -> {
            optimisticRead();
        }).start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     *     6.测试: StampedLock重入性测试
     * </p>
     *
     * 【结论】
     * 不可重入，无论是写内读，还是读内些，都会导致死锁: 内部无法获取到锁。
     *
     */
    @Test
    public void test6() {
        // 1.读内写
        /*StampedLock stampedLock = new StampedLock();

        long l = stampedLock.readLock();
        TestHelper.println("正在读");
        // 尝试写
        long l1 = stampedLock.writeLock();
        TestHelper.println("正在写");
        stampedLock.unlockWrite(l1);

        stampedLock.unlockRead(l);*/

        //2.写内读
        StampedLock stampedLock = new StampedLock();

        long l = stampedLock.writeLock();
        TestHelper.println("正在写");
        // 尝试写
        long l1 = stampedLock.readLock();
        TestHelper.println("正在读");
        stampedLock.unlockRead(l1);

        stampedLock.unlockWrite(l);


    }


    /* ----------------------------------------- test7 ---------------------------------------- */
    static String s_test7 = "old_string";

    /**
     * test7
     */
    private static void optimisticRead_test7() {
        long stamp = stampedLock.tryOptimisticRead();
        TestHelper.println("code after tryOptimisticRead");

        // validate：如果期间没有发生写操作，返回true
        if (!stampedLock.validate(stamp)) {
            TestHelper.println("wait write lock to release");
            stamp = stampedLock.readLock();
            TestHelper.println("普通读");
            stampedLock.unlockRead(stamp);
        } else {
            TestHelper.println("乐观读正在读取数据");

            //模拟期间发生写操作
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            TestHelper.println("乐观读读取到数据：" + s_test7);
        }

    }

    /**
     * test7
     */
    private static void write_test7() {
        long stamp = stampedLock.writeLock();
        TestHelper.println("正在写...");
        s_test7 = System.currentTimeMillis() + "";
        stampedLock.unlockWrite(stamp);
        TestHelper.println("写完成");

    }

    /**
     * <p>
     *     7.测试: 乐观读是否存在线程安全问题？
     * </p>
     *
     * 【出入参记录】
     * [Thread-0] - code after tryOptimisticRead
     * [Thread-0] - 乐观读正在读取数据
     * [main] - 正在写...
     * [main] - 写完成
     * [Thread-0] - 乐观读读取到数据：1624522862253
     *
     * 可以看到当stampedLock.validate(stamp)为true时，在乐观读的过程中，数据被其他线程更改了。
     *
     * 【结论】
     * stampedLock.validate(stamp)为true，乐观读的过程中，数据可能被其他线程更改，
     * 因此需要在此代码块中判断数据是否被修改。
     *
     * 【注意点】
     *
     */
    @Test
    public void test7() {

        //
        new Thread(() -> {
            optimisticRead_test7();
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        write_test7();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /* ----------------------------------------- test8 ---------------------------------------- */
    @Setter
    @Getter
    static class SafeString {
        String s_test8 = "old_string";
        int version = 0;
    }

    static SafeString safeString_test8 = new SafeString();

    /**
     * test8
     */
    private static void optimisticRead_test8() {
        long stamp = stampedLock.tryOptimisticRead();
        TestHelper.println("code after tryOptimisticRead");


        int oldVersion = safeString_test8.getVersion();
        TestHelper.println("安全读正在读取数据版本: " + oldVersion);

        // validate：如果期间没有发生写操作，返回true
        if (!stampedLock.validate(stamp)) {
            TestHelper.println("wait write lock to release");
            stamp = stampedLock.readLock();
            TestHelper.println("普通读");
            stampedLock.unlockRead(stamp);
        } else {
            TestHelper.println("乐观读正在读取数据");

            //模拟期间发生写操作
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int newVersion = safeString_test8.getVersion();
            if (oldVersion != newVersion)
                TestHelper.println("数据已经被修改，读取放弃");
            else {
                TestHelper.println("乐观读读取数据", safeString_test8.getS_test8());
            }
        }

    }

    /**
     * test8
     */
    private static void write_test8() {
        long stamp = stampedLock.writeLock();
        TestHelper.println("正在写...");
        safeString_test8.setS_test8(System.currentTimeMillis() + "");
        safeString_test8.setVersion(safeString_test8.getVersion() + 1);
        stampedLock.unlockWrite(stamp);
        TestHelper.println("写完成");
    }

    /**
     * <p>
     *     8.测试: 乐观读数据安全实现(解决test7问题)。
     * </p>
     *
     * 【出入参记录】
     * [Thread-0] - code after tryOptimisticRead
     * [Thread-0] - 安全读正在读取数据版本: 0
     * [Thread-0] - 乐观读正在读取数据
     * [main] - 正在写...
     * [main] - 写完成
     * [Thread-0] - 数据已经被修改，读取放弃
     *
     * 【结论】
     *
     * 【注意点】
     *
     */
    @Test
    public void test8() {
        //
        new Thread(() -> {
            optimisticRead_test8();
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        write_test8();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 官方demo
// 注：在官方基础上，有所补充
class Point {
    private double x, y;
    private final StampedLock sl = new StampedLock();

    void move(double deltaX, double deltaY) { // an exclusively locked method
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin() { // A read-only method
        long stamp = sl.tryOptimisticRead();
        double currentX = x, currentY = y;
        // 乐观读总是认为没有线程会获取写锁修改数据，因此需要编码实现检查是否有线程在写，这个方法就是validate,返回true代表没有线程写。
        // 如果表达式(!sl.validate(stamp))为true,意味着有写锁进行写，此时currentX和currentY值可能不对，为了保证数据为修改后的最新数据
        // 此时通过获取读锁来保证数据被修改完后再读取。
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                // 重新获取最新值
                // 这里实际上是刷新数据：获取最新的数据
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    void moveIfAtOrigin(double newX, double newY) { // upgrade
        // Could instead start with optimistic, not read mode
        long stamp = sl.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }
}
