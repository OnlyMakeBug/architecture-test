package xhm.struts.datawrap;

import com.opensymphony.xwork2.ActionSupport;
/*2.����ע�룺pojo��װ
 * Http��������ᱣ����������β�ո�
 * 
 * Ҫ��Action �� pojo ��װ������Ҫ�ṩsetter ��  getter��
 * ������ṩgetter����Ȼ���ᱨ��������Ϊû��getter����,��Ϊÿһ����������һ��pojo��
 * ��pojo�е������Խ��и�ֵ���ٵ���setter��ֵ��Action,���ڶ�ε���setter�����յ�Pojo������
 * ���һ�δ�����pojo�������Ĳ�����ʧ��
 *
 * �ŵ㣺�����Ҫ�����Խ����Է�װ�����pojo�����С�
 * */

public class Wrapper2 extends ActionSupport {

	private static final long serialVersionUID = 1L;
	StudentInfo sinfo;
	
	public StudentInfo getSinfo() {
		return sinfo;
	}

	public void setSinfo(StudentInfo sinfo) {
		this.sinfo = sinfo;
	}

	User user;

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		System.out.println(user.name);
		System.out.println(user.sex);
		System.out.println(user.age);
		System.out.println(sinfo.time);
		System.out.println(sinfo.grade);
		System.out.println(sinfo.price);
		return "wrapper2";
	}

	public User getUser() {
		return user;
	}
}
