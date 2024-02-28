package com.user.center.example.usercenter.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.center.example.usercenter.model.domain.Apicenter;
import com.user.center.example.usercenter.model.domain.User;
import com.user.center.example.usercenter.model.userRegister;
import com.user.center.example.usercenter.service.ApicenterService;
import com.user.center.example.usercenter.mapper.ApicenterMapper;
import com.user.center.example.usercenter.utils.Tools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author HZ
* @description 针对表【apicenter(api项目的用户信息表)】的数据库操作Service实现
* @createDate 2024-02-23 22:47:14
*/
@Service
public class ApicenterServiceImpl extends ServiceImpl<ApicenterMapper, Apicenter>
    implements ApicenterService {

    @Resource
    ApicenterMapper apicenterMapper;
    @Override
    public Apicenter CheckUserExist(Apicenter apicenter) {
        if (apicenter == null || apicenter.getUserPassword() == null || apicenter.getUserName() == null) return null;

        final String apicenterUserName = apicenter.getUserName();
        final String apicenterPassword = apicenter.getUserPassword();


        List<Apicenter> list = apicenterMapper.CheckUserExist(apicenter);
        if (list.size() == 0 ) return null ;
//        System.out.println(list.toString());
        Apicenter competeUser= list.get(0);

        if (!competeUser.getUserPassword().equals(apicenterPassword)){
            return null ;
        }

        return competeUser;
    }

    @Override
    public int UserRegister(userRegister user) {

        // 1.检查密码 和 二次密码是否相同
        if (!user.getCheckPassword().equals(user.getUserPassword())) return 0 ;

        // 2.检查账户长度
        if (user.getUserName().length() > 30 || user.getUserName().length() <4) return 1;
        // 3.检查密码长度
        if (user.getUserPassword().length() > 30 || user.getUserPassword().length() <6) return 2;
        // 4.检查账号和密码 字符的合法性

        if (!Tools.CharIsPassing(user.getUserName()) || !Tools.CharIsPassing(user.getUserPassword())) return 3;



        // 5.检查用户名是否重复
        if (apicenterMapper.countUserName(user.getUserName()) != 0) return 4 ;

        // 6.检查 邀请码是否存在 且合法 todo 上线的时候去数据库中查出邀请码
//        if (!user.getInvitationCode().equals(BaseMessage.Invitation_Code)) return null ;

        // 7.一切合法 之后 开始存储数据

        Apicenter safeUser = new Apicenter();
        safeUser.setUserName(user.getUserName());
        safeUser.setUserPassword(user.getUserPassword());
        // 8.调用方法生成 ak ,sk 分配给用户
        safeUser.setAccessKey(Tools.CreatAccessKey(user.getUserName()));
        safeUser.setSecretKey(Tools.CreatSecrtKey(user.getUserPassword()));
        // 9.算法实现当前时间点
        safeUser.setLastDate(Tools.AcquireTodaySeconds());
        // 10.算法实现当前 随机数字
        safeUser.setNoneNumber(RandomUtil.randomInt(10000,99999));

        // 11.向数据库钟存储数据

        if (apicenterMapper.InsertOneUser(safeUser) == 0) return 5 ;

        return 6;
    }
}




