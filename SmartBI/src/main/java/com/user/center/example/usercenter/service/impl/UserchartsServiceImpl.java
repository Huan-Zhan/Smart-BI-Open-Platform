package com.user.center.example.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.center.example.usercenter.model.domain.Usercharts;
import com.user.center.example.usercenter.service.UserchartsService;
import com.user.center.example.usercenter.mapper.UserchartsMapper;
import org.springframework.stereotype.Service;

/**
* @author HZ
* @description 针对表【usercharts(用户图标总表)】的数据库操作Service实现
* @createDate 2024-02-04 16:19:57
*/
@Service
public class UserchartsServiceImpl extends ServiceImpl<UserchartsMapper, Usercharts>
    implements UserchartsService{

}




