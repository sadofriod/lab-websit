package cn.java.ckEc.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler
{
	@ResponseBody
	@ExceptionHandler(value=java.lang.ArithmeticException.class)
	public Map<String,Object> handlerException(ArithmeticException ex)
	{
		ex.printStackTrace();
		Map<String,Object> map = new HashMap<>();
		
		map.put("error", ex.getMessage());
		
		return map;
	}
}
