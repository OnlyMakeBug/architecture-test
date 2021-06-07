package test.frame.json;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.junit.Test;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @time 2019��12��12�� ����3:23:50
 * @author Administrator
 * @corporation luku
 * @description: springmvc��Ƕjson���jackson��ʹ��
 */
public class JacksonTest {

	// ����ʵ����-��¼�û�
	static class User {
		
		private String username ;
		private String password;
		
		private int age;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getUsername() {
			return username;
		}
		
		//@JsonIgnore
		public void setUsername(String username) {
			this.username = username;
		}
		
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return username + "," + password;
		}
	}

	/**
	 * ʹ��һ��readValue() - ��jsonת����pojo��(�����л�����)
	 * 
	 * ע��:json����δ֪����ʱ����ҪignoreUnknown=true
	 */
	public static void test1() {
		ObjectMapper objectMapper = new ObjectMapper();

		// 1.json��Դ - �ַ���
		/*
		 * String jsonStr = "{\"username\":\"username\",\"password\":\"password\"}";
		 * User user = null; try { //test.main.JacksonTest$User@68be2bc2 user =
		 * objectMapper.readValue(jsonStr, User.class); } catch (JsonMappingException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (JsonProcessingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } System.out.println(user);
		 */

		// 2.json��Դ-�ֽ�
		/*
		 * String jsonStr = "{\"username\":\"username\",\"password\":\"password\"}"; try
		 * { User user = objectMapper.readValue(jsonStr.getBytes(), User.class);
		 * System.out.println(user); } catch (JsonParseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (JsonMappingException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		// 3.json��Դ-File-C:\Users\Administrator\Desktop
		// ������json.text���ݣ�{"username":"username","password":"password"}
		// ע�⣺������ת���ַ���\\
		/*
		 * try {
		 * 
		 * User user = objectMapper.readValue(new
		 * File("C:/Users/Administrator/Desktop/json.txt"), User.class);
		 * System.out.println(user); } catch (JsonParseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (JsonMappingException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		// 4.json��Դ-InputStream-�˴����ֽ�������
		/*
		 * String jsonStr = "{\"username\":\"username\",\"password\":\"password\"}";
		 * ByteArrayInputStream byteArrayInputStream = new
		 * ByteArrayInputStream(jsonStr.getBytes());
		 * 
		 * try {
		 * System.out.println(objectMapper.readValue(byteArrayInputStream,User.class));
		 * } catch (JsonParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (JsonMappingException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }finally { if(null !=
		 * byteArrayInputStream) try { byteArrayInputStream.close(); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
		 */

		// 5.json��Դ-Reader
		String jsonStr = "{\"username\":\"username\",\"password\":\"password\"}";
		ByteArrayInputStream byteArrayInputStream = null;
		BufferedReader reader = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(jsonStr.getBytes());
			reader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
			User user = objectMapper.readValue(reader, User.class);
			System.out.println(user);
			//jsonתMap
			//HashMap<String,Object> jsonMap = objectMapper.readValue(reader, HashMap.class);
			
			//jsonתList
			ArrayList<String> testList = new ArrayList<String>();
			testList.add("string1");
			testList.add("string2");
			
			String listJson = objectMapper.writeValueAsString(testList);
			//["string1","string2"]
			System.out.println(listJson);
			@SuppressWarnings("unchecked")
			ArrayList<String> sList = objectMapper.readValue(listJson, ArrayList.class);
			//[string1, string2]
			System.out.println(sList);
		} catch (Exception e2) {
			// TODO: handle exception
		} finally {
			if (byteArrayInputStream != null)
				try {
					byteArrayInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * ʹ�ö�����pojo����ת����Json�ַ���(���л�����)
	 * 
	 * */
	public static void test2() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		//objectMapper.writeValueAsString(Object);
		//objectMapper.writeValueAsBytes(Object)
		User testUser = new User();
		testUser.setUsername("zhou");
		testUser.setPassword("123456");
		
		//����writValueAsString(Object)
		//ʹ�ô˷�����Jaskson��@JsonIgnore�Ƿ���Ч��   ���ǣ�����ע��ʹ����setter/getter�����൱�����������ϣ����л��ͷ����л�����Ч��
		try {
			System.out.println(objectMapper.writeValueAsString(testUser));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ο����������л�
	 * Ĭ�������Include.ALWAYS
	 * �����ö���Map��List��Ч��
	 * ������û�����Ա����л��� ����{}
	 * */
	@Test
	public void test3() {
		ObjectMapper mapper = new ObjectMapper();
		// Include.ALWAYS - Ĭ��
		// Include.NON_EMPTY - ""��null�����ᱻ���л�
		// Include.NON_NULL - ""�ᱻ���л���
		// Include.NON_DEFAULT - Ĭ��ֵ���ᱻ���л�������ʼ����ֵ���ᱻ��ʼ����intΪ0 ��������Ϊnull��
		mapper.setSerializationInclusion(Include.NON_DEFAULT);
		User user = new User();
		//user.setUsername("");
		try {
			System.out.println(mapper.writeValueAsString(user));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//test1();
		test2();
	}

}
