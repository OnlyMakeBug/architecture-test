package xhm.struts.datawrap;

import com.opensymphony.xwork2.ActionSupport;

/*1.����ע�룺������
 * Ҫ��Action������Ӧ������setter������
 * */
public class Wrapper1 extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String name;
	
	//struts2 �Զ�����ַ�������ֵ��ת��
	private int age;
	
	private String sex;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String execute() throws Exception {
		System.out.println(name);
		System.out.println(sex);
		System.out.println(age);
		return "wrapper1";
	}
}
