package test.frame.spring.springtask;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ̽��cron���ʽ��Java�е�д��
 */
/*@Service("task2")*/
@Component("Task2")
public class Task2 {
	private static int count ;
	/**java��cron���ʽ��ѡֵ�������Ի���
	 * W��0Ϊ����,1~6��Ӧ��һ������
	 * M��1~12
	 * D:��1��ʼ
	 * H:0~23
	 * M��0~59
	 * S��0~59
	 * 
	 * 
	 * ���ʹ��:
	 * * -->  ���������ֶ�
	 * , --> ���������б�ֵ
	 * ? --> ֻ�����ں�������ʹ��
	 * -  --> ��ʾһ����Χ
	 * / --> �Ȳ�������,���ڷ����ֶ���ʹ��0/15�����ʾΪ0,15,30��45�룬��5/15�ڷ����ֶ��б�ʾ5,20,35,50����Ҳ����ʹ��* /y������ͬ��0/y��
	 * W: �������ڣ���ʾ�뵱������Ĺ�����,����ǰ�������㹤����,�ó������һ�졣���ǵ������½����ڼ�,���ܿ���,��ѡ���µ�ĳ�졣
	 * L: ��������/���� -> �������ڱ�ʾ���µ����һ��,�������ڱ�ʾ��6,ָ���ǵ������е���6,�����Lǰ������ֵ,��5L,���ʾ�������һ������Żᴥ����
	 * LW: �������� --> LW��ʾ���һ��������
	 * #: ֻ�������� --> 4#5��ʾ���µĵ��������5��java��������0��ʾ
	 * C: ��������/����  --> ����5C�������ֶ��о��൱������5���Ժ�ĵ�һ�졣0C�������ֶ����൱�������պ�ĵ�һ�졣
	 * */
	@Scheduled(cron="0 53 9 * * 6")
	public void startWork() {
		System.out.println("Task2 count: " + count);
		count++;
	}
	
	@Test
	public void test1() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("resource/applicationContext.xml");
		System.out.println("after");
		classPathXmlApplicationContext.close();
	}
}