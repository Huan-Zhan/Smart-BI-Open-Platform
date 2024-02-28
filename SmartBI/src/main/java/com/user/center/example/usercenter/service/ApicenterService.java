package com.user.center.example.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.center.example.usercenter.model.domain.Apicenter;
import com.user.center.example.usercenter.model.domain.User;
import com.user.center.example.usercenter.model.userRegister;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author HZ
* @description 针对表【apicenter(api项目的用户信息表)】的数据库操作Service
* @createDate 2024-02-23 22:47:14
*/
@Mapper
public interface ApicenterService extends IService<Apicenter> {
    Apicenter CheckUserExist(Apicenter user);
    int UserRegister(userRegister user);
}
