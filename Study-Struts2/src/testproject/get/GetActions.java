package testproject.get;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import freemarker.cache.StrongCacheStorage;

public class GetActions  {
	
	/*
	 * GET������ԣ���������
	 * */
	public String getWithoutParams() {
		System.out.println("getWithoutParams");
		
		//��ȡ����
		HttpServletRequest request = ServletActionContext.getRequest();
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String param = request.getParameter(paramName);
			System.out.println(paramName+": " +  param);
		} 
		
		
		
		return "getWithoutParams";
	}
	

}
