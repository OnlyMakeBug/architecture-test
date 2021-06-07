package test.frame.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.frame.spring.injection.TestPojo;
import test.frame.spring.injection.TestServiceImpl;

/*
 * @time  2019��12��31�� ����2:56:26
 * @author  zhoujian
 * @corporation  luku
 * @description   
 * @finished  false
 * @finishTime  
 * @version  1.0
 */
public class JunitTest {
	
	public static void main(String[] args) {
		
		// ��ɨ��base-package���¼����Ӱ�����
		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/frame/spring/applicationContext.xml");
		
		TestServiceImpl testServiceImpl = applicationContext.getBean(TestServiceImpl.class);
		//testServiceImpl.test1();
		System.out.println(testServiceImpl.username);
		//System.out.println(testServiceImpl.age);
		//System.out.println(testServiceImpl.firstEnter);
		//  bean name�Ǵ�Сд���ֵ�,myTestServiceImpl ��myTestServiceimpl����һ���£��ᵼ���쳣
		//TestServiceImpl testServiceImpl1 = (TestServiceImpl) applicationContext.getBean("myTestServiceImpl");
		//testServiceImpl1.test1();
		
		TestPojo testPojo = applicationContext.getBean(TestPojo.class);
		testPojo.testServiceImpl.test1();
		/*testPojo.testServiceImpl2.test1();
		testPojo.testServiceImpl3.test1();*/
		//testPojo.testServiceImpl4.test1();
		
		// �ᵼ��bean ���ٷ����ĵ��á�
		applicationContext.close();
	}
}



