package xhm.struts.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/* �Զ�������������Ҫ�̳���AbstractInterceptor */
public class MyInterceptor2 extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9213772179815702636L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("���󾭹�������2");
		
		//���ͨ�������������ø÷�����������ֹ����
		String obj = invocation.invoke();
		System.out.println("���󷵻ؾ���������2");
		return obj;
	}

}
