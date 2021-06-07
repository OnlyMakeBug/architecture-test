package test.frame.spring.aop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * ������
 * */
public class JUnitTest {
	
	@Test
	public void test1() {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:test/frame/spring/aop/beans.xml");
		
		MyCutPoints cutPoints = context.getBean(MyCutPoints.class);
		
		//cutPoints.add();
		
		//cutPoints.privateMethod();
		
		// 1.public/ȱʡ���εķǾ�̬�����ᱻAOP����
		// 2.�е��ڵ��������е�,�����õ��е㲻�ᴥ��AOP��
		//cutPoints.staticMethod();
		
		//context.close();
		
		//cutPoints.methodWithParam("Hello",true);
		
		// cutPoints.methodWithParam("Hello",1);
		/*cutPoints.methodWithParam("Hello", 1.0);
		cutPoints.methodWithParam("Hello", false);

		cutPoints.methodWithParam("", new Integer(1));*/
		cutPoints.methodWithParam("");
		
		//System.out.println(-1&1);
	}
	
	public static void main(String[] args) {
		FileOutputStream fileOutputStream = null;
		Scanner scanner = null;
		
		try {
			fileOutputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\user.txt"),true);
		    scanner = new Scanner(System.in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			System.out.println("����������:");
			String content = scanner.nextLine();
			System.out.println("�û�����:" + content);
			
			try {
				fileOutputStream.write(content.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
