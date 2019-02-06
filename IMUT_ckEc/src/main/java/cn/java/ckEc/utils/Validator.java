package cn.java.ckEc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Validator {
    
    public static Map<String, Object> fieldValidate(BindingResult errorResult) 
    {
        Map<String, Object> errorMap = null;
        boolean flag = errorResult.hasErrors();
        if (flag) 
        {// 数据有错
            errorMap = new HashMap<String, Object>();
            // 将字段对应的错误信息答应出来
            List<FieldError> errorList = errorResult.getFieldErrors();
            for (FieldError fieldError : errorList)
            {
                // 1、获取实体类中的属性名
                String fieldName = fieldError.getField();
                // 2、当数据不满足匹配规则时，获取错误提示信息
                String errorMessage = fieldError.getDefaultMessage();
                System.out.println(fieldName + "=" + errorMessage);
                errorMap.put(fieldName, errorMessage);
            }
        }
        return errorMap;
    }
}
