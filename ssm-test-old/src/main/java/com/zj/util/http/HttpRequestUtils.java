package com.zj.util.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;

import com.zj.util.StreamUtils;

/**
 * @time 2019��11��16�� ����12:03:39
 * @author zhoujian
 * @corporation luku
 * @description:http�и�ʽ������
 * @finished��true
 * @finishTime: 2019��12��27�� 14:33:30
 * @version 1.0
 */
public final class HttpRequestUtils {

	//private static Logger logger = Logger.getLogger(HttpRequestUtils.class);
	
	// default request headers
	public static String DEFAULT_ACCEPT_HEADER = "*/*";
	public static String DEFAULT_CONNECTION_HEADER = "Keep-Alive";
	public static String DEFAULT_USERAGENT_HEADER = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";

	/* default timeout */
	public static int DEFAULT_CONNECTION_TIMEOUT = 5000;
	public static int DEFAULT_READ_TIMEOUT = 15000;

	public static enum ASCIISortType {
		ASC, DESC
	}

	/** ����ʵ���� */
	private HttpRequestUtils() {
		
	}

	/**
	 * ǩ�������������ݺ�ƴ��"&"+key
	 * 
	 * @return ���content==null || content.equals("") || key==null || key.equals("") Ϊtrue,
	 * ����null��
	 */
	public static String sign(String content, String key) {
		String signedContent = null;
		if (content != null && !content.equals("") && key != null && !key.equals("")) {
			signedContent = new StringBuilder(content).append("&key=").append(key).toString();
		}
		return signedContent;
	}

	/**
	 * ����GET����
	 * 
	 * @param url  ����ҳ��url������Я������������Ϊ�ջ��߿մ���ո񡣷���IllegalAraugmentException���ᱻ�׳���
	 * @param paramsStr  ���������ַ���������Ϊ�գ����Ϊ�գ������մ�������
	 * 
	 */
	public static String sendGet(String url, String paramsStr) {

		String resultStr = null;
		if (url != null && !url.trim().equals("")) {
			String newUrl = url + "?" + (paramsStr==null ? "" : paramsStr);
			URL requestUrl;
			URLConnection connection;
			InputStream in = null;

			try {
				requestUrl = new URL(newUrl);
				connection = requestUrl.openConnection();

				// ���ó�ʱʱ�䣬0�������޵ȴ�
				// ��ʱʱ��ָ���ǽ������ӵĹ��̣�����DNS��������·��������ʱ��
				// ֻҪ����������������ȷ������ָ������Դ�Ƿ���ڶ���Ѹ�ٷ��ء�
				// һ��������������������״���ģ���ͻȻ��Ķ�����
				connection.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);
				connection.setReadTimeout(DEFAULT_READ_TIMEOUT);

				// ��������ͷ
				connection.setRequestProperty("Accept", DEFAULT_ACCEPT_HEADER);
				connection.setRequestProperty("Connection", DEFAULT_CONNECTION_HEADER);
				connection.setRequestProperty("User-Agent", DEFAULT_USERAGENT_HEADER);

				// ��������
				connection.connect();
				in = connection.getInputStream();
				
				// ����ַ���
				resultStr = StreamUtils.convertInputStreamAsString(connection.getInputStream());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				StreamUtils.closeInputStream(in);
			}

		} else {
			throw new IllegalArgumentException("invalid param url");
		}

		return resultStr;
	}

	/**
	 * ����POST����:
	 * post������bodyЯ�������ַ���,����д�����൱��Content-Type=application/x-www-form-urlencode
	 * 
	 * @param url ����ҳ��url������url����Я������,����Ϊ�ջ��߿մ���ո񡣷���IllegalAraugmentException���ᱻ�׳���
	 * @param paramsStr
	 *            ��Ϣ������
	 * 
	 */
	public static String sendPost(String url, String paramsStr) {
		String resultStr = null;

		if (url != null && !url.trim().equals("")) {
			URL requestUrl;
			URLConnection connection;
			InputStream in = null;
			OutputStream out = null;
			PrintWriter writer;

			try {
				requestUrl = new URL(url);
				connection = requestUrl.openConnection();

				// ��������ͷ
				connection.setRequestProperty("Accept", DEFAULT_ACCEPT_HEADER);
				connection.setRequestProperty("Connection", DEFAULT_CONNECTION_HEADER);
				connection.setRequestProperty("User-Agent", DEFAULT_USERAGENT_HEADER);
				// ����ϴ���������,����Ĭ��ͷ
				// connection.setRequestProperty("Content-Type",
				// "application/x-www-form-urlencoded; charset=utf-8");

				// post������Ҫ��Ҫ����input,output��
				connection.setDoInput(true);
				connection.setDoOutput(true);

				// post����Ҫ���ô˷���
				// connection.connect();

				out = connection.getOutputStream();
				writer = new PrintWriter(out);
				writer.print(paramsStr);
				writer.flush();

				// ��ȡ������
				in = connection.getInputStream();
				resultStr = StreamUtils.convertInputStreamAsString(in);

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				StreamUtils.closeInputStream(in);
				StreamUtils.closeOutputStream(out);
			}

		}else {
			throw new IllegalArgumentException("invalid param url");
		}
		return resultStr;
	}

	/*
	 * ����JSON����
	 */

	/*
	 * static class A1{ String access_token;
	 * 
	 * public String getAccess_token() { return access_token; }
	 * 
	 * public void setAccess_token(String access_token) { this.access_token =
	 * access_token; }
	 * 
	 * }
	 */

	/**
	 * ��������ַ�����ʽ�����ߣ������ַ��������ֵ���(ASCII)����
	 * 
	 * @param paramsMap ����map
	 * 		����Ϊnull���߳ߴ�Ϊ0������ֱ�ӷ���null
	 * 
	 * @param ascIISortType
	 * 		ö��ֵ�������ֵ�����/����,��ѡֵASCIISortType.ASC,ASCIISortType.ASC
	 * 
	 * @param urlEncoded
	 *            �Ƿ�Բ���ֵ����urlencoding����
	 * 
	 * @return 
	 * 		���paramsMapΪ�ջ�ߴ�Ϊ0,�Լ�map��key���ǿմ�(�����ո�)������null����������·��ظ�ʽ������ַ���
	 *      ���ؽ��ʾ��������+���룺age=23&ced=2321312321dqw&gj=23213123d22&username=z_j%E5%91%A8%E5%81%A5&wdasdge=dasd22&xdasdge=23213123
	 */
	public static String formatParamsMapByASCII(Map<String, Object> paramsMap, ASCIISortType ascIISortType,
			boolean urlEncoded) {
		if (paramsMap == null || paramsMap.size() == 0) {
			return null;
		}

		StringBuilder sb;
		String resStr;
		List<Map.Entry<String, Object>> paramsList;

		// sort
		sb = new StringBuilder();
		paramsList = new ArrayList<Map.Entry<String, Object>>(paramsMap.entrySet());

		if (ascIISortType == ASCIISortType.ASC) {
			
			Collections.sort(paramsList, new Comparator<Map.Entry<String, Object>>() {
				public int compare(Entry<String, Object> o1, Entry<String, Object> o2) {
					return o1.getKey().compareTo(o2.getKey());
				}
			});
		} else {
			Collections.sort(paramsList, new Comparator<Map.Entry<String, Object>>() {
				
				public int compare(Entry<String, Object> o1, Entry<String, Object> o2) {
					return o2.getKey().compareTo(o1.getKey());
				}
			});
		}

		// format params list
		for (Entry<String, Object> entry : paramsList) {
			String key = entry.getKey();
			String val = (String) entry.getValue();

			if (urlEncoded)
				try {
					val = URLEncoder.encode(val, "UTF-8");
				} catch (UnsupportedEncodingException e) {

					e.printStackTrace();
				}

			//key����Ϊ"" ������"  "
			if (!key.trim().equals(""))
				sb.append(key).append("=").append(val).append("&");
		}

		resStr = sb.toString();
		
		//�Ƴ����һ��&
		return resStr.equals("") ? null : resStr.substring(0, resStr.length() - 1);
	}

	/**
	 * ��ȡԶ������ip��ַ,����������ȡ�����Ŀͻ���ip��ַ
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		// һ����˵��X-Forwarded-For�����ڼ�¼������Ϣ�ģ�ÿ����һ������(�����������)��������������������������ԴIP׷����X-Forwarded-For��
		// X-Forwarded-For: 1.1.1.1, 2.2.2.2, 3.3.3.3
		
		String ipStr = request.getHeader("x-forwarded-for");
		
		// ��ȡԴip��ַ
		if(ipStr.indexOf(',') != -1) {
			ipStr = ipStr.substring(0,ipStr.indexOf(','));
		}
		
		if (ipStr == null || ipStr.length() == 0 || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getHeader("Proxy-Client-IP");
		}
		if (ipStr == null || ipStr.length() == 0 || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipStr == null || ipStr.length() == 0 || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ipStr == null || ipStr.length() == 0 || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getRemoteAddr();
		}
		return ipStr;
	}

	/*public static void main(String[] args) {
		// String content = "dsadasqwwda2";
		// System.out.println(sign(content, "hemingiojriof"));
		
		 * sendRequestByGet("https://api.weixin.qq.com/cgi-bin/token",
		 * "grant_type=client_credential&appid=wxd5fa2a6a8bfb5c89&secret=1e3da98bd58f5a96a04486756c4b5349"
		 * );
		 

		
		 * String res = com.luke.util.HttpRequest.sendGet(
		 * "https://www.lkpetq.com/petMaker/admin/user/selectPetAndPerson",
		 * "unionid=o_vAO1cwvsi_PrY1HlH09yoJwt5M");
		 * 
		 * Gson gson = new GsonBuilder().create(); A1 a =gson.fromJson(res, A1.class);
		 * System.out.println(a.getAccess_token());
		 * 
		 * //https://api.weixin.qq.com/wxa/msg_sec_check?access_token=ACCESS_TOKEN res =
		 * com.luke.util.HttpRequest.sendPost(
		 * "https://api.weixin.qq.com/wxa/msg_sec_check",
		 * "access_token="+a.getAccess_token()+"content="+ "123");
		 * System.out.println(res);
		 

		 //����get����
		 //String sendGet = sendGet("","");
		 //System.out.println(sendGet);
		
		//Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: -1
		//System.out.println("�Ұ���".substring(0,-1));

		// HttpRequestUtils hrUtils = new HttpRequestUtils();
		
		//1null
		//System.out.println("1"+null);
	}*/

}
