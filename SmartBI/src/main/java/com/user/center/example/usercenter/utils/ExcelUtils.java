package com.user.center.example.usercenter.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExcelUtils {
    public static StringBuilder toCSV(MultipartFile multipartFile){

        if (multipartFile == null) return null ;


        List<Map<Integer,String>> list = null;

        final String fileType = getFileType(multipartFile);

        // 读 excel 文件

        if (fileType.equals("xlsx")){
            try {
                list = EasyExcel.read(multipartFile.getInputStream())
                        .excelType(ExcelTypeEnum.XLSX)
                        .sheet()
                        .headRowNumber(0)
                        .doReadSync();
            } catch (IOException e) {
                log.error("表格转CSV失败 ！"+e);
                //throw new RuntimeException(e);
            }
        }else if (fileType.equals("xls")){
            try {
                list = EasyExcel.read(multipartFile.getInputStream())
                        .excelType(ExcelTypeEnum.XLS)
                        .sheet()
                        .headRowNumber(0)
                        .doReadSync();
            } catch (IOException e) {
                log.error("表格转CSV失败 ！"+e);
                //throw new RuntimeException(e);
            }
        }else {
            return null;
        }



        if (list == null || list.isEmpty()){
            return null ;
        }

        /**
         * 文件不为空 且 合法
         * 转 csv
          */

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            LinkedHashMap<Integer,String> linkedHashMap = (LinkedHashMap<Integer, String>) list.get(i);
            List<String> datalist = linkedHashMap.values().stream().toList();
            stringBuilder.append(StringUtils.join(datalist,',')).append("\n");
        }
        return stringBuilder;
    }

    /**
     * 获得 文件拓展名
     */
    public static String getFileType(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        if (fileName != null && fileName.length() > 0) {
            int index = fileName.lastIndexOf(".");
            if (index > -1 && index < fileName.length() - 1) {
                return fileName.substring(index + 1);
            }
        }
        return null;
    }
}
