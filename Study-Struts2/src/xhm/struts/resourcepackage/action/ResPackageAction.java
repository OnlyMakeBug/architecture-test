package xhm.struts.resourcepackage.action;

import com.opensymphony.xwork2.ActionSupport;

//��Դ������action
public class ResPackageAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String
	execute() throws Exception {
		
		//��ȡ��ʽ1��ActionSupport getText()
		System.out.println(getText("username"));
		System.out.println(getText("password"));
		
		/*��ȡָ���������ԣ��ᰴ����Դ������˳����в��ң����û�У�����������
		 * ���������в���
		 * */
		System.out.println(getText("age"));
		
		System.out.println(getText("school"));
		
		System.out.println(getText("xxx"));
		
		//��ʵ֤�����ڲ��Ҹ�����ʱ��Ҳ�����ActionName.properties������������package.properties
		System.out.println(getText("parent"));
		
		return SUCCESS;
	}

}
