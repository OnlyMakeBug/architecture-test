package test.frame.spring.injection.xml.bean;

/**
 * @CreateTime��2020��2��9�� ����5:24:52
 * @Author��zhoujian
 * @QQ��2025513
 * @FileDescription��
 * @IsFinished��false
 */

public class StudentFactoryBean {
	
	// ��������,����Ϊprivate
	private Student createStudent() {
		return new Student("student4",21);
	}
}
