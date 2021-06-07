package test.frame.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/*
 *5��֪ͨadvice(����ִ��˳����д):
 *
 *1.ǰ��  before����around֮ǰ���á�
 * 
 *2.����  around
 *
 *3.�쳣  afterThrowing����around-after֮ǰִ�С�
 *
 *4.��������  afterReturning����around-after��ִ�С�
 *
 *5.���� after
 * 
 *����ִ��˳��(�����ע����ʽ�����÷�ʽ˳�������)��
 *before -> around-before -> (method) 
 *	  -> after-throwing -> around-after 
 *    -> after-returning -> after 
 * */

/**
 * ֪ͨ��
 * */
public class MyAdvice {
	
	public void before() {
		System.out.println("before");
	}
	
	public void after() {
		System.out.println("after");
	}
	
	public Object around(ProceedingJoinPoint joinPoint) {
		System.out.println("round-before");
		Object object = null;
		try {
			//  ִ��Ŀ�귽��
			object = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("round-after");
		return object;
	}
	
	public void afterThrowing() {
		System.out.println("afterThrowing");
	}
	
	public void afterReturning() {
		System.out.println("afterReturing");
	}
}