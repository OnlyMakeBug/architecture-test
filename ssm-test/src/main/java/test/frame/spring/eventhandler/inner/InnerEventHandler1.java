package test.frame.spring.eventhandler.inner;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/*
 * Spring�����¼�����: started
 * ע�⣺ ֻ��ʵ��һ��ApplicationEvent�ӿڣ�
 * ��ʵ����ApplicationListener<ContextStartedEvent>�Ͳ���ʵ��ApplicationListener<ContextStoppedEventedEvent>
 * 
 * �����¼��� started,refreshed,stopped,closed��RequestHandledEvent
 * */

//  �¼���������Ҫ��ʵ����
@Component
public class InnerEventHandler1 implements ApplicationListener<ContextStartedEvent>{

	public void onApplicationEvent(ContextStartedEvent event) {
		System.out.println("Spring context started");
	}
}