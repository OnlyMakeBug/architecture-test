package test.frame.json;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;


/**
  * @time��2019��12��26�� ����9:43:50
  * @author��zhoujian
  * @corporation��luke
  * @description��alibaba fastjsonʹ��
  * @finished��false
  * @finishTime��
  *
  */
/**
 *�ŵ㣺
 * json ->Object
 * 1.����ݶ��������飬���ת����JSONObject / JSONArray
 * 2.���Ժܷ�����JSONArray/JSONObject ��ȡ�ʹ洢ֵ��
 * 
 * object->json
 * ������Ŀ������л���
 * */
public class FastJsonTest {

	//@Test
	public  void json2Object() {
		String user = "{\"username\":\"zhoujian\",\"age\":25}";
		
		/* 1.1.JSON -> Object��ȱ���ǲ�������ת��Class
		 * */
		//�˷������Զ��жϲ�ת����JSON/JSONArray
		JSONObject object = (JSONObject) JSON.parse(user);
		//ָ������JSONObject
		//JSONObject aObject = JSONObject.parseObject(user);
		
		//1.2.��ȡ���Եķ���,��Щget��ͷ�ķ��������׿��Ʒ������ͣ��緵��int��double
		object.get("username");
		object.getInteger("age");
		System.out.println(object.get("username")+"��" + object.getInteger("age"));
		
		String arr = "[1,\"zhoujian\"]";
		
		//2.1.json -> JSONArray
		JSONArray array = JSON.parseArray(arr);
		//class com.alibaba.fastjson.JSONArray
		System.out.println(JSON.parse(arr).getClass());
		
		//ע�⣺��Ҫ���Խ�����ת����JSONObject
		//java.lang.ClassCastException: com.alibaba.fastjson.JSONArray cannot be cast to com.alibaba.fastjson.JSONObject
		//System.out.println(JSON.parseObject(arr).getClass());
		//ת����Object[]
		array.toArray();
		//Ҳ������Listһ�����Ԫ��
		array.add("name");
		array.add(true);
		//[1, zhoujian, name, true]
		System.out.println(Arrays.toString(array.toArray()));
		
		//class com.alibaba.fastjson.JSONObject
		System.out.println(object.getClass());
	}
	
	/**
	 * ������/����ת����json
	 * 
	 * Ĭ������£�null���Բ��ᱻ���л���String����Ϊ""Ҳ�ᱻ���л���
	 * 
	 * ����ʹ�ù����� + SerializerFeature���п���
	 * 
	 * <pre>
	 * NOTE:
	 * SerializerFeature.WriteNullStringAsEmpty - ��nullֵ����Ϊ"" - Ĭ���������Ч�������Թ����ˣ����ֵ��������Ч�����鵥��ʹ��ֵ������ʵ�֡�			
	 * SerializerFeature.UseSingleQuotes - �����Ժ�ֵ�ĵ������滻��''������SerializerFeature.QuoteFieldNamesͬʱ���ڣ�������Ч�����Ժ�ֵ����''
	 * SerializerFeature.QuoteFieldNames- ������ʹ��"",Ĭ��Ϊtrue
	 * SerializerFeature.WriteNullListAsEmpty���CList�ֶ����Ϊnull,���Ϊ[],����null,Ĭ��true
	 * </pre>
	 * */
	@Test
	public  void objectAndArrayToJson() {
		
		String user = "{\"username\":\"zhoujian\",\"age\":25,\"school\":null}";
	    JSONObject userObject = JSON.parseObject(user);
	    
		//1.parse(String)
		// {"age":25,"username":"zhoujian",}
		System.out.println(userObject);
		// {"age":25,"username":"zhoujian"}
		//System.out.println(JSON.parse(user).toString());
		
		/*
		 * ����null�����ܷ����л���null���ᱻ���л���
		 * StringΪ""�ᱻ���л�*/
		
		userObject.put("school","");
		////{"age":25,"school":"","username":"zhoujian"}
		System.out.println(userObject);
		
		userObject.put("child", null);
		
		//UseSingleQuotes�����Ժ�ֵ��""���滻��''
		//{'age':25,'school':'','username':'zhoujian'}
		System.out.println(JSONObject.toJSONString(userObject, SerializerFeature.UseSingleQuotes));
		
		//SerializerFeature.WriteNullStringAsEmpty - ��nullֵ����Ϊ""
		//��Ч�����ܽ�nullֵ���س�"",����ʹ��ֵ������ʵ��
		//ע�⣺System.out.println(JSONObject.toJSONString(userObject,filter,SerializerFeature.WriteNullStringAsEmpty)); -������Ч
		//System.out.println(JSONObject.toJSONString(userObject,SerializerFeature.WriteNullStringAsEmpty));
		
		//2.ʹ��ֵ��������null����Ϊ""
		//{"age":25,"child":"","school":"","username":"zhoujian"}
		System.out.println(JSONObject.toJSONString(userObject, new ValueFilter() {

			public Object process(Object source, String name, Object value) {
				// TODO Auto-generated method stub
				if(value==null)
					return "";
				
				return value;
			}
			
		}));
		
		//3.���ָ������null?
		//SerializerFeature.WriteMapNullValue)������null�Ĺ���
		//{"age":25,"child":null,"school":"","username":"zhoujian"}
		System.out.println(JSONObject.toJSONString(userObject, SerializerFeature.WriteMapNullValue,SerializerFeature.UseSingleQuotes));
	
		System.out.println(JSONObject.toJSONString(userObject,SerializerFeature.QuoteFieldNames,SerializerFeature.UseSingleQuotes));
		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		
		userObject.put("list", new JSONArray());
		//{"age":25,"list":[],"school":"","username":"zhoujian"}
		System.out.println(JSONObject.toJSONString(userObject));
		
	}
	
	public String object2JSON() {
		
		return null;
	}
}
