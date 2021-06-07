package com.zj.util.security.md;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.zj.util.NullByteArrayException;
import com.zj.util.NullStringException;

/*
 * @Time��2020��1��26��15:45:52
 * @Author��zhoujian
 * @QQ��2025513
 * @Description��
 * @IsFinished��false
 */

/**
 * MD5����
 * 
 * ժҪ32λ
 * 
 * ժҪ�㷨��Ҫ��ΪMD��SHA��Hmac�㷨��ժҪ�㷨��ʵ������Ч�����������Եģ�����������ĳЩ�ļ�ʱ��
 * ����MD5��SHA1ֵ�ṩ����Ч�����ص��ļ��Ƿ��������������ڸ�������������Ψһ��ժҪֵ���޷�����ժҪֵ֪��ԭ���ݣ����ڲ������
 */
public class MD5Utils {

	private MD5Utils() {
	}

	public static String encode(byte[] originBytes, boolean returnUppercase) 
	throws NullByteArrayException{
		if(originBytes ==null)
			throw new NullByteArrayException();
		
		String resultString = null;
		StringBuilder stringBuilder = new StringBuilder();
		MessageDigest md5 = null;

		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] encodedBytes = md5.digest(originBytes);

			for (int i = 0; i < encodedBytes.length; i++) {
				// OK
				// long val = (100l) & 0xff;

				// �����ͳһ���ͣ��Ҳ�����Ϊint/long

				// ��ΪByte,Shortû��ת��ʮ�����Ƶķ���������val����Ϊint����
				// byte val = (byte) ((encodedBytes[i]) & 0xff);
				int val = encodedBytes[i] & 0xff;
				if (val < 16)
					stringBuilder.append("0");
				// ����Ox/OXǰ׺
				// ab
				// be
				// 56
				// e0
				// System.out.println(Integer.toHexString(val));

				stringBuilder.append(Integer.toHexString(val));
			}
			resultString = returnUppercase ? stringBuilder.toString().toUpperCase() : stringBuilder.toString();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		
		return resultString;
	}

	// intתbyte��ֱ��ȡ��8λ
	public static String encode(String originString, boolean returnUppercase) throws NullStringException,NullByteArrayException {
		if(originString ==null)
			throw new NullStringException();
		
		byte[] originBytes = null;
		try {
			originBytes = originString.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//  ������벶���쳣����������Ҫ�ܳ�throws ..
		return encode(originBytes, returnUppercase);
	}

}
