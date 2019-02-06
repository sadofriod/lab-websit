package cn.java.ckEc.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.ckEc.service.AdministratorRegisterService;

@Controller
@RequestMapping("/super")
public class AdministratorRegisterController
{
	@Autowired
	HttpServletRequest request;
	@Autowired
	AdministratorRegisterService administratorRegisterService ;
	
	@RequestMapping("/powReg.shtml")
	@ResponseBody
	public Map<String,Object> powerMemRegister(String memberName)
	{
		System.out.println("测试0："+memberName);
		return administratorRegisterService.powerMemberRigister(memberName,this.request);
	}
	
	@RequestMapping("/powBackReg.shtml")
	@ResponseBody
	public Map<String,Object> powerBackMemRegister(String memberName,HttpServletRequest request)
	{
		System.out.println("测试12："+memberName);
		return administratorRegisterService.powerBackMemberRegister(memberName,request);
	}
}
