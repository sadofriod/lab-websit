/**
 * Project Name:dingpeng_springboot2
 * File Name:GoodController.java
 * Package Name:cn.java.controller
 * Date:上午11:27:26
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.ckEc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.ckEc.entity.Good;
import cn.java.ckEc.service.GoodService;

/**
 * Description: <br/>
 * Date: 上午11:27:26 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
@Controller
@RequestMapping("/goods/")
public class GoodController {
    @Autowired
    private GoodService goodService;

    // 获取goods表中所有的数据
    @RequestMapping("/selectAllGoods.do")
    @ResponseBody
    public List<Good> selectAllGoods() {
        return goodService.getAll();
    }

}
