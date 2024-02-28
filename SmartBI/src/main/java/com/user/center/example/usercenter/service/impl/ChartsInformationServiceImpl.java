package com.user.center.example.usercenter.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.center.example.usercenter.mapper.ChartsInformationMapper;

import com.user.center.example.usercenter.model.domain.ChartsInformation;

import com.user.center.example.usercenter.service.ChartsInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChartsInformationServiceImpl extends ServiceImpl<ChartsInformationMapper, ChartsInformation>
    implements ChartsInformationService {

    @Resource
    ChartsInformationMapper chartsInformationMapper;

    @Override
    public Object CreateUserChartsInformation(String userId) {
        if (userId.length() == 0) return "创建失败";

        try {
            chartsInformationMapper.CreateUserChartsInformation(userId);
        }catch (Exception e){
            throw (e);
        }
        return "创建成功";
    }

    @Override
    public List<ChartsInformation> SelectUserChartsInformation(String userId) {
        if (userId.length() == 0) return null;
        List<ChartsInformation> list ;
        try {
            list = chartsInformationMapper.SelectUserChartsInformation(userId);
        }catch (Exception e){
            throw (e);
        }

        System.out.println(list.toString());


        return list;
    }

    /**
     * true  - 存在
     * false - 不存在
     * @param userId
     * @return
     */
    @Override
    public boolean CheckTablesIsExist(String userId) {

        String str = chartsInformationMapper.CheckTablesIsExist(userId);

        if (str == null) return false;

        return true;
    }


    /**
     * 异步化 Ai 未生成
     * @param chartsInformation
     * @return
     */
    @Override
    public boolean InsertChartInformation(ChartsInformation chartsInformation) {
        if (chartsInformation == null) return false;
        if (chartsInformationMapper.InsertChartInformation(chartsInformation) == 0){
            return false;
        }
        return true;
    }

    /**
     * 异步化 Ai 生成完毕
     * @param chartsInformation
     * @return
     */
    @Override
    public boolean InsertChartInformationOver(ChartsInformation chartsInformation) {
        if (chartsInformation == null) return false;
        if (chartsInformationMapper.InsertChartInformation(chartsInformation) == 0){
            return false;
        }
        return true;
    }


    /**
     * 查询 CharsInformation_UserId 中的全部 合法数据
     * @param userId
     * @return
     */
    @Override
    public List<ChartsInformation> SelectAllList(String userId) {
        final List<ChartsInformation> list = chartsInformationMapper.SelectAll(userId);
        return list;
    }

    @Override
    public int CountAll(String userId) {
        return   chartsInformationMapper.CountAll(userId);
    }

    @Override
    public int UpdateChartsInformationWhenOver(ChartsInformation chartsInformation) {
        chartsInformationMapper.UpdateChartsInformationWhenOver(chartsInformation);
        return 0;
    }

    @Override
    public int DeleteById(Long userId, String dataId) {
        int res = chartsInformationMapper.DeleteById(userId,dataId);

        return  res ;
    }
}
