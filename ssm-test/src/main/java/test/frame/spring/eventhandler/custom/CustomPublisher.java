package test.frame.spring.eventhandler.custom;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/* �Զ����¼�������
 * 
 * Spring�����¼��ࣺ��Ҫʵ��ApplicationEventPublishAware�ӿڣ����淢����ʵ������ʹ�ø�ʵ�������¼���
 * 
 * ������ʱ���ȡ�Զ����¼������࣬�����Լ��ķ���������
 *  
 * ��Ҫʹ��ɨ��ע��
 *  */
@Component
public class CustomPublisher implements ApplicationEventPublisherAware {
	
	ApplicationEventPublisher publisher;
	
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		
		this.publisher =applicationEventPublisher;
	}
	
	/*
	 * ��Ա���������Ǽ̳����ķ���
	 * */
	public void publish() {
		
		CustomEvent cEvent = new CustomEvent(this);
		
		//publishEvent(Object)
		publisher.publishEvent(cEvent);
		
	}
}
