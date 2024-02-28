package com.user.center.example.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot_base_init.client.XingHuoAIClient;
import com.user.center.example.usercenter.model.*;
import com.user.center.example.usercenter.model.domain.Apicenter;
import com.user.center.example.usercenter.model.domain.ChartsInformation;
import com.user.center.example.usercenter.mq.BIClient;
import com.user.center.example.usercenter.service.*;

import com.user.center.example.usercenter.utils.AIClientUtils;
import com.user.center.example.usercenter.utils.ExcelUtils;
import com.user.center.example.usercenter.utils.ResponseUtils.BaseResponse;
import com.user.center.example.usercenter.utils.ResponseUtils.ResultUtils;
import com.yupi.yucongming.dev.client.YuCongMingClient;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;


@RestController
@RequestMapping("/user")
public class MyUserController {
    @Resource
    private  UsercenterService usercenterService ;
    @Resource
    private YuCongMingClient ApiClient ;
    @Resource
    private ApicenterService userService ;

    @Resource
    XingHuoAIClient xingHuoAIClient ;
    @Resource
    ChartsInformationService chartsInformationService;
    @Resource
    ThreadPoolExecutor threadPoolExecutor ;
    @Resource
    BIClient biClient ;

    @RequestMapping("/sleep")
    public String Sleep(String str) throws InterruptedException {

        System.out.println(str);
        System.out.println("sleep");
        Thread.sleep(5000);

        return "sleep" ;
    }

    @RequestMapping("/uploadFile1")
    public String upLoadFile(@RequestPart("file") MultipartFile multipartFile) {
//@RequestPart("file")
        System.out.println(multipartFile.toString());
        System.out.println("uploadFile");
        String FileData = ExcelUtils.toCSV(multipartFile).toString();

        System.out.println(FileData);

        return "uploadFile" ;
    }

    @RequestMapping("/hello")
    public String Hello(@RequestBody DeleteDataInformation data, Apicenter user){

        System.out.println(data.toString());
        System.out.println(user.toString());

        return "hello" ;
    }

    /**
     * 登录页
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse Login(@RequestBody userLogin user, HttpServletRequest request){
        // , HttpServletRequest request
        // 校验数据
        System.out.println("here is login");

        if(user == null ||
                user.getUserPassword().equals("") ||
                user.getUserPassword()==null ||
                user.getUserName().equals("") ||
                user.getUserName()==null
        ){
            return ResultUtils.Error();
        }
        Apicenter safetyUser = new Apicenter();
        safetyUser.setUserName(user.getUserName());
        safetyUser.setUserPassword(user.getUserPassword());



//        if (request == null) return safetyUser ;

        if (userService.CheckUserExist(safetyUser) == null){
            return ResultUtils.Error();
        }
        //todo 待优化

        return ResultUtils.Success("success");
    }
    
    /**
     * 注册页
     * @param user
     * @return
     */
    @PostMapping("/register")
    public  BaseResponse Register(@RequestBody userRegister user){
        // 校验数据

        System.out.println("here is register");
        if(user == null || user.getUserPassword()==null || user.getUserName()==null || user.getCheckPassword() == null){
            return ResultUtils.Error() ;
        }
        if (!user.getUserPassword().equals(user.getCheckPassword())) return ResultUtils.Error();

        /**
         * 写入数据库
         */
        switch (userService.UserRegister(user)){
            case 0 : return ResultUtils.Error("确认密码失败");
            case 1 : return ResultUtils.Error("账户长度应在4-20之间");
            case 2 : return ResultUtils.Error("密码长度应在6-20之间");
            case 3 : return ResultUtils.Error("账号或密码存在违规字符");
            case 4 : return ResultUtils.Error("用户名重复");
            case 5 : return ResultUtils.Error("用户保存失败");
            case 6 : return ResultUtils.Success("success") ;
            default: return ResultUtils.Error("未知错误");
        }
    }


    /**
     * 同步生成图标
     * @param multipartFile
     * @param chartByAiRequest
     * @param user
     * @return
     */
    @PostMapping("/ApiUploadFile")
    public BaseResponse ApiUploadFile(@RequestPart("file")MultipartFile multipartFile , ChartByAiRequest chartByAiRequest , Apicenter user){

        System.out.println("here is ApiUploadFile");
        String goal = chartByAiRequest.getAnalysisAim();
        String name = chartByAiRequest.getTableName();
        String charType = chartByAiRequest.getTableType();
        String fileData ;
        String requestData ;
        if (goal == null || name == null || charType == null) return ResultUtils.Error();

        Apicenter apicenter= userService.CheckUserExist(user);
        if (apicenter == null) return ResultUtils.Error();

//        System.out.println(goal+" " + name + " " +charType);

        fileData =  ExcelUtils.toCSV(multipartFile).toString();

//        System.out.println(fileData);

        requestData = AIClientUtils.genRequestData(fileData,chartByAiRequest);

        System.out.println(requestData.toString());

        AIClientUtils aiClientUtils = new AIClientUtils(xingHuoAIClient);
        final ApiAnswer answer = aiClientUtils.Send(requestData);

        /**
         * 将查询的数据 和 api 相应数据 存入数据库
         */

//        userchartsService.save(new Usercharts(list.get(0),list.get(1),name,goal,charType));
        final boolean isExist = chartsInformationService.CheckTablesIsExist(apicenter.getID().toString());

        if (isExist){
            // 表格存在
            chartsInformationService.InsertChartInformationOver(
                    new ChartsInformation(apicenter.getUserName(), apicenter.getID(), fileData,name,goal,charType,answer.getMyOptions(),answer.getMyAnalysis(),1));
        }else {
            // 表格不存在
            chartsInformationService.CreateUserChartsInformation(apicenter.getID().toString());
            chartsInformationService.InsertChartInformationOver(
                    new ChartsInformation(apicenter.getUserName(),apicenter.getID(),fileData,name,goal,charType,answer.getMyOptions(),answer.getMyAnalysis(),1));
        }


//        System.out.println("******************************************************");
//        System.out.println(answer.getMyOptions());
//        System.out.println("******************************************************");
//        System.out.println(answer.getMyAnalysis());
//        System.out.println("******************************************************");


        return ResultUtils.Success(answer);
    }

    /**
     * 异步化生成图表
     * @param multipartFile 文件
     * @param chartByAiRequest 需求描述
     * @param user 用户信息
     * @return 生成状态
     */
    @PostMapping("/uploadFile")
    public BaseResponse UploadFile(@RequestPart("file")MultipartFile multipartFile , ChartByAiRequest chartByAiRequest ,Apicenter user){

        System.out.println("here is uploadFile");
        /**
         * 判空
         */
        String goal = chartByAiRequest.getAnalysisAim();
        String name = chartByAiRequest.getTableName();
        String charType = chartByAiRequest.getTableType();
        String fileData ;
        if (goal == null || name == null || charType == null) return ResultUtils.Error();

        Apicenter list = userService.CheckUserExist(user);
        if (list == null) return ResultUtils.Error();


        fileData =  ExcelUtils.toCSV(multipartFile).toString();


        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                /**
                 *  准备调用 AI
                 *  和调用AI
                 */
                String requestData = AIClientUtils.genRequestData(fileData,chartByAiRequest);

                System.out.println(requestData.toString());

                final boolean isExist = chartsInformationService.CheckTablesIsExist(list.getID().toString());
                ChartsInformation chartsInformation =  new ChartsInformation(list.getUserName(),list.getID(),fileData,name,goal,charType,"","");

                if (isExist){
                    // 表格存在
                    chartsInformationService.InsertChartInformation(chartsInformation);
                }else {
                    // 表格不存在
                    chartsInformationService.CreateUserChartsInformation(list.getID().toString());
                    chartsInformationService.InsertChartInformation(chartsInformation);
                }

                //todo
                Long id = (long)chartsInformationService.CountAll(list.getID().toString());
                biClient.SendMessage(
                        new BIMessage(id,fileData,requestData,
                                list.getID().toString(),list.getUserName(),name,goal,charType)
                );

//                AIClientUtils aiClientUtils = new AIClientUtils(xingHuoAIClient);
//                final ApiAnswer answer = aiClientUtils.Send(requestData);
//                /**
//                 * 将查询的数据 和 api 相应数据 存入数据库
//                 */
//                final boolean isExist = chartsInformationService.CheckTablesIsExist(list.get(1));
//
//                if (isExist){
//                    // 表格存在
//                    chartsInformationService.InsertChartInformation(
//                            new ChartsInformation(list.get(0),Long.parseLong(list.get(1)),fileData,name,goal,charType,answer.getMyOptions(),answer.getMyAnalysis()));
//                }else {
//                    // 表格不存在
//                    chartsInformationService.CreateUserChartsInformation(list.get(1));
//                    chartsInformationService.InsertChartInformation(
//                            new ChartsInformation(list.get(0),Long.parseLong(list.get(1)),fileData,name,goal,charType,answer.getMyOptions(),answer.getMyAnalysis()));
//                }

            }
        },threadPoolExecutor);


        ApiAnswer answer = new ApiAnswer();


        return ResultUtils.Success(answer);
    }

    @PostMapping("getHistoryCharts")
    public BaseResponse GetHistoryCharts(@RequestBody Apicenter user){
        System.out.println("here is getHistoryCharts");

        if (user == null || user.getUserName() == null || user.getUserPassword()==null) return ResultUtils.Error();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("UserName",user.getUserName());

        Apicenter CompleteUser = userService.CheckUserExist(user);
//        System.out.println(user.toString());
        if (!CompleteUser.getUserPassword().equals(user.getUserPassword())) return ResultUtils.Error();
        if (!chartsInformationService.CheckTablesIsExist(CompleteUser.getID().toString()))return ResultUtils.Error();

        queryWrapper.clear();
        queryWrapper.eq("userId",CompleteUser.getID());

        /**
         * 1.获得 ChartsInformation_UserId 表数据 【检测表是否存在】
         */

        final List<ChartsInformation> list = chartsInformationService.SelectAllList(CompleteUser.getID().toString());

        System.out.println(list.toString());
        /**
         * 实现按时间顺序 倒序排列
         */
        Collections.sort(list, new Comparator<ChartsInformation>() {
            @Override
            public int compare(ChartsInformation o1, ChartsInformation o2) {
                return o2.getCreateDate().compareTo(o1.getCreateDate());
            }
        });


        return ResultUtils.Success(list);
    }

    /**
     *
     */
    @PostMapping("deleteById")
    public BaseResponse DeleteById(@RequestBody DeleteDataInformation data){

        if (data ==null || data.getDataId() == null || data.getUserPassword() == null || data.getUserName() == null) return ResultUtils.Error();

        Apicenter user= new Apicenter();
        user.setUserName(data.getUserName());
        user.setUserPassword(data.getUserPassword());

        Apicenter apicenter = userService.CheckUserExist(user);

        if (apicenter == null) return ResultUtils.Error();

        /**
         * 判断表格是否cunzai
         */

        if (chartsInformationService.CheckTablesIsExist(apicenter.getID().toString())){
            chartsInformationService.DeleteById(apicenter.getID(),data.getDataId());
        }else {
            return ResultUtils.Error();
        }

        return ResultUtils.Success("deleteSuccess");
    }



    /**
     * 模拟数据接口，节省API接口调用次数
     * @param multipartFile
     * @param chartByAiRequest
     * @return
     */
    @PostMapping("MockData")
    public BaseResponse MockData(@RequestPart("file")MultipartFile multipartFile , ChartByAiRequest chartByAiRequest ,Apicenter user){
        System.out.println("here is mock");
        String option = "{\n" +
                "    \"tooltip\": {\n" +
                "        \"trigger\": \"axis\"\n" +
                "    },\n" +
                "    \"legend\": {\n" +
                "        \"data\": [\"高数成绩\"]\n" +
                "    },\n" +
                "    \"xAxis\": {\n" +
                "        \"type\": \"category\",\n" +
                "        \"data\": [\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"10\",\"11\",\"12\",\"13\",\"14\",\"15\",\"16\",\"17\",\"18\",\"19\",\"20\",\"21\",\"22\",\"23\",\"24\",\"25\",\"26\",\"27\",\"28\",\"29\",\"30\",\"31\",\"32\",\"33\",\"34\",\"35\",\"36\",\"37\",\"38\",\"39\",\"40\",\"41\",\"42\",\"43\",\"44\",\"45\",\"46\",\"47\",\"48\",\"49\",\"50\",\"51\",\"52\",\"53\",\"54\",\"55\",\"56\",\"57\",\"58\",\"59\",\"60\"]\n" +
                "    },\n" +
                "    \"yAxis\": {\n" +
                "        \"type\": \"value\"\n" +
                "    },\n" +
                "    \"series\": [\n" +
                "        {\n" +
                "            \"name\": \"高数成绩\",\n" +
                "            \"type\": \"line\",\n" +
                "            \"data\": [5.6,49.1,27.3,81.5,80.2,19.2,93.0,9.5,73.7,57.4,86.0,4.8,85.7,29.8,93.6,45.5,11.6,70.2,38.8,80.2,8.6,39.6,28.7,22.4,99.6,15.6,94.3,53.7,76.2,71.9,7.6,17.0,5.7,15.9,93.6,52.2,80.9,93.6,24.9,93.2,94.8,82.4,32.4,82.9,90.6,26.1,62.9,94.6,35.7,94.2,2.0,39.9,87.2,76.9,81.4,7.2,25.1,45.4,96.2,25.1]\n" +
                "        }\n" +
                "    ]\n" +
                "}" ;
        String analysis="从生成的折线图中可以看出，学生的高数成绩波动较大，但整体上呈现出一定的上升趋势。尤其是在第7、15、25、35、59次周考中，学生的成绩达到了93分以上，表现非常优秀。然而，也存在一些成绩较低的周考，如第6、12、31、33、52、56、58次周考，学生的成绩均在20分以下，需要引起关注。此外，从整体趋势来看，学生的成绩在后期有所提升，但仍有波动，建议学生和教师针对成绩波动较大的情况进行分析和调整，以便更好地提高学生的学习效果。";
        ApiAnswer answer = new ApiAnswer(option,analysis);
        System.out.println("****************************************************");
        System.out.println(option);
        System.out.println("****************************************************");
        System.out.println(analysis);
        System.out.println("****************************************************");

        Apicenter list = userService.CheckUserExist(user);
        if (list == null) return ResultUtils.Error();

        /**
         * 将查询的数据 和 api 相应数据 存入数据库
         *
         * !!! 暂时不存
         */

//        String goal = chartByAiRequest.getAnalysisAim();
//        String name = chartByAiRequest.getTableName();
//        String charType = chartByAiRequest.getTableType();
//        final boolean isExist = chartsInformationService.CheckTablesIsExist(list.get(1));
//        String fileData =  ExcelUtils.toCSV(multipartFile).toString();
//        final boolean AddisExist = chartsInformationService.CheckTablesIsExist("10000001");
//        if (AddisExist){
//            // 表格存在
//            chartsInformationService.InsertChartInformation(
//                    new ChartsInformation("admin",10000001L,fileData,name,goal,charType,answer.getMyOptions(),answer.getMyAnalysis()));
//        }else {
//            // 表格不存在
//            chartsInformationService.CreateUserChartsInformation(list.get(1));
//            chartsInformationService.InsertChartInformation(
//                    new ChartsInformation("admin",10000001L,fileData,name,goal,charType,answer.getMyOptions(),answer.getMyAnalysis()));
//        }

        return ResultUtils.Success(answer);
    }



}
