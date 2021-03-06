package com.zj.test.spring.task.task;

import com.zj.test.spring.cycle_dependeny.C;
import com.zj.test.util.TestHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 *      task1
 * </p>
 *
 * @author: zhoujian
 * @e-mail: 2025513@qq.com
 * @create-time: 2021/6/7 13:34
 * @is-finished: false
 *
 */

@Component
public class Task1 {

    public Task1() {
        TestHelper.println("Task1 constructed.");
    }
//    Cron 表达式(以下掌握就够用了)：
//
//    Cron表达式是一个字符串，字符串以5或6个空格隔开，分为6或7个域，每一个域代表一个含义，Cron有如下两种语法格式：
//
//            1、Seconds Minutes Hours DayofMonth Month DayofWeek Year（这种我使用失败，加上year就会报错，原因未知，有人知道望留言告知）
//            2、Seconds Minutes Hours DayofMonth Month DayofWeek
//
//    例  "0 0 12 ? * WED" 在每星期三下午12:00 执行,
//
//    个别子表达式可以包含范围, 例如，在前面的例子里("WED")可以替换成 "MON-FRI", "MON, WED, FRI"甚至"MON-WED,SAT".
//
//    每一个字段都有一套可以指定有效值，如
//
//    Seconds (秒)         ：可以用数字0－59 表示，
//
//    Minutes(分)          ：可以用数字0－59 表示，
//
//    Hours(时)             ：可以用数字0-23表示,
//
//    Day-of-Month(天) ：可以用数字1-31 中的任一一个值，但要注意一些特别的月份
//
//    Month(月)            ：可以用0-11 或用字符串  “JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV and DEC” 表示
//
//    Day-of-Week(每周)：可以用数字1-7表示（1 ＝ 星期日）或用字符口串“SUN, MON, TUE, WED, THU, FRI and SAT”表示
//
//	●星号(*)：可用在所有字段中，表示对应时间域的每一个时刻，例如，*在分钟字段时，表示“每分钟”；
//
//            ●问号（?）：该字符只在日期和星期字段中使用，虽然我现在不知道它的值是多少，但是它的值是唯一的，通过日期可以推出星期，通过本周是周几也可以推出日期。
//
//            ●减号(-)：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12；
//
//            ●逗号(,)：表达一个列表值，如在星期字段中使用“MON,WED,FRI”，则表示星期一，星期三和星期五；
//
//            ●斜杠(/)：x/y表达一个等步长序列，x为起始值，y为增量步长值。如在分钟字段中使用0/15，则表示为0,15,30和45秒，而5/15在分钟字段中表示5,20,35,50，你也可以使用*/y，它等同于0/y；
//
//            ●L：该字符只在日期和星期字段中使用，代表“Last”的意思，但它在两个字段中意思不同。L在日期字段中，表示这个月份的最后一天，如一月的31号，非闰年二月的28号；如果L用在星期中，则表示星期六，等同于7。但是，如果L出现在星期字段里，而且在前面有一个数值 X，则表示“这个月的最后X天”，例如，6L表示该月的最后星期五；
//
//            ●W：该字符只能出现在日期字段里，是对前导日期的修饰，表示离该日期最近的工作日。例如15W表示离该月15号最近的工作日，如果该月15号是星期六，则匹配14号星期五；如果15日是星期日，则匹配16号星期一；如果15号是星期二，那结果就是15号星期二。但必须注意关联的匹配日期不能够跨月，如你指定1W，如果1号是星期六，结果匹配的是3号星期一，而非上个月最后的那天。W字符串只能指定单一日期，而不能指定日期范围；
//
//            ●LW组合：在日期字段可以组合使用LW，它的意思是当月的最后一个工作日；
//
//            ●井号(#)：该字符只能在星期字段中使用，表示当月某个工作日。如6#3表示当月的第三个星期五(6表示星期五，#3表示当前的第三个)，而4#5表示当月的第五个星期三，假设当月没有第五个星期三，忽略不触发；
//
//            ●com.zj.test.spring.cycle_dependeny.C：该字符只在日期和星期字段中使用，代表“Calendar”的意思。它的意思是计划所关联的日期，如果日期没有被关联，则相当于日历中所有日期。例如5C在日期字段中就相当于日历5日以后的第一天。1C在星期字段中相当于星期日后的第一天。
//
//            1）Cron表达式的格式：秒 分 时 日 月 周 年(可选)。
//
//    字段名                 允许的值                        允许的特殊字符
//    秒                         0-59                               , - * /
//    分                         0-59                               , - * /
//    小时                     0-23                               , - * /
//    日                         1-31                               , - * ? / L W C
//    月                         1-12 or JAN-DEC         , - * /
//    周几                     1-7 or SUN-SAT           , - * ? / L C #
//    年 (可选字段)     empty, 1970-2099      , - * /
//
//            2）Cron表达式范例：
//
//    每隔5秒执行一次：*/5 * * * * ?
//
//    每隔1分钟执行一次：0 */1 * * * ?
//
//    每天23点执行一次：0 0 23 * * ?
//
//    每天凌晨1点执行一次：0 0 1 * * ?
//
//    每月1号凌晨1点执行一次：0 0 1 1 * ?
//
//    每月最后一天23点执行一次：0 0 23 L * ?
//
//    每周星期天凌晨1点实行一次：0 0 1 ? * L
//
//    在26分、29分、33分执行一次：0 26,29,33 * * * ?
//
//    每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?

    /**
     * spring cron表达式为6字段而不是7字段：
     * Encountered invalid @Scheduled method 'test1': Cron expression must consist of 6 fields (found 7 in "* * * * * ? 2021")
     */
    //@Scheduled(cron = "* * * * * ? 2021")


    /**
     * 1.测试：@Scheduled入门测试 + 长耗时任务对调度周期的影响
     *
     * 【结论】
     * @Scheduled任务当耗时大于或等于调度间隔时，会导致调度周期变长。
     * 因此对于长耗时的任务，应使用线程池去执行。
     */
    //@Scheduled(cron = "* * * * * ?")
    public void test1(){
        TestHelper.println("task1.test1()");
        TestHelper.println(new Date());

        // 长耗时的任务会导致任务调度周期变长，如添加以下代码，会导致调度周期变成2秒。
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        /*长耗时任务应使用线程池来执行，这里测试用线程来代替。
        这样调度周期就能保证一致*/
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * <p>
     *     2.测试: cron表达式x/y测试
     * </p>
     *
     * 【出入参记录】
     * --1.5/10
     * 当秒为5,15,25,35,45,55时触发任务：
     * [scheduling-1] - task1.test2(): 秒：5/10测试
     * [scheduling-1] - Mon Jun 07 14:36:45 CST 2021
     * [scheduling-1] - task1.test2(): 秒：5/10测试
     * [scheduling-1] - Mon Jun 07 14:36:55 CST 2021
     * [scheduling-1] - task1.test2(): 秒：5/10测试
     * [scheduling-1] - Mon Jun 07 14:37:05 CST 2021
     * [scheduling-1] - task1.test2(): 秒：5/10测试
     * [scheduling-1] - Mon Jun 07 14:37:15 CST 2021
     * [scheduling-1] - task1.test2(): 秒：5/10测试
     * [scheduling-1] - Mon Jun 07 14:37:25 CST 2021
     *
     * --2.* /10
     * [scheduling-1] - Mon Jun 07 14:39:00 CST 2021
     * [scheduling-1] - Mon Jun 07 14:39:10 CST 2021
     * [scheduling-1] - Mon Jun 07 14:39:20 CST 2021
     * [scheduling-1] - Mon Jun 07 14:39:30 CST 2021
     * [scheduling-1] - Mon Jun 07 14:39:40 CST 2021
     * [scheduling-1] - Mon Jun 07 14:39:50 CST 2021
     * [scheduling-1] - Mon Jun 07 14:40:00 CST 2021
     * [scheduling-1] - Mon Jun 07 14:40:10 CST 2021
     * [scheduling-1] - Mon Jun 07 14:40:20 CST 2021
     *
     * 【结论】
     *
     * 【注意点】
     *
     */
    //@Scheduled(cron = "*/10 * * * * ?")
    public void test2(){
        TestHelper.println("task1.test2(): 秒：5/10测试");
        TestHelper.println(new Date());

    }

    /**
     * 注：
     * 'cron', fixedDelay,'fixedDelay(String)', or 'fixedRate(String)'...属性，只能出现一个，因此使用 fixedDelay，则方法会立刻执行一次。
     *
     **
     * 实现固定间隔执行：以下写法可以实现spring初始化后，延时10秒再执行任务，任务执行完后5秒后再次执行任务。
     *
     * <pre>
     * <strong>NOTE:</strong>
     * fixedDelay/fixedDelayString：上一次任务完成后到下一次任务开始的间隔，区别在于字符串
     * 注意：第一次任务执行之前，也会等待fixedDelay时长。
     *
     * fixedRate/fixedRateString：上一次任务开始到下一次任务开始的间隔，区别在于字符串
     * 如果任务耗时较长，会影响到调度周期，因此对于长耗时任务，要用线程池来执行。
     *
     * initialDelay/ initialDelayString：第一次任务开始前，初始延时。
     *
     * zone：时区，接收一个java.util.TimeZone#ID。cron表达式 会基于该时区解析。默认是一个空字符串，即取服务器所在地的时区。比如我们一般使用的时区Asia/Shanghai。该字段我们一般留空。
     * </pre>
     * */
    //@Scheduled(fixedDelayString=("5000"),initialDelay=10000)

    //@Scheduled(fixedDelayString=("2000"))

    //如果不提供参数会报错：Encountered invalid @Scheduled method 'test3': Exactly one of the 'cron', 'fixedDelay(String)', or 'fixedRate(String)' attributes is required
    //@Scheduled()

    //@Scheduled(fixedRate=2000)

    //第一次任务执行之前，也会等待fixedDelay时长。
    //@Scheduled(fixedDelay=10000)
    public void test3() {
        System.out.println("Task1.test3()");
        TestHelper.println(new Date());
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestHelper.println(new Date());*/
    }

}
