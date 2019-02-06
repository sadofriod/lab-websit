/**
 * Project Name:dingpeng_springboot2
 * File Name:GoodService.java
 * Package Name:cn.java.service.impl
 * Date:上午11:26:26
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.ckEc.service;

import java.util.List;

import cn.java.ckEc.entity.Good;

/**
 * Description: <br/>
 * Date: 上午11:26:26 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
public interface GoodService {

    /**
     * 
     * Description: 查询所有商品<br/>
     *
     * @author 丁鹏
     * @return
     */
    // List<Map<String, Object>> getAll();

    List<Good> getAll();

}
