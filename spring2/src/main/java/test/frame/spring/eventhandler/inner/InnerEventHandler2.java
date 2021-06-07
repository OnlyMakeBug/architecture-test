package test.frame.spring.eventhandler.inner;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;


/*
 * Spring�����¼�����: stoped
 * ע�⣺ ֻ��ʵ��һ��ApplicationEvent�ӿڣ�
 * ��ʵ����ApplicationListener<ContextStartedEvent>�Ͳ���ʵ��ApplicationListener<ContextStoppedEventedEvent>
 * 
 * 
 * �����¼��� started,refreshed,stoped,closed��RquestHandledEvent
 * */

@Component
public class InnerEventHandler2 implements ApplicationListener<ContextStoppedEvent>{

	public void onApplicationEvent(ContextStoppedEvent event) {
		System.out.println("Spring context stoped");
	}
}








