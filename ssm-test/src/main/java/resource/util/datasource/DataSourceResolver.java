package resource.util.datasource;

import org.aspectj.lang.annotation.Aspect;

/**
 * @time 2019��12��25�� ����4:32:57
 * @author zj
 * @corporation luku
 * @description:
 */
@Aspect
public class DataSourceResolver {
	public static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
	/**
	 * ���õ�ǰ�̶߳�Ӧ��DataSource��
	 * */
	public static void setDataSource(String dataSourceName) {
		threadLocal.set(dataSourceName);
	}
	
	/**
	 * ��ȡ��ǰ�߳�DataSource��
	 * */
	public static String getDataSource() {
		return threadLocal.get();
	}
}



