/**
 * Project Name:dingpeng_springboot2
 * File Name:GoodServiceImpl.java
 * Package Name:cn.java.service.impl
 * Date:上午11:24:19
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.ckEc.service.impl;
/**
 * Description:	   <br/>
 * Date:     上午11:24:19 <br/>
 * @author   丁鹏
 * @version  
 * @see 	 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.ckEc.entity.Good;
import cn.java.ckEc.mapper.GoodMapper;
import cn.java.ckEc.service.GoodService;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodMapper goodMapper;

    /**
     * 简单描述该方法的实现功能（可选）.
     * 
     * @see cn.java.service.impl.GoodService#getAll()
     */
    // @Override
    // public List<Map<String, Object>> getAll() {
    // return goodMapper.getAllGoods();
    // }

    @Override
    public List<Good> getAll() {
        return goodMapper.selectAllGoods();
    }
}
