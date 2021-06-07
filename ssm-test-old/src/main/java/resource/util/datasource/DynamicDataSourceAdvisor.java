package resource.util.datasource;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @time 2019��12��25�� ����4:45:20
 * @author Administrator
 * @corporation luku
 * @description: ��̬����Դ֪ͨ��
 */
public class DynamicDataSourceAdvisor {
	
	/**
	 * ���ݽӿ����������Դ
	 * */
	public void before(JoinPoint joinPoint) {
		// �е���ʵ������
		Object target = joinPoint.getTarget();
		
		// ��ȡ������: ��Ϊ@DataSource�Ƿ���ע��
		String methodName = joinPoint.getSignature().getName();
		// ��ȡ��������
		Class<?>[] paramTypes = ((MethodSignature)joinPoint.getSignature())
				.getMethod().getParameterTypes();
		
		// ��ȡ����ʵ�ֵĽӿ�,Ĭ�ϲ��ҵ�һ���ӿڵķ���ע�⣬��ȷ��serviceʵ����ֻʵ��һ���ӿ�
		Class<?>[] interfaces = target.getClass().getInterfaces();
		
		// ��ȡָ���ķ���
		try {
			//�Ҳ�������null
			Method method = interfaces[0].getMethod(methodName, paramTypes);
			
			/*method.isAnnotationPresent(annotationClass);
			 * 
			 * �����Ԫ���ϴ���ָ�����͵�ע�ͣ��򷵻�true������Ϊfalse���˷�����Ҫ���ڷ���ط��ʱ��ע�͡�
	         *�÷������ص���ֵ����:getAnnotation(annotationClass) != null
	         *Ĭ�Ϸ���������ָ��Ϊ����Ĵ��롣
	         * */
			
			//��ȡ@DataSourceע��
			
			//���зǿ��жϣ���Ϊ���ʵ�ֶ���ӿڣ�����0�Žӿ�û��ָ���ķ���
			if(method != null) {
				
				DataSource dataSource = method.getAnnotation(DataSource.class);
				if(dataSource != null) {
					System.out.println("�߳�"+ Thread.currentThread().getName()+ "(" +Thread.currentThread().getId()+")ѡ�����ݿ�:" +dataSource.value());
					DataSourceResolver.setDataSource(dataSource.value());
				}
			}
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

}



