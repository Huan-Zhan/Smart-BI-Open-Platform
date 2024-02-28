package com.user.center.example.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.center.example.usercenter.model.Usercenter;
import com.user.center.example.usercenter.service.UsercenterService;
import com.user.center.example.usercenter.mapper.UsercenterMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;

/**
* @author HZ
* @description 针对表【usercenter】的数据库操作Service实现
* @createDate 2023-10-17 15:09:23
*/
@Service
public class UsercenterServiceImpl extends ServiceImpl<UsercenterMapper, Usercenter>
    implements UsercenterService {

    @Resource
    UsercenterMapper usercenterMapper ;

    private static final String Salt = "huanzhan";

    @Override
    public Usercenter Login(String userName, String userPassword) {
        // 账号长度问题
        if (userName.length()<4 || userName.length()>20){
            return null ;
        }
        // 用户名是否存在
        QueryWrapper<Usercenter> wrapper = new QueryWrapper<>();

        wrapper.eq("userName",userName);

        this.count(wrapper);

        if (this.count(wrapper) != 1){
            return null ;
        }

        // 密码长度问题
        if (userPassword.length()<4 || userPassword.length()>20){
            return null ;
        }

        // 密码正确

        final List<Usercenter> usercenters = usercenterMapper.selectList(wrapper);

        if (userName.equals("huanzhan")||userName.equals("admin")){

            /**
             * 管理员
             */
            if (!userPassword.equals(usercenters.get(0).getUserPassword())){
                return null ;
            }
        }else {

            /**
             * 普通用户身份
             */
            if (!DigestUtils.md5DigestAsHex((userPassword+Salt).getBytes()).equals(usercenters.get(0).getUserPassword())){
                return null ;
            }
        }

        Usercenter saftyUser = usercenters.get(0) ;
        saftyUser.setUserPassword(null);

        return saftyUser;
    }

    @Override
    public int Register(String userName, String userPassword, String checkPassword) {

        if (userName.length()<4 || userName.length()>20){
            return -1 ;
        }

        QueryWrapper<Usercenter> wrapper = new QueryWrapper<>();

        wrapper.eq("userName",userName);

        this.count(wrapper);

        if (this.count(wrapper) > 0){
            return -1 ;
        }

        if (userPassword.length()<4 || userPassword.length()>20){
            return -1 ;
        }

        if (!userPassword.equals(checkPassword)){
            return -1 ;
        }

        /**
         * 密码加密
         */
        userPassword = DigestUtils.md5DigestAsHex((userPassword+Salt).getBytes());

        Usercenter usercenter = new Usercenter();

        usercenter.setUserName(userName);
        usercenter.setUserPassword(userPassword);

        this.save(usercenter);

        return usercenter.getId();
    }
}




