package xhm.struts.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/* �Զ�������������Ҫ�̳���AbstractInterceptor */
public class MyInterceptor1 extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7022051242717027488L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("���󾭹�������1");
		
		//���ͨ�������������ø÷�����������ֹ����
		String obj = invocation.invoke();
		System.out.println("���󷵻ؾ���������1");
		return obj;
	}

}
