package com.user.center.example.usercenter.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.center.example.usercenter.model.domain.ChartsInformation;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ChartsInformationMapper extends BaseMapper<ChartsInformation> {
    List<ChartsInformation> SelectUserChartsInformation(String userId);
    ChartsInformation CreateUserChartsInformation(String userId);

//    @MapKey("table_name") // 指定 mapkey 为 table_name
    String CheckTablesIsExist(String userId);

    Integer InsertChartInformation(ChartsInformation chartsInformation);
    Integer InsertChartInformationOver(ChartsInformation chartsInformation);
    Integer UpdateChartsInformationWhenOver(ChartsInformation chartsInformation);

    List<ChartsInformation> SelectAll(String userId);


    List<ChartsInformation> CheckId(String id , String userId);

    int CountAll(String userId);

    int DeleteById(Long userId , String dataId);



}
