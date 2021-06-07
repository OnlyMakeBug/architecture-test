package ssm.test.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zj.service.TestService;

@Controller
@RequestMapping("/ssmtest")
public class SSMTestController {
	/**
	 * �yԇcontroller��ɨ���Ƿ���
	 * */
	@RequestMapping(value="controllerTest.action",method= {RequestMethod.POST})
	public String test1(String username,@RequestBody User user ) {
		System.out.println("controllerTest.action");
		System.out.println(username);
		System.out.println(user);
		return "/jsp/success.jsp";
	}
	
	/**
	 * �����������Ƿ���ȷ����
	 * */
	@RequestMapping("interceptorTest.action")
	public String test2() {
		return "/jsp/success.jsp";
	}
	
	/**
	 * �����������Ƿ���ȷ����
	 * */
	@RequestMapping("exceptionTest.action")
	public String test3() {
		@SuppressWarnings("unused")
		int i = 1/0;
		
		return "/jsp/success.jsp";
	}
	
	/**
	 * ���ʻ�����
	 * */
	@RequestMapping("localeTest.action")
	public String test4(HttpServletRequest request) {
		
		
		//Locale locale  = new Locale("en","US");
		//request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		
		//�ֶ�����Locale
		//request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		
		return "/jsp/locale.jsp";
	}
	
	@RequestMapping("uploadTest.action")
	public String test5(@RequestParam(value="file1",required=false) MultipartFile file1) {
		System.err.println("�ϴ�����");
		System.err.println("name: " + file1.getName());
		System.err.println("originalFileName: " + file1.getOriginalFilename());
		System.err.println("ContentType: " + file1.getContentType());
		System.err.println("size: " + file1.getSize());
		try {
			System.err.println("bytes: " + file1.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/jsp/success.jsp";
	}
	
	/*
	 * ����json
	 * @ResponseBodyע�������publicǰ�򷵻�����ǰ��������
	 * */
	@RequestMapping("jsonTest.action")
	public @ResponseBody User test6() {
		User user = new User();
		user.setUsername("xiaohongmao");
		user.setAge(15);
		return user;
	}
	
	/*���ԣ�ǰ�˿�����url-partternΪ/*���ܷ���������jsp
	 * ���ܷ��ʣ���Ϊ/*�����ûᵼ�´���ҵ���Servlet����ȱʧ
	 *
	 * No mapping found for HTTP request with URI [/MavenSsm/jsp/success.jsp] in DispatcherServlet with name 'springmvc'
	 * */
	@RequestMapping("test1Jsp")
	public String test7() {   
		return "/jsp/success.jsp";    
	}
	
	/**
	 * ������ͼ���ƽ���������
	 * �Ƿ�ɹ�����
	 * */
	@RequestMapping("testInternalResourceViewResolver")
	public String test8() {   
		return "success";    
	}
	
	/**
	 * �����쳣ӳ�䴦����:�������Զ����쳣���������鷳��ֻ��Ҫָ���쳣����ͼ��Դ���ɿ��ٴ����쳣��
	 * �Ƿ�ɹ�����
	 * */
	@RequestMapping("testSimpleMappingExceptionResolver")
	public String test9() {  
		@SuppressWarnings("unused")
		int i = 1/0;
		return "success";    
	}
	
	@Autowired
	TestService testServiceImpl;
	/**
	 * ��⶯̬����Դ
	 * �Ƿ�ɹ��������������ݿ�ok(�������ò�����targetDataSources�е�����Դ���֣����ᱨ��)������DynamicDataSource.determineCurrentLookupKey��û�е��ã�
	 * ֻ�е�ִ�����ݿ����ʱ���Ż���ø÷���
	 * */
	@RequestMapping("testDynamicDataSource")
	public String test10() {  
		testServiceImpl.test1();
		return "success";    
	}
	
	/**
	 * ����������
	 * ����ɹ�������ִ��sqlǰ��ע��ͬ��������:
	 * Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4256f6c8]
	 * 
	 * �ͷţ�
	 * Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4256f6c8]
	 * �ύ
	 * Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4256f6c8]
	 * ȡ��ע��
	 * Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4256f6c8]
	 * �ر�
	 * Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4256f6c8]
	 * */
	@RequestMapping("testTransactionManager")
	public String test11() {  
		testServiceImpl.insertARow(100);
		return "success";    
	}
	
	/**
	 * <pre>
	 * ����ʵ��shiro ��֤/Ȩ����֤
	 * 
	 *δ��Ȩ��500�쳣��
	 * org.apache.shiro.authz.AuthorizationException:
	 *     Not authorized to invoke method: public java.lang.String ssm.test.controller.SSMTestController.test12()
	 * 
	 * Ĭ������£������ע�⣬������SecurityUtils��ط��������ᴥ����֤/��֤
	 * </pre>
	 * */
	@RequestMapping("testshiro")
	public void test12() {  
		
		// false 
		System.out.println("isRemembered:" + SecurityUtils.getSubject().isRemembered());
		
		System.out.println("(#^.^#)");
		// 1.�����֤ - ���û���¼ʱ���������´����߼�
		System.err.println("��ʼ��֤...");
		UsernamePasswordToken token = new UsernamePasswordToken("zhoujian", "123456");
		// ����remember Cookie Ĭ��false,rememberMe=deleteMe; Path=/SsmDevProject; Max-Age=0; Expires=Fri, 24-Jan-2020 03:32:50 GMT
		 
		//true:<!-- ���token.setRememberMe(true) ����������Cookie��-->
		/*<!-- Set-Cookie:rememberMe=deleteMe; Path=/SsmDevProject; Max-Age=0; Expires=Fri, 
				24-Jan-2020 04:24:54 GMT 
				Set-Cookie:rememberMe=FUT++bPZ8SEZz6n7EZ3fW6L1QK/c29zT1irtwHn+um4WNZpU7yuSh9uYABUGXH10Wk8Z1zMLgOpFzFwJiceyjyH0Xcxx/1WpYfumBdw9kMh8ue/YOTqcL0DosUFxmOO1pQcSO/U3jY+/jGe4TBA+2nXCsXnBxzNSJFR7SG5P4MC5EuNcWJ49grACnREBlukgyxN9n64CEaGpMTd1uF9SWKXLwC66c2Dgp09cjz8qB3A/pCwoTN0tbnqpq0TKcDIHxRdIU/elNRjtFJc6hUv5qwIxZklZGWSAfZq+2piPTOVZxCaaLDLpT4HTq6+v5QBsphuGOJ8Y6bNY+al/ieu0IY0AzLi7KNnaxxsuOEtC6H6hbvgevJvB4nGEufRrxKSWAcuxzsI6ad2qIMYz2u9SQDDICq529LGeRjcgFyO3OkEqXe8e7fjH4EDhbRS/0pvMzjP3qsL4jeaBSpZ2Y/uS/0Xy++G29V8qZ39maf8tRU3zF8YcB1W+VvJP/pUIs0Pv; 
				Path=/SsmDevProject; Max-Age=2592000; Expires=Mon, 24-Feb-2020 04:24:54 GMT; 
				HttpOnly -->*/
		token.setRememberMe(true);
		//  ��֤token
		//  account-�˺� 
		//  Credential - �൱������
		//  ���������󣺷���500ҳ�棺org.apache.shiro.authc.IncorrectCredentialsException: Submitted credentials for token [org.apache.shiro.authc.UsernamePasswordToken - zhoujian, rememberMe=false] did not match the expected credentials.
		SecurityUtils.getSubject().login(token);
		System.out.println("��֤�ɹ����������ִ��");
		
		// 2.Ȩ����֤
		System.err.println("��ʼȨ����Ȩ...");
		//��֤ʧ�ܲ����ܳ��쳣����Ҫ�Լ��жϽ��
		boolean permitted = SecurityUtils.getSubject().isPermitted("delete");
		System.out.println((permitted ? "Ȩ����֤�ɹ�": "Ȩ����֤ʧ��"));
		System.out.println("--------");
		
		// false
		System.out.println("isRemembered:" + SecurityUtils.getSubject().isRemembered());
		
		// ע�⣺�����url�Ǳ���֤������loginURL,Ӧ�÷�������Ϊvoid�����򲻻���ת��Ŀ��url��
		// return "authencation";    
	}
	
	/**
	 * <pre>
	 * @RequiresAuthentication ʹ�ø�ע���ע���࣬ʵ���������ڷ��ʻ����ʱ����ǰSubject�����ڵ�ǰsession���Ѿ�����֤�����û��ʹ��SecurityUtils�����֤��������δͨ����֤�����׳�UnauthenticatedException�쳣��Controller��������ִ�С�
	 * @RequiresGuest ʹ�ø�ע���ע���࣬ʵ���������ڷ��ʻ����ʱ����ǰSubject�����ǡ�gust����ݣ��οͣ�������Ҫ������֤������ԭ�ȵ�session�д��ڼ�¼��ע�⣺
	 * ֻ���οͿ��Է��ʣ������ǰ�û��Ѿ���֤����ݣ����ܷ��ʣ�����UnauthenticatedException: Attempting to perform a guest-only operation. The current Subject is not a guest (they have been authenticated or remembered from a previous login). Access denied.
	 * @RequiresPermissions ����ͬʱӵ������������Ȩ�ޣ�����UnauthorizedException: Subject does not have permission [xxxx]
	 * @RequiresRoles Ҫ��ָ���Ľ�ɫȨ�ޣ���ɫȨ��ͨ��simpleAuthorizationInfo.addRole(..)��ӣ���������Ȩ��
	 * @RequiresUser ��֤�û��Ƿ񱻼��䣬user�����ֺ��壺һ���ǳɹ���¼�ģ�subject.isAuthenticated() ���Ϊtrue����
     * ����һ���Ǳ�����ģ�subject.isRemembered()���Ϊtrue����
	 * </pre>
	 *   
	 * */
	@RequestMapping(value="shiroannotation")
	// ��¼�󣬿���������������ǿ��Է��ʣ�ֻҪsid cookie���ڣ���Ϊ��¼��Ϣ�Ѿ����ڷ�����
    @RequiresAuthentication
	// @RequiresGuest
	// @RequiresPermissions 
	//@RequiresRoles(value= {"admin"})
	 
	@RequiresUser
	public String test13() {
		
		//  ������ͼ������Ҳ�����õ�����·��
		// 404��SsmDevProject/html//html/shiroannotation.html
		// return "/html/shiroannotation";  
		
		 return "shiroannotation";  
	}
}
