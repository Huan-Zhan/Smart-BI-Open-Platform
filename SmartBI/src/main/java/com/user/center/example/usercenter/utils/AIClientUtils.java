package com.user.center.example.usercenter.utils;


import com.example.springboot_base_init.client.XingHuoAIClient;
import com.user.center.example.usercenter.model.ApiAnswer;
import com.user.center.example.usercenter.model.ChartByAiRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static cn.hutool.core.lang.Console.log;

@Slf4j
public class AIClientUtils {
    private XingHuoAIClient xingHuoAIClient ;
    private List<String> list;

    public AIClientUtils(XingHuoAIClient xingHuoAIClient) {
        if (list == null) list = new ArrayList<>();
        if (this.xingHuoAIClient == null) this.xingHuoAIClient = xingHuoAIClient;
        list.add("你是一个数据分析师和前端开发专家，接下来我会按照以下固定格式给你提供内容：\nAnalysis goal: {数据分析的需求或者目标}\nRaw data:\n{csv格式的原始数据，用,作为分隔符}\n\"\n请根据这两部分内容，按照以下指定格式用英文或中文生成内容，此外不要输出任何多余的开头、结尾、注释：\n\"\n【【【【【\n{前端Echarts V5的option配置对象的JSON代码，不能生成开头的 \" options = \"，而是生成options花括号里面的内容代码，不要生成options的\"title\"= \"chart title\"。以及不要生成任何多余的注释，解释，介\n绍，开头，结尾。生成的Echarts能够合理地将数据进行可视化}\n【【【【【\n{明确的数据分析结论、越详细越好，不要生成多余的注释}\n你明白你的职责么，如果明白请回复我 明白");
//        list.add("你是一个数据分析师和前端开发专家，接下来我会按照以下固定格式给你提供内容：\nAnalysis goal: {数据分析的需求或者目标}\nRaw data:\n{csv格式的原始数据，用,作为分隔符}\n\"\n请根据这两部分内容，按照以下指定格式用英文或中文生成内容，此外不要输出任何多余的开头、结尾、注释：\n\"\n{前端Echarts V5的option配置对象的JSON代码，不能生成开头的 \" options = \"，而是生成options花括号里面的内容代码，不要生成options的\"title\"= \"chart title\"。以及不要生成任何多余的注释，解释，介\n绍，开头，结尾。生成的Echarts能够合理地将数据进行可视化}\n{明确的数据分析结论、越详细越好，不要生成多余的注释}\n你明白你的职责么，如果明白请回复我 明白");
    }

    /**
     * 添加 prompt
     * @param prompt
     */
    public AIClientUtils(XingHuoAIClient xingHuoAIClient , String prompt){
        if (list == null) list = new ArrayList<>();
        if (this.xingHuoAIClient == null) this.xingHuoAIClient = xingHuoAIClient;
        list.clear();
        list.add(prompt);
    }

//    public static void main(String[] args) {
//        String str = "```json\n" +
//                "{\n" +
//                "    \"tooltip\": {\n" +
//                "        \"trigger\": \"axis\"\n" +
//                "    },\n" +
//                "    \"xAxis\": {\n" +
//                "        \"type\": \"category\",\n" +
//                "        \"data\": [\"1号\", \"2号\", \"3号\", \"4号\", \"5号\", \"6号\", \"7号\", \"8号\", \"9号\", \"10号\"]\n" +
//                "    },\n" +
//                "    \"yAxis\": {\n" +
//                "        \"type\": \"value\"\n" +
//                "    },\n" +
//                "    \"series\": [\n" +
//                "        {\n" +
//                "            \"name\": \"人数增长趋势\",\n" +
//                "            \"type\": \"line\",\n" +
//                "            \"data\": [82, 171, 258, 338, 393, 449, 469, 559, 650, 672]\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}\n" +
//                "```\n" +
//                "\n" +
//                "根据提供的数据，我们可以观察到人数的增长趋势。从1号到10号，人数呈现持续上升的趋势。在这段时间内，人数从82增加到672，增长了近8倍。特别是在4号之后，人数的增长速率明显加快，这可能与某些特定事件或因素有关。整体来看，人数增长趋势呈现出较为稳定的上升趋势。\n";
//        System.out.println("*************************************************");
//        System.out.println(getOption(str));
//        System.out.println("*************************************************");
//        System.out.println(getAnalysis(str));
//        System.out.println("*************************************************");
//    }

    public ApiAnswer Send(String requestData) {

        list.add(requestData);
        ApiAnswer apiAnswer = null ;
        try {
            String responseData = xingHuoAIClient.ConnectWithXingHuo(list);

            String myOptions  ;
            String myAnalysis ;
            log(responseData.toString());


            apiAnswer = SetAnswer(responseData);

        }catch (Exception e){
            log(e);
        }

        return apiAnswer ;

    }

    public static ApiAnswer SetAnswer(String responseData){
        int begin = 0  , end = 0 , start = 0 , finish = 0;
        ApiAnswer answer = new ApiAnswer();

        /***
         * 封装 Option
         */
        int watch = -1 ;
        for (int i = 0; i < responseData.length(); i++) {
            if (watch == 0){
                end = i ;
                break;
            }
            if ( watch==-1 && responseData.charAt(i) == '{' ){

                watch = 1 ;
                begin = i ;
//                System.out.println("{ "+watch);
                continue;
            }
            if (responseData.charAt(i) == '{') {

                watch++;
//                System.out.println("{ "+watch);
            }
            if (responseData.charAt(i) == '}') {

                watch--;
//                System.out.println("} "+watch);
            }
        }
        System.out.println();
        if (begin < end){
            answer.setMyOptions(responseData.substring(begin,end));
        }else {
            answer.setMyOptions("false");
        }
        /***
         * 封装 Analysis
         */
        for (int i = end+1 ; i <responseData.length() ; i++) {
            if (responseData.charAt(i)!='\n' && responseData.charAt(i)!='【' && responseData.charAt(i)!='】'&&responseData.charAt(i)!='`'){
                start = i ;
                break;
            }
        }

        for (int i = responseData.length(); i > start ; i--) {
            if (responseData.charAt(i-1)!='\n' && responseData.charAt(i-1)!='【' && responseData.charAt(i-1)!='】'&& responseData.charAt(i-1)!='`'){
                finish = i ;
                break;
            }
        }
        if (responseData.charAt(responseData.length()-1) == '\n'){
            finish = responseData.length()-1;
        }else {
            finish = responseData.length() ;
        }

        if (start < finish){
            answer.setMyAnalysis(responseData.substring(start,finish));
        }else {
            answer.setMyAnalysis("false");
        }

        return  answer ;
    }

    public static String genRequestData(String fileCsvData , ChartByAiRequest chartByAiRequest){
        fileCsvData = fileCsvData.substring(0,fileCsvData.length()-1);
        chartByAiRequest.check();
        String requestData ="Analysis goal: {"+"使用"+chartByAiRequest.getTableType()+","
                +chartByAiRequest.getAnalysisAim()+","
                +"图表名称为:"+chartByAiRequest.getTableName()+"}\n"
                +"{"+fileCsvData+"}";

        return requestData ;
    }






}
