package cn.java.ckEc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("/hello.do")
	public String helloWorld()
	{
		return "front/login";
	}
	
	@RequestMapping("/hello2")
	public String helloWorld2()
	{
		int i = 10/0 ;
		return "哈哈";
	}
}
