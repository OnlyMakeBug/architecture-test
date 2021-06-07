package com.zj.util;

public class HexUtils {
	
	/** ���������ݷ��س�Ϊ16�����ַ��� */
	public  static String toHexString(byte[] bytes) throws NullByteArrayException{
		
		if(bytes == null )
			throw new NullByteArrayException();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i=0; i<bytes.length ; i ++) {
			int val = bytes[i]  & 0xff;
			if(val < 16) {
				stringBuilder.append("0");
			}
			
			stringBuilder.append(Integer.toHexString(val));
		}
		
		return stringBuilder.toString();
	}
}
