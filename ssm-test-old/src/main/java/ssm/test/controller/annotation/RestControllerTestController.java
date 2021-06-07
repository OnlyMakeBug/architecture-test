package ssm.test.controller.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ssm.test.controller.User;
/*
 * RestController�൱��@ResponseBody + @Controller
 * ����ע��json controller
 * */

//@ResponseResult - �ҿ��������˵Ĵ��a�е��]�⣬��ԓ��@RestController����һ�ӣ��������ϰ汾�С�
@RestController
@RequestMapping("annotationtest")
public class RestControllerTestController {

	/*
	 * ���ԣ� @RestController��������ֵʱǰ�˷��أ�
	 * Content-Length:0
	   Date:Fri, 01 Nov 2019 09:47:02 GMT
	 * 
	 * */
	@RequestMapping("test1")
	public void test1() {
		System.out.println("annotation test1");
	} 
	
	/*
	 * Content-Disposition:inline;filename=f.txt
	   Content-Type:application/json;charset=UTF-8
	   Date:Fri, 01 Nov 2019 09:50:27 GMT
       Transfer-Encoding:chunked
	 * 
	 * ǰ�˷��أ�
	 * {"username":"wangbaoqiang","age":50}
	 * */
	@RequestMapping("test2")
	public User test2() {
		User queryUser = new User();
		queryUser.setUsername("wangbaoqiang");
		queryUser.setAge(50);
		return queryUser;
	}
}
