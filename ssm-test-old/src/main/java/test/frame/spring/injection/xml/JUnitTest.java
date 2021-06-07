package test.frame.spring.injection.xml;

import org.apache.activemq.console.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.frame.spring.injection.xml.bean.Student;

/**
 * @CreateTime��2020��2��9�� ����4:28:45
 * @Author��zhoujian @QQ��2025513
 * @FileDescription��xml����ʵ��������ע��
 * @IsFinished��false
 */

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration("test/frame/spring/injection/xml/applicationContext.xml")
public class JUnitTest {

	public static void main(String[] args) {
		//����clsspathֻ����Сд
		//classpath:��/�ǿ�ѡ��
		AbstractApplicationContext abstractApplicationContext = new ClassPathXmlApplicationContext(
				"classpath:/test/frame/spring/injection/xml/applicationContext.xml");

		// 1.ͨ��Class���ͻ�ȡbean
		// Student student = (Student)
		// abstractApplicationContext.getBean(Student.class);
		// System.out.println(student);

		// Student student2 = (Student) abstractApplicationContext.getBean("student2");
		// System.out.println(student2);

		// Student student3 = (Student) abstractApplicationContext.getBean("student3");
		// System.out.println(student3);
		
		// Student student4 = (Student) abstractApplicationContext.getBean("student4");
		// System.out.println(student4);
		
		// 2.��ͬ����bean�ж��ʱ��ͨ�����ͻ�ȡ���׳��쳣:
		// org.springframework.beans.factory.NoUniqueBeanDefinitionException
		// Student anyStudent = abstractApplicationContext.getBean(Student.class);
		// System.out.println(anyStudent);
		//false 
		System.out.println(abstractApplicationContext.getBean("student4") == abstractApplicationContext.getBean("student4"));
	}
}
