package cn.java.ckEc.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MemberRegisterService 
{
	public Map<String,Object> powerMemberRegister(String memberName,HttpServletRequest request);
    
	public Map<String,Object> powerBackMemberRegister(String memberName,HttpServletRequest request);
}
