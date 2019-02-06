package cn.java.ckEc.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.java.ckEc.entity.Member;


public interface MemberService 
{
	public Map<String,Object> memberRegister(Member member,String type);
	
	public Map<String,Object> memberSignOut();
	
	public Map<String,Object> memberLogin(Map<String,String> map,String type,HttpServletRequest request);
}
