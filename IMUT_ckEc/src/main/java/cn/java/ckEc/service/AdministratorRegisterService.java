package cn.java.ckEc.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AdministratorRegisterService
{
	public Map<String,Object> powerMemberRigister(String memberName,HttpServletRequest request);
    
	public Map<String,Object> powerBackMemberRegister(String memberName,HttpServletRequest request);
}
