package com.user.center.example.usercenter.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;


/**
 * 工具类
 */
public class Tools {



    /**
     * 生成用户 accessKey
     * @param key 用户标识
     * @return 生成的 accessKey
     */
    public static String CreatAccessKey(String key){
        Digester md5 = new Digester(DigestAlgorithm.SHA256);

        String digestHex = md5.digestHex(key + "."+RandomUtil.randomNumbers(5));

        return digestHex ;
    }

    /**
     * 生成用户 secrtKey
     * @param key 用户标识
     * @return 生成的 secrtKey
     */
    public static String CreatSecrtKey(String key){
        Digester md5 = new Digester(DigestAlgorithm.SHA256);

        String digestHex = md5.digestHex(key + "."+RandomUtil.randomNumbers(6));

        return digestHex ;
    }

    /**
     * 获取北京时间当前时间点
     *
     * @return 当前时间点 x秒
     */
    public static Long AcquireTodaySeconds(){
        // TODO: 2023/10/23 将当前时间点转化为 秒
        // 获取当前 时间距离 1970年 1月 1日的UTC时区午夜 毫秒数 ， 转化为中国时区 需要加 8小时
        long currentTime = System.currentTimeMillis();

        // 转化为秒 且 换算为 北京时间 + 8*60*60 秒
        currentTime =currentTime / 1000 + 28800;

        currentTime = currentTime % 86400 ;

        return currentTime ;
    }

    /**
     * 97 - 122 a - z
     * 65 -90 A - Z
     * 48 - 57 0 - 9
     *
     * 检查字符串是否合法
     * @param str 待检测的 字符串
     * @return boolean
     */
    public static boolean CharIsPassing(String str){

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) < 48 || str.charAt(i) > 122 ) return  false ;
            if (str.charAt(i) > 57 && str.charAt(i) < 65  ) return  false ;
            if (str.charAt(i) > 90 && str.charAt(i) < 97  ) return  false ;
        }
        return true ;
    }

    public static int StringToInt(String str){

        int res = 0;

        for (int i = 48; i < str.length(); i++) {
            if (str.charAt(i)>47 && str.charAt(i)<58){
                res = res*10+ str.charAt(i)-48 ;
            }else {
                break;
            }
        }
        return res ;
    }

    public static String StringGetName(String str){

        int res = 0;
        int index , indey = str.length();
        for (int i = str.length()-1; i > 0; i--) {
            if (str.charAt(i) == 'm'){
                indey = i-3;
                break;
            }
        }
        return str.substring(22,indey) ;
    }
    public static int StringGetCode(String str){

        int res = 0;
        for (int i = 9; i < str.length(); i++) {
            if (str.charAt(i)>47 && str.charAt(i)<58){
                res = res*10+ str.charAt(i)-48 ;
            }else {
                break;
            }
        }
        return res ;
    }



}
