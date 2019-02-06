package cn.java.ckEc.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.ckEc.service.MemberRegisterService;

@Controller
@RequestMapping("/admin")
public class MemberRegisterController
{
	@Autowired
	MemberRegisterService memberRegisterService ;
	
	@RequestMapping("/powRegister.shtml")
	@ResponseBody
	public Map<String,Object> powerMemRegister(String memberName,HttpServletRequest request)
	{
		System.out.println("测试12："+memberName);
		return memberRegisterService.powerMemberRegister(memberName,request);
	}
	@RequestMapping("/powBackRegister.shtml")
	@ResponseBody
	public Map<String,Object> powerBackMemRegister(String memberName,HttpServletRequest request)
	{
		System.out.println("测试12："+memberName);
		return memberRegisterService.powerBackMemberRegister(memberName,request);
	}
}
