/**
 * Project Name:dingpeng_springboot2
 * File Name:GoodMapper.java
 * Package Name:cn.java.mapper
 * Date:上午11:23:11
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.ckEc.mapper;
/**
 * Description:	   <br/>
 * Date:     上午11:23:11 <br/>
 * @author   丁鹏
 * @version  
 * @see 	 
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import cn.java.ckEc.entity.Good;

public interface GoodMapper {

    /**
     * 
     * Description:获取springboot库下goods表中所有的数据 <br/>
     *
     * @author 丁鹏
     * @return
     */
    @Select("SELECT * FROM goods")
    public List<Map<String, Object>> getAllGoods();
    
    @Select("SELECT * FROM goods")
    public List<Good> selectAllGoods();

}
