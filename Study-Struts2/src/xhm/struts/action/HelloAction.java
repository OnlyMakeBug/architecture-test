package xhm.struts.action;

/*
 *1.Action��pojo�ࣺ����Ҫʵ�ֽӿںͼ̳���,
 */
public class HelloAction {
	
	public String execute() {
		return "hello";
	}
	
	public String execute2() {
		return "hello";
	}
	
	public String update() {
		System.out.println("����Update");
		return "update_ok";
	}
	
	public String delete() {
		return "ok";
	}
	
	public String add() {
		return "add_ok";
	}
	
	public String dynamicMethod() {
		return "ok";
	}
	
}
