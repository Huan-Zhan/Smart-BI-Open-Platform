package com.user.center.example.usercenter.easy;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static StringBuilder function(File file) {
        //File file = null;
        try {
            file = ResourceUtils.getFile("D:\\IDEA\\Project\\SmartBI\\src\\main\\java\\com\\user\\center\\example\\usercenter\\files\\test.xlsx");
        } catch (Exception e) {
            System.err.println(e);
        }

        final List<Map<Integer,String>> list = EasyExcel.read(file)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet()
                .headRowNumber(0)
                .doReadSync();

//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (int i = 0; i < list.size(); i++) {
//            LinkedHashMap<Integer,String> linkedHashMap = (LinkedHashMap<Integer, String>) list.get(i);
//            List<String> datalist = linkedHashMap.values().stream().toList();
//            //System.out.println(StringUtils.join(datalist,','));
//            stringBuilder.append(StringUtils.join(datalist,',')).append("\n");
//        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(function(null));
    }


}
