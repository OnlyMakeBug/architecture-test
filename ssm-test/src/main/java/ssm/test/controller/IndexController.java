package ssm.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	/**
	 * ��ҳ
	 * */
	@RequestMapping("index.action")
	public String Index() {
		System.out.println("enter index.action"); 
		return "/jsp/success.jsp";    
	}
	
	
}
