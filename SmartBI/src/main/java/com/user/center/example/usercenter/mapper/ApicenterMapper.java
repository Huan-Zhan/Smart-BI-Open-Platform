package com.user.center.example.usercenter.mapper;

import com.user.center.example.usercenter.model.domain.Apicenter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author HZ
* @description 针对表【apicenter(api项目的用户信息表)】的数据库操作Mapper
* @createDate 2024-02-23 22:47:14
* @Entity com.user.center.example.usercenter.model.domain.Apicenter
*/
public interface ApicenterMapper extends BaseMapper<Apicenter> {

    List<Apicenter> CheckUserExist(Apicenter apicenter);
    int InsertOneUser(Apicenter apicenter);

    int countUserName(String UserName);

}




