package test;

/* *
 * @time��2020��2��13�� ����5:49:31
 * @Author��zhoujian
 * @QQ��2025513
 * @description��
 */

public class FindUppercase {
	
	
	public static boolean findUpperChar(String s,char findChar) {
		//// �������Сд�ַ��Զ�ת���ɴ�С
		if(findChar>=97)
			findChar -=32;
		
		boolean found = false;

		for (char c : s.toCharArray()) {
			if (c == findChar) {
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	public static void main(String[] args) {
		byte b = 'a';
		System.out.println(b);
		System.out.println((int)'A');
		//true
		System.out.println(findUpperChar("jsdhlD", 'd'));
	}
}
