package cn.java.ckEc.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.ckEc.entity.Member;
import cn.java.ckEc.service.MemberService;
import cn.java.ckEc.utils.Validator;


@Controller
@RequestMapping("/member")
public class MemberController 
{
	@Autowired
	private MemberService memberService ;
	
	@RequestMapping("/signUp.shtml")
	@ResponseBody
   public Map<String,Object> register(@Valid Member member,BindingResult errorResult,String type )
   {
		//errorMap的key为校验不合法的字段，value为对应的提示信息
	    Map<String,Object> errorMap = Validator.fieldValidate(errorResult); 
	    
	    Map<String,Object> respMap = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
				
		data.put("errorMap", errorMap);
				
		if(errorMap == null)
		{
			return memberService.memberRegister(member,type);                   //上传参数符合要求
					
		}
		else
		{					
			respMap.put("errorCode", -1);                                        //code-1校验不成功 
			respMap.put("message", "字段校验不合法");
			respMap.put("data", data);
			return respMap ;
		}
     }
	
	
	@RequestMapping("/signIn.shtml")
	@ResponseBody
   public Map<String,Object> login(String memberName,String password,String type,HttpServletRequest request )
   {
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("memberName", memberName);
		map.put("password", password);
		
		return memberService.memberLogin(map, type, request);
   }
	
	@RequestMapping("/signOut.shtml")
	@ResponseBody
   public Map<String,Object> login()
   { 
		
		return memberService.memberSignOut();
   }
}
