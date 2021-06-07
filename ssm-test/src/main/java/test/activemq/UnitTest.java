package test.activemq;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:resource/applicationContext-activemq.xml")

/**
 * ��ʵ�ֽӿ�
 * 
 * */
public class UnitTest {

	@Resource(name="jmsQueueTemplate")
	JmsTemplate jmsQueueTemplate;

	/* ��������topic������Ϣ */
	@Resource(name="jmsTopicTemplate")
	JmsTemplate jmsTopicTemplate;

	/* ��������queue������Ϣ */
	@Autowired
	ActiveMQQueue queueDestination;

	@Autowired
	ActiveMQTopic topicDestination;

	@Test
	public void test1() {
		// 1.send(..)
		// send() - ��������Ϣ������̷���
		/*jmsQueueTemplate.send(queueDestination, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {

				return session.createTextMessage("message");
			}
		});*/

		/*for (int i = 0; i <20; i++) {
		    //  ��Ϣֻ�ܱ�����һ��
			jmsQueueTemplate.send("135.queue", new MessageCreator() {

				public Message createMessage(Session session) throws JMSException {

					return session.createTextMessage("message");
				}
			});
		}*/

		//����topic��Ϣ��ÿ����Ϣ�����Ա��������
		/*jmsTopicTemplate.send(topicDestination, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub

				return session.createTextMessage("message");
			}
		});*/
		
		System.out.println("������Ϣ...");
		jmsTopicTemplate.send("135.topic",new MessageCreator() {
			
			public Message createMessage(Session arg0) throws JMSException {
				// TODO Auto-generated method stub
				return arg0.createTextMessage("message");
			}
		});
		
		/*jmsTopicTemplate.send("135.topic",new MessageCreator() {
			
			public Message createMessage(Session arg0) throws JMSException {
				// TODO Auto-generated method stub
				return arg0.createTextMessage("message");
			}
		});*/
	}
}
