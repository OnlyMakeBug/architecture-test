package com.zj.util.security.base64;
/*
 * @Time��2020��1��26�� ����5:16:38
 * @Author��zhoujian
 * @QQ��2025513
 * @Description��
 * @IsFinished��false
 */

/**
 * 64λ
 * ����ģ���ȫ�Ƚϵͣ�������Ҳ������Ϊ�������򵥵ļ����㷨���ڼ���Ҫ����������
 * 
 * */
public class Base64Utils {

	private Base64Utils() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * �����ַ���
	 * 
	 * */
	public static String encodeString(String string) {
		return org.springframework.util.Base64Utils.encodeToString(string.getBytes());
	}
	
	public static byte[] encode(byte[] bytes) {
		return org.springframework.util.Base64Utils.encode(bytes);
	}
	
	public static byte[] decode(byte[] bytes) {
		return org.springframework.util.Base64Utils.decode(bytes);
	}
	
	public static void main(String[] args) {
		String encode = encodeString("�ܽ�");
		System.out.println(encode);
		
		String decode =new String(decode(encode.getBytes())) ;
		System.out.println(decode);
		
		byte[] encodeBytes = encode("�ܽ�".getBytes());
		System.out.println(new String(decode(encodeBytes)));
	}

}
