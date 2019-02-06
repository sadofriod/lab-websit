package cn.java.ckEc.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.ckEc.entity.Member;
import cn.java.ckEc.mapper.MemberMapper;
import cn.java.ckEc.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService
{
	@Autowired
    private HttpServletRequest request ;
	
    @Autowired
    private MemberMapper memberMapper ;
    
	@Override
	public Map<String,Object> memberRegister(Member member,String type) {
		Map<String,Object> respMap = new HashMap<String,Object>();
		Map<String,Object> message = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		
		//检查用户名,手机号是否被注册
		HttpSession session = request.getSession();
		Member member2 = (Member)session.getAttribute("user");
		if(member2 != null)
		{
			respMap.put("errorCode", -5);
			respMap.put("message", "请先注销当前用户");
			respMap.put("data", data);
			return respMap ;
		}
		
		String memberName = member.getMemberName();
		String cellphone = member.getCellphone();
		
		if(type.trim().equals("0"))
		{
			//member申请,检查用户名和手机号是否被注册,从member_register查找,没有则添加
			Member[] members = memberMapper.selectUser(memberName,cellphone);
			
			for(Member memberFind : members)
			{
				if(memberFind.getCellphone().equals(cellphone))
				{
					message.put("message1","手机号已注册");
				}
				if(memberFind.getMemberName().equals(memberName))
				{
					message.put("message2","用户名已注册");
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
			if(member.getHeadSculpture() == null||member.getHeadSculpture().equals("NULL")|| member.getHeadSculpture().equals("null"))
			{
				member.setHeadSculpture("/6.png");
			}
			
			member.setTimeCreated(new Timestamp(System.currentTimeMillis()));
			
			int flag = memberMapper.insert(member);
			
			if(flag<1)
			{
				respMap.put("errorCode",-3);                     
				respMap.put("message","插入失败");
				respMap.put("data",data);
				
				return respMap ;
			}
			
			respMap.put("errorCode",0);                    //插入成功,申请成功,code0
			respMap.put("message","申请成功");
			respMap.put("data",data);
			
			return respMap ;
		}
		else if(type.trim().equals("1"))
		{
			//管理员申请,检查用户名和手机号是否被注册,从member_register查找,没有则添加
			Member[] administrators = memberMapper.selectAdministrator(memberName,cellphone);
			
			for(Member memberFind : administrators)
			{
				if(memberFind.getCellphone().equals(cellphone))
				{
					message.put("message1","手机号已注册");
				}
				if(memberFind.getMemberName().equals(memberName))
				{
					message.put("message2","用户名已注册");
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
			if(member.getHeadSculpture() == null||member.getHeadSculpture().equals("NULL")|| member.getHeadSculpture().equals("null"))
			{
				member.setHeadSculpture("/6.png");
			}
			
			member.setTimeCreated(new Timestamp(System.currentTimeMillis()));
			
			int flag = memberMapper.insert2(member);
			if(flag<0)
			{
				respMap.put("errorCode",-3);                     
				respMap.put("message","插入失败");
				respMap.put("data",data);
				
				return respMap ;
			}
			
			respMap.put("errorCode",0);                    //插入成功,申请成功,code0
			respMap.put("message","ok");
			respMap.put("data",data);
			
			return respMap ;
		}
		
		respMap.put("errorCode",-4);                    //插入成功,申请成功,code0
		respMap.put("message","请选择申请type");
		respMap.put("data",data);
		
		return respMap;
	}

	@Override
	public Map<String, Object> memberLogin(Map<String,String> map,String type,HttpServletRequest request) {

		
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
		String memberName = map.get("memberName");
		String password = map.get("password");
		if(memberName==null||password==null )
		{
			respMap.put("errorCode", -2);
			respMap.put("message", "请输入用户名和密码");
			respMap.put("data", data);
			
			return respMap ;
		}
		
		//用户名或密码为空,防止都输入空格
	    String username2 = memberName.trim();
		String password2 = password.trim();
		if(username2==""||password2=="" )
		{
			respMap.put("errorCode", -3);
			respMap.put("message", "请不要输入空格");
			respMap.put("data", data);
			
			return respMap ;
		}
		
		//用户名或密码为空
		
		if(type.trim().equals("0"))
		{
			//开发人员member
			Member member = memberMapper.selectUserByName6(memberName);
			
			if(member!=null)
			{
				if(member.getPassword().equals(password))
				{
					//用户名密码正确
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //登录成功 code0
					String loginTime = sdf.format(new Date());
					session.setAttribute("user", member);
					session.setAttribute("type", "isMember");
					
					data.put("loginTime", loginTime);
					data.put("memberName", member.getMemberName());
					data.put("major", member.getMajor());
					data.put("description", member.getDescription());
					data.put("isMember", true);
					data.put("headSculpture", member.getHeadSculpture());
					
					respMap.put("errorCode", 0);
					respMap.put("message", "登陆成功");
					respMap.put("data",data);
					
					return respMap ;
					
				}
				else
				{   //用户名正确，密码不正确                            /
					respMap.put("errorCode", -4);
					respMap.put("message", "密码不正确");
					respMap.put("data", data);
					
					return respMap;
				}
			}
			else
			{
				//用户名不存在
				respMap.put("code", -5);
				respMap.put("message", "用户不存在");
				respMap.put("data", data);
				
				return respMap;
			}
	
		}
		else if(type.trim().equals("1"))
		{
			//普通管理员
			Member member = memberMapper.selectUserByName4(memberName);
			System.out.println(member);
		
			if(member!=null)
			{
				if(member.getPassword().equals(password))
				{
					//用户名密码正确
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //登录成功 code0
					String loginTime = sdf.format(new Date());
					session.setAttribute("user", member);
					session.setAttribute("type", "isAdministrator");
					
					data.put("loginTime", loginTime);
					data.put("administratorName", member.getMemberName());
					data.put("major", member.getMajor());
					data.put("description", member.getDescription()); 
					data.put("isAdministrator", true);
					data.put("headSculpture", member.getHeadSculpture());
					
					respMap.put("errorCode", 0);
					respMap.put("message", "ok");
					respMap.put("data",data);
					
					return respMap ;
					
				}
				else
				{   //用户名正确，密码不正确                            /
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
		else if(type.trim().equals("2"))
		{
			//超级管理员
			Member member = memberMapper.selectUserByName7(memberName);
			
			if(member!=null)
			{
				if(member.getPassword().equals(password))
				{
					//用户名密码正确
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //登录成功 code0
					String loginTime = sdf.format(new Date());
					session.setAttribute("user", member);
					session.setAttribute("type", "isSuperAdministrator");
					
					data.put("loginTime", loginTime);
					data.put("superAdministratorName", member.getMemberName());
					data.put("isSuperAdministrator", true);
					data.put("headSculpture", member.getHeadSculpture());
					
					respMap.put("errorCode", 0);
					respMap.put("message", "登录成功");
					respMap.put("data",data);
					
					return respMap ;
					
				}
				else
				{   //用户名正确，密码不正确                            /
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
		else
		{
			//用户名不存在
			respMap.put("errorCode", -6);
			respMap.put("message", "不是合法的用户类型");
			respMap.put("data", data);
			
			return respMap;
			
		}
	}

	@Override
	public Map<String, Object> memberSignOut() {

		Map<String,Object> respMap = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("user");
		if(member == null)
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
