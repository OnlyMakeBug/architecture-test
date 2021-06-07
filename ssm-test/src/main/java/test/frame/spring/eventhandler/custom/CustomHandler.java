package test.frame.spring.eventhandler.custom;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/*
 * �Զ����¼���������ʵ��ApplicationListener<T> �ӿ�
 * ��Ҫʹ��ɨ��ע��
 * */
@Component
public class CustomHandler implements ApplicationListener<CustomEvent> {

	public void onApplicationEvent(CustomEvent event) {
		// TODO Auto-generated method stub
		System.out.println("�Զ��崦����");

		System.out.println("�¼�Դ�Ƿ���CustomPublisher: " + (event.getSource() instanceof CustomPublisher));
	}

}
