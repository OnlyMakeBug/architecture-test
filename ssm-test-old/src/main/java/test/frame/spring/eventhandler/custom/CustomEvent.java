package test.frame.spring.eventhandler.custom;

import org.springframework.context.ApplicationEvent;

/*
 * �Զ����¼���:��Ҫ�̳���ApplicationEvent
 * ע����һ���࣬���ǽӿ�
 * */
public class CustomEvent extends ApplicationEvent{
	/**
	 */
	private static final long serialVersionUID = 1L;

	/* ��Ϊ�¼�Դ */
	public CustomEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
}
