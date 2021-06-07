package test.frame.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
  * @time��2020��1��2�� ����9:28:46
  * @author��zhoujian
  * @corporation��luke
  * @description�������࣬�����е��֪ͨ
  * @finished��false
  * @finishTime��
  * 
  */

/**
 * ע�⣺��ȷ��ָ��˳��
 * start...
   advice around-before    @Around
   advice before           @Before
   in des method		   des method
   advice around-after     @Around 
   advice after            @After 
 * 						   @AfterReturning(�������������Ż�ִ��)
 * 						   @afterThrowing(�����쳣�Ż�ִ��)
 * */

/*
 * @Aspect�����@Service������ʵ����ע��һ��ʹ�ã�������Ч
 * */
@Service
@Aspect
public class AspectClass {
	
	/**������ʾJavaע������Aspect*/
	
	/**
	 * ����,�����е㣬����ʱͨ��������()�����ã���Ҫ����()
	 * 
	 * @Pointcutע����Լ����κη������͵ķ����ϣ����Ƿ�������Ҫ���ض�Ӧ���ͣ���������ͨ�����롣
	 * ʵ���ϣ����벻��ִ�С�
	 * ��ˣ�һ�㶨�巵������Ϊvoid�ҷ�����Ϊ�յķ������ɡ�
	 * */
	@Pointcut("execution(* test.frame.spring.aop..*.*(..))")
	public String myCutpoint() {System.out.println("dasdadsadd");return "112";}
	
	/**
	 * before֪ͨ
	 * */
	@Before(value="myCutpoint()")
	public void before(JoinPoint joinPoint) {
		System.out.println("--------------�������۽��׷�������---------------");
		//  getTarget() - ��ȡ�е����ʵ��
		//  class test.frame.spring.aop.annotation.Student
		//System.out.println("joinPoint.getTarget().getClass():" + joinPoint.getTarget().getClass());
		
		System.out.println("advice before");
	}
	
	/**
	 * after
	 * */
	@After(value="myCutpoint()")
	public void after() {
		System.out.println("advice after");
	}
	
	/**
	 * ע�⣺afterReturning֪ͨ������ִ�С�
	 * @AfterReturning����˵����
	 * 1.value��pointcut����һ�������ܶ����е���ʽ�����÷����е㣬�����߶�����ʱ�����߸���ǰ�ߡ�
	 * 2.returning��ʾ֪ͨ������������Ŀ�귽������ֵ���β�����
	 * 3.ע��returning�β����ͱ�ʾ��Щ�������͵ķ����ᱻ���أ�Object���������������з������磺
	 * @AfterReturning(value = "myCutpoint()", returning = "result")
	   public void afterReturing(JoinPoint joinPoint,String result) {}
	         ��ֻ�������е���ʽ���ҷ���ֵΪString���͵�Ŀ�귽���Ż�ִ��@AfterReturning֪ͨ������
	   int/Integer������int����ֵ����װ������,���β�����Ϊint,�Ὣ��int�������͵�Ŀ�귽��ת����Integer
	 * 
	 * ���ϲ�������������ں�around���ʹ��ʱ��ֻ������ƥ��ʱ���Ż�ִ�д˷���������ִ�С�
	 * */
	@AfterReturning(value = "myCutpoint()", returning = "result")
	public void afterReturing(JoinPoint joinPoint,int result) {
		System.out.println("advice afterReturning");
		System.out.println("���ص��ķ���ֵ:" + result);
	}
	
	/**
	 * around
	 * 
	 *ע�⣺ProceedingJoinPoint.proceed()���ܷ���null
	 * ע�⣺
	 * 1.around��.ProceedingJoinPoint.proceed()���������ܵ�@AfterReturningӰ�죬���������
	 * @AfterReturning�β����ͣ���proceed()����ֵΪnull����������������β�Ҫ��afterThrowing����
	 * Ҳ����ִ�С�
	 * */
	@Around(value="myCutpoint()")
	public Object around(ProceedingJoinPoint pjp) {
		Object retVal = null;
		try {
			System.out.println("advice around-before");
			retVal = pjp.proceed();
			System.err.println("retValue:   " + retVal);
			System.out.println("advice around-after");
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	/**
	 * afterThrowing
	 * */
	@AfterThrowing("myCutpoint()")
	public void afterThrowing() {
		System.out.println("advice afterThrowing");
	}
	
	/*
	 *���涨�����ע���ټ�
	 *@Aspect: ��Ҫ��ʵ����ע��(@Component,@Service��)һ��ʹ��,����ʹ��û���κ����塣
	 *
	 *@Pointcut:�����е���ʽ��
	 *
	 *@Before
	 *@Around
	 *@After
	 *@AfterReturning
	 *@AfterThrowing
	 * */
}
