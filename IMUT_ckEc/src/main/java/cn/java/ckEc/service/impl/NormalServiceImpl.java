package cn.java.ckEc.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.java.ckEc.entity.Normal;
import cn.java.ckEc.mapper.NormalMapper;
import cn.java.ckEc.service.NormalService;

@Service
public class NormalServiceImpl implements NormalService
{
	@Autowired
	private HttpServletRequest request ;
	
	@Autowired
	private HttpServletResponse response ;
	
	@Autowired
	private NormalMapper normalMapper ;
	
	@Override
	public Map<String, Object> normalRegister(Normal normal) {
		    
		    Map<String,Object> respMap = new HashMap<String,Object>();
		    Map<String,Object> data = new HashMap<String,Object>();
		    Map<String,Object> message = new HashMap<String,Object>();
			
			//登录就不能注册
			HttpSession session = request.getSession();
			Normal normal2 = (Normal)session.getAttribute("user");
			if(normal2 != null)
			{
				respMap.put("errorCode", -4);
				respMap.put("message", "请先注销当前用户");
				respMap.put("data", data);
				return respMap ;
			}
			
			//检查用户名,手机号，email是否被注册
			String username = normal.getUsername();
			String email = normal.getEmail();
			String password = normal.getPassword();
			String cellphone = normal.getCellphone();
			
			Normal[] normals = normalMapper.selectUserByName(username,email,cellphone);
			System.out.println("size:"+normals.length);
			for(Normal normalFind : normals)
			{
				if(normalFind.getCellphone().equals(cellphone))
				{
					message.put("message1","手机号已注册");
				}
				if(normalFind.getEmail().equals(email))
				{
					message.put("message2","该邮箱已注册");
				}
				if(normalFind.getUsername().equals(username))
				{
					message.put("message3","用户名已注册");
				}
			}
			//含有被注册字段
			if(message.size() != 0)
			{
				respMap.put("errorCode", -2);
				respMap.put("message", message);
				respMap.put("data", data);
				return respMap ;
			}
			
			//注册信息合法 
			if(normal.getHeadSculpture() == null||normal.getHeadSculpture().equals("NULL")|| normal.getHeadSculpture().equals("null"))
			{
				normal.setHeadSculpture("/6.png");
			}
			normal.setTimeCreated(new Timestamp(System.currentTimeMillis()));
			
			int flag = normalMapper.insert(normal); // 检测是否大于等于1
			if(flag<1)
			{
				respMap.put("errorCode",-3);                     
				respMap.put("message","插入失败");
				respMap.put("data",data);
				
				return respMap ;
			}
			
			try {
				response.sendRedirect("/normal/signIn.shtml?username="+username+"&password="+password);
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return respMap ;
	}

	@Override
	public Map<String, Object> normalLogin( Map<String, String> map, HttpServletRequest request) {
		
		Map<String,Object> respMap = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user")!=null)
		{
			respMap.put("errorCode", -1);
			respMap.put("message", "请注销当前用户");
			respMap.put("data", data);
			
			return respMap ;
		}
		
		//用户名或密码为空,审查元素，防止为空
		String username = map.get("username");
		String password = map.get("password");
		if(username==null||password==null )
		{
			respMap.put("errorCode", -2);
			respMap.put("message", "请输入用户名和密码");
			respMap.put("data", data);
			
			return respMap ;
		}
		
		//用户名或密码为空,防止都输入空格
	    String username2 = username.trim();
		String password2 = password.trim();
		if(username2==""||password2=="" )
		{
			respMap.put("errorCode", -3);
			respMap.put("message", "请不要输入空格");
			respMap.put("data", data);
			
			return respMap ;
		}
		
		//根据用户名来检索用户
		Normal normal = normalMapper.selectUserByName2(username);
		System.out.println(normal);
		if(normal!=null)
		{
			if(normal.getPassword().equals(password))
			{
				//用户名密码正确
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String loginTime = sdf.format(new Date());
				session.setAttribute("user", normal);
				session.setAttribute("type", "isNormal");
				
				data.put("loginTime", loginTime);
				data.put("username", normal.getUsername());
				data.put("isNormal", true);
				data.put("headSculpture", normal.getHeadSculpture());
				
				respMap.put("errorCode", 0);
				respMap.put("message", "登录成功");
				respMap.put("data",data);
				
				return respMap ;
				
			}
			else
			{   //用户名正确，密码不正确
				respMap.put("errorCode", -4);
				respMap.put("message", "密码不正确");
				respMap.put("data", data);
				
				return respMap;
			}
		}
		else
		{
			//用户名不存在
			respMap.put("errorCode", -5);
			respMap.put("message", "用户不存在");
			respMap.put("data", data);
			
			return respMap;
		}
	}

	@Override
	public Map<String, Object> normalSignOut() {
		
		Map<String,Object> respMap = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Normal normal = (Normal)session.getAttribute("user");
		if(normal == null)
		{
			respMap.put("errorCode", -1);
			respMap.put("message", "请先登录");
			respMap.put("data", data);
			
			return respMap ;
		}
		session.setAttribute("user", null);
		
		respMap.put("errorCode", 0);
		respMap.put("message", "注销成功");
		respMap.put("data", data);
		
		return respMap ;
	}

}
