package test.frame.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @CreateTime��2020��2��10�� ����2:06:58
 * @Author��zhoujian
 * @QQ��2025513
 * @FileDescription��
 * @IsFinished��false
 */

// AfterReturningAdvice �ڷ���֪֮ͨ�󣬽��ڳ��淽������ʱ����֪ͨ�����������׳��쳣ʱ���á�������֪ͨ���Կ�������ֵ�������ܸ�������
public class AdviceImpl implements MethodBeforeAdvice,AfterReturningAdvice{

	public void before(Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		
	}

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
	}
}
