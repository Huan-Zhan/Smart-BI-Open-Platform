package com.user.center.example.usercenter.mapper;

import com.user.center.example.usercenter.model.domain.ChartsInformation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChartsInformationMapperTest {

    @Resource
    ChartsInformationMapper chartsInformationMapper ;
    @Test
    void checkId() {
        System.out.println(chartsInformationMapper.CheckId("50","10000001").size());
    }

    @Test
    void countAll() {
        System.out.println(chartsInformationMapper.CountAll("3"));
    }

    @Test
    void UpdateChartsInformationWhenOver(){
        ChartsInformation chartsInformation = new ChartsInformation(18L,10000001L,"da","da",1);
        chartsInformationMapper.UpdateChartsInformationWhenOver(chartsInformation);
    }

    @Test
    void createUserChartsInformation() {
        chartsInformationMapper.CreateUserChartsInformation("4");
    }

}