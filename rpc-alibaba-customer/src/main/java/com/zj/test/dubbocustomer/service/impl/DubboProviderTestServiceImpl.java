package com.zj.test.dubbocustomer.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.zj.test.dobboprovider.service.DubboProviderTestService;
import com.zj.test.dubbocustomer.service.DubboCustomerTestService;
import com.zj.test.util.TestHelper;
import org.springframework.beans.factory.annotation.Autowired;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 10:45
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Service()
public class DubboProviderTestServiceImpl implements DubboCustomerTestService {
    // 一直为null?
    /**
     * 1.消费者Reference注解参数说明:
     * 1.1.retries参数: 当调用provider接口方法超时时，重试的次数,默认值0
     * 默认值0: 实际上为2次，假设每次都超时，则provider方法共调用3次。
     * n: provider接口方法调用次数: n+1次
     * 注：由于次数是整数，因此参数值的讨论结果如下：
     * 当是n<= -1的整数时，不会重试，重试次数为0。
     * 当n=0时，重试次数为2次。
     * 当n>=1时，重试次数为n次。
     *
     * 1.2.timeout: 提供者接口方法必须在此时间内完成响应，否则抛出异常。
     * 默认值为0，实际上是1000ms。
     * 注意：当timeout<=0 ,注意，实际上是1000ms。
     *
     * 1.3.重试过程
     * 第一次超时达到timeout -> 再次调用provider方法 ->如果超时，继续重试到retries次-> customer代码报错,如果不捕获异常，代码终止。
     *
     * 1.4.异常情况
     * 方法调用异常捕获是可选的，如果不捕获，一旦超时，代码报错，中断。
     * 如果捕获，代码还能继续执行。
     *
     * 当消费者调用提供者方法超时时, 不管retries值为多少,只抛出一次异常：
     * com.alibaba.dubbo.rpc.RpcException
     * ...
     * Caused by: com.alibaba.dubbo.remoting.TimeoutException: Waiting server-side response timeout. start time: 2020-10-23 10:20:47.397, end time: 2020-10-23 10:20:48.401, client elapsed: 0 ms, server elapsed: 1004 ms, timeout: 1000 ms, request: Request [id=2, version=2.0.0, twoway=true, event=false, broken=false, data=RpcInvocation [methodName=timeoutTest, parameterTypes=[], arguments=[], attachments={path=com.zj.test.dobboprovider.service.DubboProviderTestService, interface=com.zj.test.dobboprovider.service.DubboProviderTestService, version=0.0.0, timeout=1000}]], channel: /172.16.200.28:3919 -> /172.16.200.28:20890
     */

    /**
     * 2.provider timeout 和costomer timeout
     * 假设provider和customer都设置了timeout属性,则最终值判断逻辑如下:
     * 1.两者都是0，则为默认值1000ms。                     // true,如果都不设置，默认也为1000ms
     * 2.一方为0，另一方不等于0，则最终值由非0方决定。         // true
     * 3.两者都非0,由customer决定。                       // true
     *
     * 3.provider retries 和customer retries
     * 1.两者都是0，则重试2次。最多共调用provider方法3次。    // true
     * 2.一方为0，另一方不等于0，则由非0方决定。              // true
     * 3.两者都非0，由customer决定。                      // true
     */

    /**
     * 4.retries参数的设置
     * 因为customer调用超时时会抛出异常,因此如果不对异常进行捕获,就会中断代码.
     * 可是对于provider而言，方法会继续执行，直到完成为止。
     * 因此当retries >= 0时，就相当于执行一个多线程的任务。
     *
     * 因此retries的设置要分情况来决定:
     * 1.如果provider多次调用执行的是相同的内容。则建议设置为-1，不重复调用。
     * 如更新用户密码，因为多次执行相同的任务没有任何意义，此时不用重复调用。
     *
     * 2.如果provider多次调用执行的任务不是相同的任务，如每次从用户表中获取1000个不同的用户并更新他们的信息，因为多次重复的调用
     * 修改的是不同的内容，可以适当增加retries、并降低tomeout值来达到一个多线程的效果。需要注意的是这种方式后续的重试线程都会等待至少一个timeout，
     * 越后面的线程等待的时间越久。因此任务还没有执行就已经浪费了很多的启动时间，这种重试方式实现的多线程可用在对速度要求不是很高的场景下，并且它在
     * 一定程度上降低主机的压力。
     * */
    @Reference(timeout = -1,retries = 1,check = false)
    DubboProviderTestService dubboProviderTestService;

    @Override
    public void helloDubboTest() {
        dubboProviderTestService.helloDubbo();
    }

    @Override
    public String timeoutTest() {
        TestHelper.println("invoking provider...");
        //try {
            dubboProviderTestService.timeoutTest();
       // } catch (Exception e) {
         //   e.printStackTrace();
        //}

        return "ok";
    }
}
