package test.frame;

import java.awt.Component;
import javax.swing.JButton;

/**
  * @time��2019��12��26�� ����9:21:35
  * @author��zhoujian
  * @corporation��luke
  * @description�������ࡢ��������
  * @finished��false
  * @finishTime��
  *
  */

/**
 * 1.����������������κα�ʶ������ʾ
 * ����һ������һ����T������һ����K,V
 * ע�⣺��������ֻ�ܽ����������ͣ������ܻ����������͡�
 * */
// ��Щ�౻��Ϊ���������������������͡�
public class GenericityTest<XD>{
	/**
	 * ���������������Ϊ��������
	 * 
	 * */
	public void printT(XD t) {
		//HashMap<K, V>;
	}
	
	
	/**
	 * 2.���ͷ������ԣ�
	 * <? extends T> - E��T������
	 * <? super T> - <E super JComponent> - ��û�в��Գɹ�
	 * 
	 * ע��<? extends T> ��д����
	 * ע��E���Ե���T����������ڸ���Ҳ���ԡ�
	 * */
	public static <E extends Component> void printArray(E[] array) {
		
	}
	
	public static void main(String[] args) {
		
		//OK
		JButton[] buttons = new JButton[3];
		buttons[0] =new JButton();
		buttons[1] =new JButton();
		buttons[2] =new JButton();
		printArray(buttons);
		
		//NO: Bound mismatch
		/*String[] strings = new String[3];
		strings[0]= "1";
		strings[1] ="2";
		strings[2] ="3";
		printArray(strings);*/
	}
	
	/**
	 * 3.����ͨ���?
	 * 
	 * */
	/*public static void getData(List<?> data) {
	      System.out.println("data :" + data.get(0));
	   }*/
}