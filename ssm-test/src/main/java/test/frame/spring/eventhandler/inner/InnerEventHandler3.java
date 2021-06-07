package test.frame.spring.eventhandler.inner;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/*
 * Spring�����¼�����: refreshed
 * ע�⣺ ֻ��ʵ��һ��ApplicationEvent�ӿڣ�
 * ��ʵ����ApplicationListener<ContextStartedEvent>�Ͳ���ʵ��ApplicationListener<ContextStoppedEventedEvent>
 * 
 * 
 * �����¼��� started,refreshed,stoped,closed��RquestHandledEvent
 * */

@Component
public class InnerEventHandler3 implements ApplicationListener<ContextRefreshedEvent>{


	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("Spring context refreshed");
	}
}







