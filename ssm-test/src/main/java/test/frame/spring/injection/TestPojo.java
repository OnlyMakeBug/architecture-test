package test.frame.spring.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * @time  2019��12��31�� ����3:29:57
 * @author  zhoujian
 * @corporation  luku
 * @description   ���������������Ե�ע��
 * @finished  true
 * @finishTime  2020��1��18��20:51:34
 * @version  1.0
 */

@Component
public class TestPojo {
	
	/* 1. ��������ע��
	 * ����Ҫ������ȱ����ֻ�ܰ�������ע��
	 * ע�⣺Ϊ��֧�ֶ�̬�������붨��Ϊ�ӿ����ͣ��������Ǿ����ʵ���ࡣ
	 * 
	 * ���¶��巽�����Ǵ���ģ�
	 * TestServiceImpl testServiceImpl;
	 * */
	@Autowired
	public TestService testServiceImpl;
	
	/* 2.@Resource
	 * ������ṩname���ԣ�����classע��
	 * ����ṩname���ԣ�����bean nameע�룬������classע�롣
	 * ���bean name �����ڣ��򱨴�
	 * ȱ����ֻ�ܰ���beanname/class���а�
	 * */ 
	
	//@Resource(name="testServiceImpl")
	public TestServiceImpl testServiceImpl2;
	
	/* 3.�������ע���Ϊ�˽������ͬʱָ��bean name��class��ȱ��
	 * @Qualifier- ��������û�����壬��Ҫ��@Autowiredһ��ʹ��
	 * 
	 * ���@Autowired��@Qualifierͬ�Դ��ڣ���Ҫ��beanͬʱ�������ͺ�nameҪ��
	 * ���û��ָ����bean����ᱨ��
	 * */
	
	//@Autowired
	//@Qualifier("testServiceImpl")
	public TestServiceImpl testServiceImpl3;
	
	// ��������û�����壬��Ȼbean name���ڣ�testServiceImpl4Ϊnull
	// ���ǲ��ᱨ��
	//@Qualifier("testServiceImpl")
	//public TestServiceImpl testServiceImpl4;
}