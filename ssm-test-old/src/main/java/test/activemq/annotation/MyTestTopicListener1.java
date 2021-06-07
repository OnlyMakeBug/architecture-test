package test.activemq.annotation;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * ע����ʽʵ��MQ����,ע����ʽ����Ҫʵ��MessageListener�ӿ�
 * ���Զ���Ϊpojo�������Ҫ����Ĭ���������м�����Ĭ��topic��Ϣ����������extends MessageListenerAdapter
 * */
@Component("myTestTopicListener1")
public class MyTestTopicListener1 extends MessageListenerAdapter{
	
	@Override
	public void setDefaultResponseTopicName(String destinationName) {
		// TODO Auto-generated method stub
		super.setDefaultResponseTopicName("135.topic");
	}

	public MyTestTopicListener1() {
		// TODO Auto-generated constructor stub
		System.out.println("MyTestTopicListener created");
	}
	
	@PostConstruct
	public void test1() {
		System.out.println("after created");
	}

	/**
	 * <ul>
	 * <li>1.destination����Ϣ������,@JmsListener����ָ��������</li>
	 * <li>2.containerFactory�����������Ҳ��ȥ����ΪjmsListenerContainerFactory��bean����Ҳ���������</li>
	 * <li>3.������������topic/quque���ͣ���Ҫ��containerFactory bean pubSubDomain���ԣ�true����topic����</li>
	 * <li>4.concurrency���������ƣ�10���ʾ����10,��ʱ����Ϊ1��5-10���ʾ��Χ��ע�⣬�ײ���������֧��Ҳ���ܲ�֧���������ԡ����磬�������޷����ţ������������ֻʹ�����ޡ�</li>
	 * 
	 * </ul>
	 * */
	@JmsListener(concurrency="1",destination="135.topic",containerFactory="jmsListenerContainerTopicFactory")
	public void onMessage(Message message) {
		
		System.out.println("mq topic listener by annotation");
		try {
			System.out.println(((TextMessage)message).getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
