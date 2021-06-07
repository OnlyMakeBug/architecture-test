package test.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ���ԣ�queue��Ϣ���м�����
 * */
public class MyTestQueueListener implements MessageListener{

	public MyTestQueueListener() {
		// TODO Auto-generated constructor stub
		System.err.println(1);
	}
	
	public void onMessage(Message message) {
		
		System.out.println("mq queue listener");
		try {
			System.out.println(((TextMessage)message).getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
