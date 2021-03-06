package com.zj.test.java.util.concurrent;

import com.zj.test.util.TestHelper;
import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/10 14:07
 * @description: 线程Thread测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class ThreadInterruptTest {

    /**
     * author: 2025513
     *
     * 1.测试: 默认单线程占用1M内存
     *
     * 创建5w个线程，一定时间内不让结束，观察内存占用情况
     *
     * 结果:
     * 5W线程在idea中占用3.2GB左右,由于idea做了内存优化的，所以没有达到5GB，但是可以明显看到线程是占用内存的。
     *
     * 结论: yield()的确放弃了cpu，只不过
     */
    @Test
    public void test1() {
        // 创建5w个线程，一定时间内不让结束，观察内存情况
        for (int i = 0; i < 50000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * author: 2025513
     *
     * 2.api测试: yield()是否真的放弃了cpu
     * public static void yield()
     * 提示调度程序当前线程愿意放弃当前对处理器的使用。调度器可以忽略这个提示。
     *
     * 测试思路:
     *  yield()调用之后,后面的代码就不会被立马执行，可以比较代码执行所用的时间
     *
     * 结果:
     * yield()后恢复所用时间:
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 24400ns
     * 我已经放弃过cpu了,已经经历了: 1300ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 500ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 700ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     * 我已经放弃过cpu了,已经经历了: 600ns
     *
     * 如果不调用yield():
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 200ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 200ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 0ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     * 我已经放弃过cpu了,已经经历了: 100ns
     *
     * 结论:
     * yield()放弃了cpu资源。
     */
    @Test
    public void test2() {
        /*new Thread(() -> {
            while (true) {
                long start = System.nanoTime();
                TestHelper.println("我已经放弃过cpu了,已经经历了: " + (System.nanoTime() - start) + "ms");
            }

        }).start();*/

        new Thread(() -> {
            while (true) {
                long start = System.nanoTime();
                Thread.yield();
                TestHelper.println("我已经放弃过cpu了,已经经历了: " + (System.nanoTime() - start) + "ms");
            }

        }).start();

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
