package cn.java.ckEc.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.ckEc.entity.Normal;
import cn.java.ckEc.service.impl.NormalServiceImpl;

@RequestMapping("/normal")
@Controller
public class NormalController 
{
	@RequestMapping("/test")
	@ResponseBody
	public String test()
	{
		return "front/index.html";
	}
	
	@Autowired
	private NormalServiceImpl normalService ;
	
	@Autowired
	HttpServletRequest request ;
	
	@RequestMapping("/signUp.shtml")
	@ResponseBody
   public Map<String,Object> register(@Valid Normal normal,BindingResult errorResult )
   {
		//errorMap的key为校验不合法的字段，value为对应的提示信息
	    Map<String,Object> errorMap = cn.java.ckEc.utils.Validator.fieldValidate(errorResult); 
	    
	    Map<String,Object> respMap = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		
		System.out.println(normal);
				
		data.put("errorMap", errorMap);
				
		if(errorMap == null)
		{
			return normalService.normalRegister(normal);                   //上传参数符合要求
					
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
   public Map<String,Object> login(String username,String password)
   { 
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", username);
		map.put("password", password);
		return normalService.normalLogin(map, this.request);
   }
	
	@RequestMapping("/signOut.shtml")
	@ResponseBody
   public Map<String,Object> login()
   { 
		
		return normalService.normalSignOut();
   }
}
