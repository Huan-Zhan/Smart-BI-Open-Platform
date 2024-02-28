package com.user.center.example.usercenter.model.restful;

import com.user.center.example.usercenter.model.domain.ChartsInformation;
import com.user.center.example.usercenter.model.domain.Usercharts;
import lombok.Data;

@Data
public class HistoryChartsInformation {

    private Usercharts usercharts;
    private ChartsInformation chartsInformation;

    public HistoryChartsInformation() {
    }

    public HistoryChartsInformation(Usercharts usercharts, ChartsInformation chartsInformation) {
        this.usercharts = usercharts;
        this.chartsInformation = chartsInformation;
    }
}
