package test.frame.spring.springtask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @time  2019��12��26�� ����4:45:08
 * @author  zhoujian
 * @corporation  luku
 * @description  ������Ȳ��ԣ�@Scheduledע����ϸ˵��
 */
@Service
public class Task1 {
	
	/**
	 * �����������ע��ʽʵ�֣�@Schedule
	 * 
	 * success����
	 * 
	 * Cron expression must consist of 6 fields (found 7 in "0 27 17 26 12 ? 2019")
	 * Ҳ����˵��֧��year
	 * */
	@Scheduled(cron="0 27 17 26 12 ?")
	public void startWork() {
		int i = 0;
		while(true) {
			System.out.println(i);
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// @Scheduled(cron="* */1 * * * ?")  -  ����д������ʵ��ÿ����ִ��һ�Σ���ȷд�����£�
	
	//  */1���ͣ���Ҳ����ʹ��*/y������ͬ��0/y,������˵������ȵ�0�ֲŻ�ִ�У����ǿ����κη���ֵ�����ԡ���������д����Ҫָ�������ÿ����ִ�С�
	//@Scheduled(cron="0 */1 * * * ?")
	
	/**
	 * <pre>
	 * cron���ʽ��ʹ�ã��������Ƿ���̺߳� '* /1',��ʹ��
	 * <ul>
	 * <li>@Scheduledע��������ǵ��̵߳ġ�</li>
	 * 
	 * <li> * /1���ͣ���Ҳ����ʹ��* /y������ͬ��0 /y,������˵������ȵ�0�ֲŻ�ִ�У����ǿ����κη���ֵ�����ԡ���������д����Ҫָ�������ִ�С�</li>
	 * </ul>
	 * <pre>
	 **/
	@Scheduled(cron="* * * * * ?")
	public void startWork1() {
		System.out.println("Work1");
		try {
			//  ע�⣺���cron���ʽָ��ÿ��ִ�У���ֻ�е���һ��������ɣ����ܼ���ִ����һ�η�����
			//  ��Ϊ��ִ�з����Ĳ��Ƕ��̡߳�
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* 
	 * �����ָ��cron��fixed*���ԣ�������ִ��һ��
	 * 
	 * fixedDelay���ϸ�����ִ����ϣ�������ٴ�ִ��
	 *
	 * 'cron', fixedDelay,'fixedDelay(String)', or 'fixedRate(String)'...���ԣ�ֻ�ܳ���һ�������ʹ�� fixedDelay���򷽷�������ִ��һ�Ρ�
	  *@Scheduled(fixedDelay=10000)
	 * 
	 */
	
	/**
	 * ʵ�̶ֹ����ִ�У�����д������ʵ��spring��ʼ������ʱ10����ִ����������ִ�����5����ٴ�ִ������
	 * 
	 * <pre>
	 * <strong>NOTE:</strong>
	 * fixedDelay/fixedDelayString����һ��������ɺ���һ������ʼ�ļ�������������ַ���
	 * 
	 * fixedRate/fixedRateString����һ������ʼ����һ������ʼ�ļ�������������ַ���
	 * 
	 * initialDelay/ initialDelayString������ʼǰ����ʼ��ʱ��
	 * 
	 * zone��ʱ��������һ��java.util.TimeZone#ID��cron���ʽ ����ڸ�ʱ��������Ĭ����һ�����ַ�������ȡ���������ڵص�ʱ������������һ��ʹ�õ�ʱ��Asia/Shanghai�����ֶ�����һ�����ա�
	 * </pre>
	 * */
	@Scheduled(fixedDelayString=("5000"),initialDelay=10000)
	public void startWork2() {
		System.out.println("entering Work2");
	}
	
}