package com.user.center.example.usercenter.service;

import com.user.center.example.usercenter.model.Usercenter;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;

/**
* @author HZ
* @description 针对表【usercenter】的数据库操作Service
* @createDate 2023-10-17 15:09:23
*/
@Mapper
public interface UsercenterService extends IService<Usercenter> {
    /**
     * 登录 Login
     */

    Usercenter Login(String userName , String userPassword);

    /**
     * 注册 Register
     */

    int Register(String userName , String userPassword , String checkPassword);
}
