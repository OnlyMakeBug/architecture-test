package test.java.reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

/* *
 * @time��2020��2��12�� ����5:07:50
 * @Author��zhoujian
 * @QQ��2025513
 * @description���Զ���ע�⴦����
 */

/**
 * ����ͨ������ʵ����ȡ���ࡢ���������ԡ������������ֲ���������ע�⡣
 * */
public class CustomApt {
	
	public static void main(String[] args) {
		Student student = new Student();
	
		Class<? extends Student> cls = student.getClass();
		// 1.��ȡ���ϵ�AlertMessageע��
		// ���û�У�����null
		AlertMessage classAnnotation = (AlertMessage) cls.getAnnotation(AlertMessage.class);
		if(classAnnotation != null) {
			for (int i = 0; i < classAnnotation.alertCount(); i++) {
				System.out.println("[������֪ͨ ]: " + classAnnotation.value() );
			}
		}
		
		// 2.��ȡpublic����username���Ե�ע��
		try {
			Field usernameField = cls.getDeclaredField("username");
			AlertMessage fieldAnnotation = (AlertMessage) usernameField.getAnnotation(AlertMessage.class);
			if(classAnnotation != null) {
				for (int i = 0; i < fieldAnnotation.alertCount(); i++) {
					System.out.println("[������֪ͨ ]: " + fieldAnnotation.value() );
				}
			}
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 3.��ȡprivate����intע��
		
		try {
			// getFieldֻ�ܻ�ȡpublic����
			Field ageField = cls.getField("age");
			AlertMessage ageFieldAlertMessage = ageField.getAnnotation(AlertMessage.class);
			for(int i=0;i<ageFieldAlertMessage.alertCount();i++) {
				System.out.println(ageFieldAlertMessage.value());
			}
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 4.doHomeWork����ע���ȡ
		try {
			Method method4 = cls.getMethod("doHomeWork");
			System.out.println(method4.getAnnotation(AlertMessage.class).value());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ��ȡ˽�з���
		// getMethodֻ�ܻ�ȡ���з���
		try {
			// getDeclaredMethod���Ի�ȡ˽�з���
			Method method4 = cls.getDeclaredMethod("getInternetCafe");
			System.out.println(method4.getAnnotation(AlertMessage.class).value());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// getMethod���Ի�ȡ�����е�public����
			Method method4 = cls.getMethod("toString");
			//System.out.println(method4.getAnnotation(AlertMessage.class).value());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// getMethod���Ի�ȡ�����е�public����
			Method method4 = cls.getMethod("toString");
			//System.out.println(method4.getAnnotation(AlertMessage.class).value());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			// getMethod���Ի�ȡ�����е�public����
			// getDeclaredMethod - ֻ�ܻ�ȡ���ж���ķ���(����ʵ�ֽӿڵķ���)���������κ����η������ܻ�ȡ�����еķ���
			// java.lang.NoSuchMethodException: test.java.reflection.Student.toString()
			Method method5 = cls.getDeclaredMethod("toString");
			//System.out.println(method4.getAnnotation(AlertMessage.class).value());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	@Test
	public void test1() {
		
	}

}

interface HomeWork{
	public void doHomeWork();
}

@AlertMessage(value="��Ϊѧ��,�����Ҫ����У�ͣ�������Ҫ��������˵����",alertCount=3)
class Student implements HomeWork{
	
	@AlertMessage(value="��״����ͬѧ,���Ѿ������������ˣ���Ҫ�������ǿ�ƴ�ʩ~")
	private String username;
	
	@AlertMessage("3��Ĺ�״����")
	public String age;
	
	/** ѧ��ȥ���ɵķ��� */
	@AlertMessage(value="��Ϊѧ��,�����Ҫ����У�ͣ�����",alertCount=3)
	private void getInternetCafe() {
		
		@AlertMessage(value="��,10��ǮһСʱ?��θ��㷣��1000",alertCount=1)
		int pay;
		
	}

	@AlertMessage("����ҵ")
	public void doHomeWork() {
		// TODO Auto-generated method stub
		
	}
}

// ��ע��������ʹ�����ĵط��ṩ������Ϣ
@Retention(value=RetentionPolicy.RUNTIME)
@interface AlertMessage{
	// ������Ϣ
	public String value();
	
	// �������,Ĭ��һ��
	public int alertCount() default 1;
}




