package cn.java.ckEc.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.java.ckEc.entity.Member;
import cn.java.ckEc.mapper.AdministratorRegisterMapper;
import cn.java.ckEc.mapper.MemberRegisterMapper;
import cn.java.ckEc.service.AdministratorRegisterService;

@Service
public class AdministratorRegisterServiceImpl implements AdministratorRegisterService
{
	@Autowired
	private HttpServletResponse response ;
	
	@Autowired
    AdministratorRegisterMapper administratorRegisterMapper ;
    
	@Transactional(readOnly=false)      //授权包括update申请表状态，insert两个操作，具有事务性
	@Override
	public Map<String, Object> powerMemberRigister(String memberName, HttpServletRequest request) {
        
		System.out.println("测试");
		Map<String,Object> respMap = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		
		//Member,普通管理员，超级管理员登录之后都会封装到一个member对象中存入session
		Member member = (Member) session.getAttribute("user");
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
		
		//防止membe表中有和管理表同名现象，过滤普通成员和普通管理员
		if (!memberType.equals("isSuperAdministrator")) {
			respMap.put("errorCode", -2);
			respMap.put("message", "您没有权限审批");
			respMap.put("data", data);

			return respMap;
		}
		
		//检查memberName是否在member_register申请表中
		String memberNameFind = administratorRegisterMapper.selectMemberRegisterByName2(memberName);
		if (memberNameFind == null) {
			respMap.put("errorCode", -3);
			respMap.put("message", "member申请表中没有此申请");
			respMap.put("data", data);

			return respMap;
		}
		
		//检查申请是否已经被审批
        int status = administratorRegisterMapper.selectAdministratorRegisterByName(memberName);
		if(status == 1)
		{
			respMap.put("errorCode", -4);
			respMap.put("message","申请已审批");
			respMap.put("data", data);
			
			return respMap;
		}
		else if(status == 2)
		{
			respMap.put("errorCode", -5);
			respMap.put("message", "此申请已审批并注销");
			respMap.put("data", data);

			return respMap;
		}
		else if(status == 0)
		{
			//检查memberName是否在administrator表中已经插入，而在申请表中status为0，数据库异常
			String memberNameFind2 = administratorRegisterMapper.selectMemberRegisterByName3(memberName);
			if (memberNameFind2 != null) {
				respMap.put("errorCode", -6);
				respMap.put("message", "该用户名已经申请过");
				respMap.put("data", data);

				return respMap;
			}
			System.out.println("测试3:id"+memberName);
			
			int userId = member.getId();
		    int flag = administratorRegisterMapper.updateAdministratorRegisterStatus(memberName,userId);
		    System.out.println("测试4:flag"+flag);
		    //授权失败
		    if(flag<1)
		    {
		    	respMap.put("errorCode", -7);
				respMap.put("message","授权失败");
				respMap.put("data", data);
				
				return respMap ;
		    }
		    
		    //先将数据从administrator_rigister表中取出来
		    Member memberFind = administratorRegisterMapper.selectMemberByName(memberName); 
		    memberFind.setTimeCreated(new Timestamp(System.currentTimeMillis()));
		    
		    //将administrator信息插入admibistrator表中
		    int flag2 = administratorRegisterMapper.insertMember(memberFind);
		    
		    if(flag2<1)
		    {
		    	respMap.put("errorCode", -8);
				respMap.put("message","授权成功，插入失败");
				respMap.put("data", data);
				
				return respMap ;
		    }
		    
		    respMap.put("errorCode", 0);
			respMap.put("message","ok");
			respMap.put("data", data);
			
			return respMap ;
		}
		else
		{
			respMap.put("errorCode", -9);                 //数据库异常
			respMap.put("message","此申请状态异常");
			respMap.put("data", data);
			
			return respMap ; 
			
		}
		
	}
	@Transactional(readOnly=false)      //注销一个用户包括update申请表状态，delete用户表两个操作，具有事务性
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
		
		//防止administrator表中有和其它表数据同名现象，过滤普通成员和普通管理员
		if(!memberType.equals("isSuperAdministrator"))
		{
			respMap.put("errorCode", -1);
			respMap.put("message", "您没有权限注销用户");
			respMap.put("data", data);
			
			return respMap ;
		}
		
		//检查administrator表中是否有此administrator
		String memberNameFind = administratorRegisterMapper.selectAdministratorByName2(memberName);
		if(memberNameFind == null)
		{
			respMap.put("errorCode", -2);
			respMap.put("message", "member表中没有这条记录");
			respMap.put("data", data);
			
			return respMap ;
		}
		
		//检查administrator_register表中是否有此administrator
		Map<String,Integer> map = administratorRegisterMapper.selectAdministratorByName3(memberName);
		if (map == null) {
			respMap.put("errorCode", -3);
			respMap.put("message", "administrator申请表中没有这条记录");
			respMap.put("data", data);

			return respMap;
		}
		
		int status = administratorRegisterMapper.selectAdministratorRegisterByName(memberName);
		if(status == 1)
		{
            int flag = administratorRegisterMapper.deleteAdministratorByName(memberName);
			
			if(flag<1)
			{
				respMap.put("errorCode", -4);
				respMap.put("message", "删除记录失败");
				respMap.put("data", data);
				
				return respMap ;
			}
			int userId = member.getId();
			int flag2 = administratorRegisterMapper.updateAdministratorRegisterStatus2(memberName, userId);
			
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
