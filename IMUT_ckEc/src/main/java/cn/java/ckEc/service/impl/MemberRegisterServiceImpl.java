package cn.java.ckEc.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.java.ckEc.entity.Member;
import cn.java.ckEc.mapper.MemberRegisterMapper;
import cn.java.ckEc.service.MemberRegisterService;

@Service
public class MemberRegisterServiceImpl implements MemberRegisterService
{
	@Autowired
	private HttpServletResponse response ;
	  
	@Autowired
    MemberRegisterMapper memberRegisterMapper ;
	
	@Transactional(readOnly=false)      //授权包括update申请表状态，insert两个操作，具有事务性
	@Override
	public Map<String,Object> powerMemberRegister(String memberName,HttpServletRequest request) {
		
		Map<String,Object> respMap = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		
		//Member,普通管理员，超级管理员登录之后基本信息都会封装到一个member对象中存入session
		Member member = (Member) session.getAttribute("user");   //普通用户登录无法由normal转到member进行授权将会产生异常，转到404
		String memberType = (String) session.getAttribute("type");
		System.out.println("member:"+member);
		
		//返回到login.html
		if (member == null) 
		{
			System.out.println("测绘师");
			try {
				response.sendRedirect("../pages/front/login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return respMap;
		}
		
		//防止membe表中有和管理表同名现象，过滤普通成员
		if(!memberType.equals("isAdministrator")&&!memberType.equals("isSuperAdministrator"))
		{
			respMap.put("errorCode", -1);
			respMap.put("message", "您没有权限审批");
			respMap.put("data", data);
			
			return respMap ;
		}
				
		
		//检查memberName是否在member_register申请表中
		String memberNameFind = memberRegisterMapper.selectMemberRegisterByName2(memberName);
		if(memberNameFind == null)
		{
			respMap.put("errorCode", -2);
			respMap.put("message","member申请表中没有此申请");
			respMap.put("data", data);
			
			return respMap;
		}
		
		//检查这条申请是否已被审批，返回status，1为已审批，0为未审批
		int status = memberRegisterMapper.selectMemberRegisterByName(memberName);
		System.out.println("status:"+status);
		if (status == 1) 
		{
			respMap.put("errorCode", -3);
			respMap.put("message", "申请已审批");
			respMap.put("data", data);

			return respMap;
		}
		else if(status == 2)               //此账号已注销，管理人员误操作进行授权
		{
			respMap.put("errorCode", -4);
			respMap.put("message", "此申请已审批并注销");
			respMap.put("data", data);

			return respMap;
		}
		else if(status == 0)
		{
			//检查memberName是否在member表中已经插入，但在申请表中status为0，这是一种数据库异常
			String memberNameFind2 = memberRegisterMapper.selectMemberRegisterByName3(memberName);
			if (memberNameFind2 != null) {
				respMap.put("errorCode", -5);
				respMap.put("message", "该用户名已经申请过");
				respMap.put("data", data);

				return respMap;
			}
			
			int typePower = 0;
			if(memberType.equals("isAdministrator"))
			{
				typePower = 1 ;
			}
			else if(memberType.equals("isSuperAdministrator"))
			{
				typePower = 2;
			}
			int userId = member.getId();
		    int flag = memberRegisterMapper.updateMemberRegisterStatus(memberName,userId,typePower);
		    System.out.println("测试9："+flag);
		    
		    int i = 10/0 ;
		    
		    //授权失败
		    if(flag<1)
		    {
		    	respMap.put("errorCode", -6);
				respMap.put("message","授权失败");
				respMap.put("data", data);
				
				return respMap ;
		    }
		    
		    //先将数据从member_rigister表中取出来
		    Member memberFind = memberRegisterMapper.selectMemberByName(memberName);
		    System.out.println("测试10:"+memberFind);
		    memberFind.setTimeCreated(new Timestamp(System.currentTimeMillis()));
		    //将member信息插入member表中
		    int flag2 = memberRegisterMapper.insertMember(memberFind);
		    System.out.println("测试11:"+flag2);
		    
		    if(flag2<1)
		    {
		    	respMap.put("errorCode", -7);
				respMap.put("message","授权成功，插入失败");
				respMap.put("data", data);
				
				return respMap ;
		    }
		    
		    respMap.put("errorCode", 0);
			respMap.put("message","审批成功");
			respMap.put("data", data);
			
			return respMap ; 
		}
		else
		{
			respMap.put("errorCode", -8);                 //数据库异常
			respMap.put("message","此申请状态异常");
			respMap.put("data", data);
			
			return respMap ; 
			
		}
		
	}
	
//	@Transactional(readOnly=false)      //注销用户包括update申请表状态，delete两个操作，具有事务性
	@Override
	public Map<String, Object> powerBackMemberRegister(String memberName, HttpServletRequest request) {


		Map<String,Object> respMap = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		
		//Member,普通管理员，超级管理员登录之后基本信息都会封装到一个member对象中存入session
		Member member = (Member) session.getAttribute("user");   //普通用户登录无法由normal转到member进行授权将会产生异常，转到404
		String memberType = (String) session.getAttribute("type");
		System.out.println("member:"+member);
		
		//返回到login.html
		if (member == null) 
		{
			System.out.println("测绘师");
			try {
				response.sendRedirect("../pages/front/login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return respMap;
		}
		
		//防止member表中有和管理表同名现象，过滤普通成员
		if(!memberType.equals("isAdministrator")&&!memberType.equals("isSuperAdministrator"))
		{
			respMap.put("errorCode", -1);
			respMap.put("message", "您没有权限注销用户");
			respMap.put("data", data);
			
			return respMap ;
		}
		
		//检查member表中是否有此member
		String memberNameFind = memberRegisterMapper.selectMemberByName2(memberName);
		if(memberNameFind == null)
		{
			respMap.put("errorCode", -2);
			respMap.put("message", "member表中没有这条记录");
			respMap.put("data", data);
			
			return respMap ;
		}
		
		//检查member_register表中是否有此member
		Map<String,Object> map = memberRegisterMapper.selectMemberByName3(memberName);
		if (map == null) {
			respMap.put("errorCode", -3);
			respMap.put("message", "member申请表中没有这条记录");
			respMap.put("data", data);

			return respMap;
		}
		//检查member_register表中是否有此member的status
		int status = memberRegisterMapper.selectMemberByName4(memberName);
		
		if(status == 1)
		{
            int flag = memberRegisterMapper.deleteByName(memberName);
			
            int i = 10/0 ;
            
			if(flag<1)
			{
				respMap.put("errorCode", -4);
				respMap.put("message", "删除记录失败");
				respMap.put("data", data);
				
				return respMap ;
			}
			int powerType= 0;
			int userId = member.getId();
			if(memberType.trim().equals("isAdministrator"))
			{
				powerType = 1 ;//普通管理员
			}
			else if(memberType.trim().equals("isSuperAdministrator"))
			{
				powerType = 2 ;
			}
			int flag2 = memberRegisterMapper.updateMemberRegisterStatus2(memberName, userId, powerType);
			
			if(flag2<1)
			{
				respMap.put("errorCode", -5);
				respMap.put("message", "修改记录状态失败");
				respMap.put("data", data);
				
				return respMap ;
			}
			
			respMap.put("errorCode", 0);
			respMap.put("message", "注销账户成功");
			respMap.put("data", data);
			
			return respMap ;
		}
		else
		{
			respMap.put("errorCode", -6);
			respMap.put("message", "记录状态异常");
			respMap.put("data", data);
			
			return respMap ;
		}
	}
}
