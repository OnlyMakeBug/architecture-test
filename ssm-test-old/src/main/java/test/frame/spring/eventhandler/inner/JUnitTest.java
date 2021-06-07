package test.frame.spring.eventhandler.inner;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.frame.spring.eventhandler.custom.CustomPublisher;

/* 
 * @RunWith(SpringJUnit4ClassRunner.class)
 * @ContextConfiguration("classpath:cn.xhm.frametest.spring.eventhandler.inner.beans.xml")
 */
public class JUnitTest {

	// spring�����¼�����
	@Test
	public void test1() {
		/*
		 * �����¼��ĵ���ʱ���� Ĭ������£�ֻ��refresh����ã������ڴ���ApplicatinContext��ʱ����á�
		 * ֻ���ڵ���close������ܽ���bean��ȡ�����������ķ�������Ӱ�����ApplicationContext��ʹ�ã���Ȼ���Ի�ȡbean��
		 * 
		 * 1.refreshed: 
		 * 1.������ʼ��ʱ���磺new ClassPathXmlApplicationContext(
		 * "classpath:cn/xhm/frametest/spring/eventhandler/beans.xml"); 
		 * 2.�ֶ�����refresh������
		 * ע��refresh����֮����Է���started,stopped���¼���
		 * 
		 * 2.started: ֻ�е���start()ʱ�Żᴥ��started�¼��� ��ʼ��������ʱ�򲻻ᴥ������Ҫ�ֶ�������
		 * 
		 * 3.stopped�� ֻ�е���stop()ʱ�Żᴥ��stopped�¼�
		 * 
		 * 4.close: close�Ժ����start(),stop()��Ч,start()��stop()�����̷���,���ᴥ���¼��������жϴ��롣
		 * �������close֮���ٵ��÷�����ȡbean������쳣�������жϡ�
		 * ���ǵ���refresh()�ǿ��Եģ����Ȼ��Xml�ļ����¼���ApplicationContext��Ȼ�����ContextRefreshedEvent�ӿ�
		 * ע��close����������ͣ�ṩ��ȡbean�����ã��Ժ��������refresh()�󣬿�������ʹ�á�
		 * 
		 * 5.RequestHandledEvent: ��
		 */
		ConfigurableApplicationContext cap = new ClassPathXmlApplicationContext(
				"classpath:test/frame/spring/applicationContext.xml");
		// ��ȡ�Զ����¼��������������¼�
		CustomPublisher cPublisher = cap.getBean(CustomPublisher.class);
		cPublisher.publish();

		//
		cap.close();
	}
}