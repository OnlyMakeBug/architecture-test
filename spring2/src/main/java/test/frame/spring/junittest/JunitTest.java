package test.frame.spring.junittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.frame.spring.injection.TestPojo;

/*
 * @time  2019��12��31�� ����4:22:43
 * @author  zhoujian
 * @corporation  luku
 * @description  ��ʾspring test��ʹ��
 * @finished  true
 * @finishTime  
 * @version  1.0
 */

/*
 * ���Զ���ʼ��spring���������Բ��������ע��ɨ�裬�Զ����ע�롣
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test/frame/spring/applicationContext.xml")
public class JunitTest {
	
	@Autowired
	TestPojo testPojo;
	
	@Test
	public void test1() {
		//System.out.println(testPojo.testServiceImpl.age);
	}
}