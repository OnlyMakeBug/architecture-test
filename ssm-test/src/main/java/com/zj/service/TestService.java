package com.zj.service;

import resource.util.datasource.DataSource;

/**
 * @time 2019��12��25�� ����5:18:46
 * @author Administrator
 * @corporation luku
 * @description: ���Զ�̬����Դ������ѯ(slow sql)
 */


public interface TestService {
	
	/**
	 * 
	 * <pre>
	 * Note:
	 * @DataSourceע��ʹ��Ҫ��
	 * 1.��û�д�ע��ʱ��ʹ��defaultTargetDataSource
	 * 2.���д�ע��ʱ�������ע������Դ����targetDataSources�У���ʹ�ô�����Դ����������ڣ���ʹ��defaultTargetDataSource��
	 * </pre>
	 * 
	 * */
	@DataSource("master")
	public void test1();
	
	@DataSource("master")
	public int insertARow(int value);
}



 