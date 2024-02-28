package com.user.center.example.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.center.example.usercenter.model.domain.User;
import com.user.center.example.usercenter.service.UserService;
import com.user.center.example.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

/**
* @author HZ
* @description 针对表【user(智能图标的注册用户)】的数据库操作Service实现
* @createDate 2024-02-04 16:46:42
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    /**
     *
     * @param user
     * @return List.get(0) userName
     * @return List.get(1) userId
     */
    @Override
    public List<String> CheckUserExist(User user) {

        if (user == null || user.getUserPassword() == null || user.getUserAccount() == null) return null;

        final String userAccount = user.getUserAccount();
        final String userPassword = user.getUserPassword();

        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("userAccount",userAccount);

        final User competeUser = this.getOne(queryWrapper);

        if (!competeUser.getUserPassword().equals(userPassword)){
            return null ;
        }

        List<String> list = new ArrayList<>();
        list.add(competeUser.getUserName());
        list.add(competeUser.getUserId().toString());
        return list;
    }
}




