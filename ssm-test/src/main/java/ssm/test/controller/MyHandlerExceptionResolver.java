package ssm.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/*ע�⣺�Զ����쳣�����������������ͼֱ�ӷ���null����᷵��ϵͳ�Ĵ���ҳ��:
 * HTTP Status 500 �C Internal Server Error */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		System.err.println("Entering MyHandlerExceptionResolver");
		System.out.println("exception type:" + ex.getClass());
		ModelAndView modelAndView = new ModelAndView();
		
		// ���벻��ȷ��IncorrectCredentialsException
		
		// shiro�쳣����
		if(ex instanceof AuthorizationException) {
			modelAndView.setViewName("success");
		}
		return modelAndView;
	}

}
