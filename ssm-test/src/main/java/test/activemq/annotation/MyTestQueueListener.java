package test.activemq.annotation;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * ע����ʽʵ��MQ����,ע����ʽ����Ҫʵ��MessageListener�ӿ�
 * �򵥵�POJO����ֻ�ܽ���queue-p2p��Ϣ
 * */
@Component("myTestQueueListener1")
@EnableJms
public class MyTestQueueListener {
	
	public MyTestQueueListener() {
		// TODO Auto-generated constructor stub
		System.out.println("MyTestQueueListener created");
	}
	
	@PostConstruct
	public void test1() {
		System.out.println("after created");
		
	}

	/**
	 * <ul>
	 * <li>1.destination����Ϣ������</li>
	 * <li>2.containerFactory�����������Ҳ��ȥ����ΪjmsListenerContainerFactory��bean����Ҳ���������</li>
	 * <li>3.������������topic/quque���ͣ���Ҫ��containerFactory bean pubSubDomain���ԣ�true����topic����</li>
	 * <li>4.concurrency���������ƣ�10���ʾ����10,��ʱ����Ϊ1��5-10���ʾ��Χ��ע�⣬�ײ���������֧��Ҳ���ܲ�֧���������ԡ����磬�������޷����ţ������������ֻʹ�����ޡ�</li>
	 * 
	 * </ul>
	 * */
	@JmsListener(concurrency="1",destination="135.queue",containerFactory="jmsListenerContainerFactory")
	public void onMessage(Message message) {
		
		System.out.println("mq queue listener by annotation");
		try {
			System.out.println(((TextMessage)message).getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
