package com.user.center.example.usercenter.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.user.center.example.usercenter.model.Usercenter;

import com.user.center.example.usercenter.model.domain.ChartsInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ChartsInformationService extends IService<ChartsInformation> {
    Object CreateUserChartsInformation(String userId);
    List<ChartsInformation> SelectUserChartsInformation(String userId);
    boolean CheckTablesIsExist(String userId);

    boolean InsertChartInformation(ChartsInformation chartsInformation);
    boolean InsertChartInformationOver(ChartsInformation chartsInformation);

    List<ChartsInformation> SelectAllList(String userId);

    int CountAll(String userId);

    int UpdateChartsInformationWhenOver(ChartsInformation chartsInformation);

    int DeleteById(Long userId,String dataId);

}
