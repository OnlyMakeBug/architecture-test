package com.zj.test.java.util;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.Objects;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/22 10:39
 * @description: java.util.Objects类的使用
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class ObjectsTest {
    /**
     * 1.T requireNonNull(T obj)
     * 作用: 检测对象是否为null，如果为null，抛出NullPointerException,代码中断执行:
     * java.lang.NullPointerException
     * at java.util.Objects.requireNonNull(Objects.java:203)
     * at com.zj.test.java.util.ObjectsTest.test1(ObjectsTest.java:20)
     * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * at java.lang.reflect.Method.invoke(Method.java:498)
     * at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
     * at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     * at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
     * at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     * at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
     * at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
     * at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
     * at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
     * at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
     * at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
     * at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
     * at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
     * at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
     * at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     * at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
     * at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
     * at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
     * at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
     */
    @Test
    public void requireNonNull() {
        Objects.requireNonNull(null);
        // 不会执行到
        TestHelper.println("code after requireNonNull");
    }

    /**
     * 2.requireNonNull(T obj,String message)
     * 作用: 检测对象是否为null，如果为null，抛出NullPointerException并且打印message异常信息，代码中断执行。
     * <p>
     * java.lang.NullPointerException: content can not be null
     * <p>
     * at java.util.Objects.requireNonNull(Objects.java:228)
     * at com.zj.test.java.util.ObjectsTest.requireNonNull1(ObjectsTest.java:60)
     * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * at java.lang.reflect.Method.invoke(Method.java:498)
     * at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
     * at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     * at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
     * at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     * at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
     * at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
     * at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
     * at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
     * at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
     * at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
     * at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
     * at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
     * at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
     * at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     * at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
     * at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
     * at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
     * at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
     */
    @Test
    public void requireNonNull2() {
        Objects.requireNonNull(null, "content can not be null");
        // 不会执行到
        TestHelper.println("code after requireNonNull");
    }

    /**
     * T requireNonNull(T obj, Supplier<String> messageSupplier)
     * <p>
     * Supplier为一接口，对象需要实现Supplier<String>接口
     * 该方法此占位不测试
     */
    @Test
    public void requireNonNull3() {
    }

    /**
     * 3.equals(Object,Object)
     *
     * <p>
     * 比较两个对象是否相等
     * <p>
     * Objects.equals(null,null)  ->  true
     *
     * @return 如果两者为null，返回true，否则通过equals方法进行比较。
     */
    @Test
    public void equals() {
        TestHelper.println("equals(null,null)", Objects.equals(null, null));
    }

    /**
     * 4. boolean isNull(@Nullable Object obj)
     *
     * <p>
     * 返回obj对象是否为null
     */
    @Test
    public void isNull() {
        TestHelper.println("Objects.isNull(null)", Objects.isNull(null));//true
        TestHelper.println("Objects.isNull(\"Hello World\")", Objects.isNull("Hello World"));//false
    }

    /***
     * 5.boolean nonNull(@Nullable Object obj)
     *<p>非null返回true，与isNull相反
     */
    @Test
    public void nonNull() {
        TestHelper.println("Objects.nonNull(null)", Objects.nonNull(null));//false
        TestHelper.println("Objects.nonNull(\"Hello World\")", Objects.nonNull("Hello World"));//true
    }

    /**
     * 6.Objects.toString(obj)      返回String类型
     * <p>
     * String toString(@Nullable Object o,String nullDefault)
     * 如果o为null,则使用默认值nullDefault
     *
     * <p><pre>
     *      toString(null,null)  >  null
     *      toString("Hello World","is null")  >  Hello World
     *      toString(null,"is null")  >  is null
     */
    @Test
    public void testToString() {
        // 相当于String.valueOf("Hello World")
        TestHelper.println(Objects.toString("Hello World"));


        TestHelper.println("toString(\"Hello World\",\"is null\")"
                , Objects.toString("Hello World", "is null"));

        TestHelper.println("toString(null,\"is null\")"
                , Objects.toString(null, "is null"));

        TestHelper.println("toString(null,null)"
                , Objects.toString(null, null));
    }

    /***
     * 7.boolean deepEquals(@Nullable Object a,@Nullable Object b
     * 该方法除了比较对象，还会比较数组类型，只有两个数组长度相同且值都相同，才会返回true。
     */
    @Test
    public void test() {
        int[] b1 = new int[1];
        b1[0] = 1;

        int[] b2 = new int[1];
        b2[0] = 1;
        TestHelper.println(Objects.deepEquals(b1, b2));//true

        int[] b3 = new int[3];
        b3[0] = 1;

        int[] b4 = new int[4];
        b4[0] = 1;

        int[] b5 = new int[3];
        b5[0] = 2;
        TestHelper.println(Objects.deepEquals(b3, b4));//false,数组长度不同
        TestHelper.println(Objects.deepEquals(b3, b5));//false,元素值存在不同
    }

    /**
     * 8.public static int hashCode(@Nullable Object o)
     * 获取哈希值
     *
     * @return 如果o为null，返回0，否则返回0.hasCode()
     */
    @Test
    public void testHashCode(){
        TestHelper.println("hasCode(\"Hello World\")",Objects.hashCode("Hello World"));
        TestHelper.println("hasCode(null)",Objects.hashCode(null));
    }
}
