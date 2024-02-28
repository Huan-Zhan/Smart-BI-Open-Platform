package com.user.center.example.usercenter.service;

import com.user.center.example.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HZ
* @description 针对表【user(智能图标的注册用户)】的数据库操作Service
* @createDate 2024-02-04 16:46:42
*/
public interface UserService extends IService<User> {

    List<String> CheckUserExist(User user);

}
