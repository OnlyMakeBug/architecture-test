package xhm.struts.datawrap;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/*3.ʵ��ģ�������ӿڣ�ModelDriven<T>��
 * T����Ҫ���ص�pojo���ͣ��ӿ���getModel()�����������Դ�ŵ�pojoʵ����
 * ���pojo������Ҫ�ֶ�ʵ������
 * 
 * Ҫ��pojo���е�������setter��������ʵ��ģ�������ӿڡ�
 * 
 * �ŵ㣺pojo������Ҫsetter,getter������ֻ��Ҫʵ�ֽӿڼ��ɡ�
 * 
 * ȱ�㣺ֻ�ܽ�������װ��һ��pojo�����С�
 * 
 * ����������õķ�ʽ - ��Ϊ���������£�һ��pojo������ܹ��洢���еĲ�����
 */ 
public class Wrapper3 extends ActionSupport implements ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User user = new User();

	@Override
	public String execute() throws Exception {
		System.out.println(user.name);
		System.out.println(user.sex);
		System.out.println(user.age);
		return "wrapper3";
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
}
