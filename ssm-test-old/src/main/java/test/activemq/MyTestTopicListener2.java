package test.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ���ԣ�queue��Ϣ���м�����
 * */
public class MyTestTopicListener2 implements MessageListener{

	public void onMessage(Message message) {
		System.out.println("mq topic listener2");
		try {
			System.out.println(((TextMessage)message).getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
