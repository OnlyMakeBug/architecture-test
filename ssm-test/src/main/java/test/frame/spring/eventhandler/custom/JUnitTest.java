package test.frame.spring.eventhandler.custom;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/* �Զ����¼������� */
public class JUnitTest {
	
	@Test   
	
	public void test1() {
		ConfigurableApplicationContext cac = new ClassPathXmlApplicationContext("classpath:cn/xhm/frametest/spring/eventhandler/beans.xml");
		
		//�����¼�
		CustomPublisher cp = cac.getBean(CustomPublisher.class);
		cp.publish();
		
		cac.close();
	}

}
