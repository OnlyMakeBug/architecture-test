package test.java.reflection;

import org.junit.Test;

/*
 * @CreateTime��2020��2��12�� ����4:34:09
 * @Author��zhoujian
 * @QQ��2025513
 * @FileDescription��
 * @IsFinished��false
 */

/**
 * ����: Class.getInterfaces()������Щ�ӿڣ�
 * ���ۣ�ֻ�ܷ��ر��ඨ��ͨ��implements��ʾ��ӵĽӿ�,�����ܷ��ظ���ʵ�ֵĽӿڡ�
 * */
public class ReflectionInterfaceTest {
	/**
	 * ����1��ʲô���ĽӿڻᱻClass.getInterfaces����?
	 * 
	 * ��: ��Class���������ʾ����implements InterfaceName...���򲻻ᱻ���أ����㸸��ʵ����
	 * �ýӿ�Ҳû�á�
	 * */
	@Test
	public void test1() {
		Class<?>[] interfacesB = B.class.getInterfaces();
		Class<?>[] interfacesC = C.class.getInterfaces();
		Class<?>[] interfacesD = D.class.getInterfaces();
		System.out.println("-----Bʵ�ֵĽӿ�----");
		
		for (Class<?> interface1 : interfacesB) {
			// interface test.java.reflection.TestInterface
			System.out.println(interface1);
		}
		
		System.out.println("-----Cʵ�ֵĽӿ�----");
		for (Class<?> interface2 : interfacesC) {
			// interface test.java.reflection.TestInterface
			System.out.println(interface2);
		}
		// false
		System.out.println(interfacesC ==null);
		// 0
		System.out.println(interfacesC.length);
		
		System.out.println("-----dʵ�ֵĽӿ�----");
		for (Class<?> interface3 : interfacesD) {
			// interface test.java.reflection.TestInterface
			System.out.println(interface3);
		}
		
		
	}

}

interface TestInterface{
	public void interfaceMethod();
}

class A implements TestInterface{

	public void interfaceMethod() {
		// TODO Auto-generated method stub
		System.out.println("interfaceMethod A");
	}
}

/** ע�����ڸ����Ѿ�ʵ���˽ӿ�,���������ʵ�ֽӿ�,������ʾ��ӷ���,���ǿ��Ը��� */
// -----Bʵ�ֵĽӿ�----
// interface test.java.reflection.TestInterface
class B extends A implements TestInterface{

	@Override
	public void interfaceMethod() {
		// TODO Auto-generated method stub
		System.out.println("interfaceMethod B");
	}
}

// -----Cʵ�ֵĽӿ�----
class C extends A {

	@Override
	public void interfaceMethod() {
		// TODO Auto-generated method stub
		System.out.println("interfaceMethod C");
	}
	
}

// -----dʵ�ֵĽӿ�----
// interface test.java.reflection.TestInterface
class D extends A implements TestInterface{
}







