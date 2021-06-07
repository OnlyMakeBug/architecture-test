package test.java.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


import org.junit.Test;

/* *
 * @time��2020��2��13�� ����8:39:20
 * @Author��zhoujian
 * @QQ��2025513
 * @description��
 */

/**
 * ѧ����Ϣʵ����
 * */
public class StudentInfo {
	
	// ö������
	private static  Sex sex = Sex.male;
	
	// ��̬������������������
	static public int DEFAULT_ADULT_AGE = 18;
	
	// ��̬˽�г�����Ĭ��ѧ���Ա���
	private static final String DEFAULT_SEX = "��";
	
	// ˽�����ԣ�ѧ������
	private String sName;
	
	// �������ԣ�ѧ�����
	public int sAge;
	
	// Test class should have exactly one public zero-argument constructor
	public StudentInfo() {
		// TODO Auto-generated constructor stub
	}
	
	// Test class can only have one construtor
	/*public StudentInfo(String sName,int sAge) {
		// TODO Auto-generated constructor stub
		this.sName = sName;
		this.sAge = sAge;
	}*/
	
	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}



	/**
	 * ��ȡ����
	 * 
	 * ��ȡ���Եļ���������
	 * 1. Field java.lang.Class.getDeclaredField(String name) 
	 * 2. Field[] java.lang.Class.getDeclaredFields() 
	 * 
	 * 3. Field java.lang.Class.getFields(String name) 
	 * 4. Field[] java.lang.Class.getFields()
	 * 
	 * getDeclaredField/getDeclaredFields  VS  getFields/getFields
	 * ǰ��ֻ�ܷ��ر��ඨ������ԣ��������κ����η������ԣ����߿��Ի�ȡ����/�ӿ��е����ԣ���Ҫע����Ǻ���ֻ�ܻ�ȡ����
	 * public���ε����ԡ�
	 * 
	 * 
	 * */
	@Test
	public void test1() {
		
		// 1.��ȡClass
		Class<?> cls = StudentInfo.class;
		
		
		// 2.��ȡ���ԣ�����ֻ��ͨ��ӳ���ȡ�������ԵĶ���Field�������ǻ�ȡ����ֵ��
		// ����Ҳ���ָ�������Ծͻ��׳�NoSuchFieldException�쳣��
		try {
			// 2.1.��ȡ��̬��������
			Field field1 = cls.getDeclaredField("DEFAULT_ADULT_AGE");
			
			// 2.2.��ȡ��̬˽�г���
			Field field2 = cls.getDeclaredField("DEFAULT_SEX");
			// 2.3.��ȡ˽�����ԣ�ѧ������
			Field field3 = cls.getDeclaredField("sName");
			// 2.3.��ȡ�������ԣ�ѧ������
			Field field4 = cls.getDeclaredField("sAge");
			
			// 3.getDeclaredFields()��ʹ��
			for(Field field: cls.getDeclaredFields()) {
				// 4.̽��Field���÷���
				// 4.1.��ȡ������
				String fieldName = field.getName();
				System.out.println("Name: " + fieldName);
				
				// 4.2.��ȡ��ʾ���η���ϵ�����,��ͬ������в�ͬ��ֵ
				int modifiersInt = field.getModifiers();
				// ���η�int -> ���η��ַ���
				String modifiersString = Modifier.toString(modifiersInt);
				System.out.println("Modifiers: " + modifiersString);
				
				// 4.3.Accessiable
				// ����ֵΪtrue��ʾ�������Ӧ����ʹ��ʱ����Java���Է��ʼ�顣
				// ����ֵΪfalse��ʾ�������Ӧǿ��ִ��Java���Է��ʼ�顣
				// ��������Կ�������private/public���ԣ��÷���������false
				System.out.println("Accessable��" + field.isAccessible());
				
				// 4.4.��������Ƿ�Ϊö�ٳ�������Ҫע�����Field������ע��Class�ģ����򷵻�false
				System.out.println("isEnumConstant��" + field.isEnumConstant());
				
				// 4.5.�ϳ����� - ���ڲ���ͼ����ⲿ���໥�����йأ��������и�����������ԣ��������ø���
				// ������Ҳ��Ϊ������ӷ���������������������ԡ�
				// class name:com.synthetic.MainClass$SubClass-access$100:true
				// class name:com.synthetic.MainClass$SubClass-access$200:true
				
				//�ο���http://blog.sina.com.cn/s/blog_1534f339a0102y88n.html
				System.out.println("isSynthetic��" + field.isSynthetic());
				
				// 4.7.��ӡ
				/*toGenericString��Constructor,Method,Field��������ר�еķ�����
				��Constructor�࣬�������Ƕ�Ӧ�Ĺ��캯����ϸ�ڣ��������η������캯�����Ͳ����б�ȡ�
				������������࣬��String��List�޷����á�*/
				System.out.println("toGenericString��" + field.toGenericString());
				// ���������һ���ġ�
				System.out.println("toString��" + field.toString());
				
				System.out.println("-----");
			} 
			
			/* output:
			Name: sex
			Modifiers: private static
			Accessable��false
			isEnumConstant��false
			isSynthetic��false
			toGenericString��private static test.java.reflection.Sex test.java.reflection.StudentInfo.sex
			toString��private static test.java.reflection.Sex test.java.reflection.StudentInfo.sex
			-----
			Name: DEFAULT_ADULT_AGE
			Modifiers: public static
			Accessable��false
			isEnumConstant��false
			isSynthetic��false
			toGenericString��public static int test.java.reflection.StudentInfo.DEFAULT_ADULT_AGE
			toString��public static int test.java.reflection.StudentInfo.DEFAULT_ADULT_AGE
			-----
			Name: DEFAULT_SEX
			Modifiers: private static final
			Accessable��false
			isEnumConstant��false
			isSynthetic��false
			toGenericString��private static final java.lang.String test.java.reflection.StudentInfo.DEFAULT_SEX
			toString��private static final java.lang.String test.java.reflection.StudentInfo.DEFAULT_SEX
			-----
			Name: sName
			Modifiers: private
			Accessable��false
			isEnumConstant��false
			isSynthetic��false
			toGenericString��private java.lang.String test.java.reflection.StudentInfo.sName
			toString��private java.lang.String test.java.reflection.StudentInfo.sName
			-----
			Name: sAge
			Modifiers: public
			Accessable��false
			isEnumConstant��false
			isSynthetic��false
			toGenericString��public int test.java.reflection.StudentInfo.sAge
			toString��public int test.java.reflection.StudentInfo.sAge
			-----
			-----*/
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
