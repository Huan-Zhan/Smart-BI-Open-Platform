package com.user.center.example.usercenter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot_base_init.client.XingHuoAIClient;
import com.user.center.example.usercenter.mapper.ChartsInformationMapper;
import com.user.center.example.usercenter.mapper.UsercenterMapper;

import com.user.center.example.usercenter.model.ApiAnswer;
import com.user.center.example.usercenter.model.Usercenter;
import com.user.center.example.usercenter.model.domain.ChartsInformation;
import com.user.center.example.usercenter.model.domain.User;
import com.user.center.example.usercenter.model.domain.Usercharts;
import com.user.center.example.usercenter.service.ChartsInformationService;
import com.user.center.example.usercenter.service.UserService;
import com.user.center.example.usercenter.service.UsercenterService;
import com.user.center.example.usercenter.service.UserchartsService;
import com.user.center.example.usercenter.utils.AIClientUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class MyBatisPlusTest {

    @Resource
    private UsercenterMapper usercenterMapper;
    @Autowired
    private UsercenterService usercenterService ;

    @Test
    public void testRegister() {
        /**
         * 测试 注册功能
         */

        int result = usercenterService.Register("Echo","asdhsgajas","asdhsgajas");

        Assert.isTrue((result != -1));
    }

    @Test
    public void testLogin() {
        /**
         * 测试 登录功能
         */
//        String userPassword = DigestUtils.md5DigestAsHex(("123456"+"Salt").getBytes());
//        System.out.println(userPassword);
        Usercenter result = usercenterService.Login("Echo","asdhsgajas");
        Assert.isTrue(result !=null);
    }

    @Test
    public void dist() {
        /**
         * 测试 登录功能
         */
        QueryWrapper<Usercenter> usercenterQueryWrapper = new QueryWrapper<>() ;
        usercenterQueryWrapper.eq("userName","Echo");

        final List<Usercenter> usercenters = usercenterMapper.selectList(usercenterQueryWrapper);

        System.out.println(usercenters.get(0).getUserPassword());
        String userPassword = DigestUtils.md5DigestAsHex(("asdhsgajas"+"huanzhan").getBytes());
        System.out.println(userPassword);

        Assert.isTrue(usercenters.get(0).getUserPassword().equals(userPassword));
    }

    @Resource
    ChartsInformationMapper chartsInformationMapper;
    @Test
    public void ChartInformationTest() throws IOException {
        String sql = "10000001" ;
        System.out.println(chartsInformationMapper.SelectUserChartsInformation(sql));

    }


    @Test
    public void CreateUserChartsInformation() throws IOException {
        String sql = "10000002" ;
        System.out.println(chartsInformationMapper.CreateUserChartsInformation(sql));

    }
    @Resource
    ChartsInformationService chartsInformationService;
    @Test
    public void ChartInformationServiceTest() throws IOException {
        String sql = "10000001" ;
        chartsInformationService.SelectUserChartsInformation(sql);
    }

    /**
     * 创建 用户表
     * @throws IOException
     */
    @Test
    public void CreateUserChartsInformationService() throws IOException {
        String sql = "10000001" ;
        chartsInformationService.CreateUserChartsInformation(sql);
    }


    @Resource
    XingHuoAIClient xingHuoAIClient;
    @Test
    public void TestAI() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("你是一个数据分析师和前端开发专家，接下来我会按照以下固定格式给你提供内容：\nAnalysis goal: {数据分析的需求或者目标}\nRaw data:\n{csv格式的原始数据，用,作为分隔符}\n\"\n请根据这两部分内容，按照以下指定格式用英文或中文生成内容，此外不要输出任何多余的开头、结尾、注释：\n\"\n【【【【【\n{前端Echarts V5的option配置对象的JSON代码，不能生成开头的 \" options = \"，而是生成options花括号里面的内容代码，不要生成options的\"title\"= \"chart title\"。以及不要生成任何多余的注释，解释，介\n绍，开头，结尾。生成的Echarts能够合理地将数据进行可视化}\n【【【【【\n{明确的数据分析结论、越详细越好，不要生成多余的注释}\n你明白你的职责么，如果明白请回复我 明白");
        list.add("你是一个数据分析师和前端开发专家，接下来我会按照以下固定格式给你提供内容：\nAnalysis goal: {数据分析的需求或者目标}\nRaw data:\n{csv格式的原始数据，用,作为分隔符}\n\"\n请根据这两部分内容，按照以下指定格式用英文或中文生成内容，此外不要输出任何多余的开头、结尾、注释：\n\"\n{前端Echarts V5的option配置对象的JSON代码，不能生成开头的 \" options = \"，而是生成options花括号里面的内容代码，不要生成options的\"title\"= \"chart title\"。以及不要生成任何多余的注释，解释，介\n绍，开头，结尾。生成的Echarts能够合理地将数据进行可视化}\n{明确的数据分析结论、越详细越好，不要生成多余的注释}\n你明白你的职责么，如果明白请回复我 明白");
        list.add("Analysis goal: {使用折线图分析人数增长趋势}\nRaw data:\n{日期,人数\n1号,82\n2号,171\n3号,258\n4号,338\n5号,393\n6号,449\n7号,469\n8号,559\n9号,650\n10号,672}");

        System.out.println(list.get(0));
        System.out.println();
        System.out.println(list.get(1));

        //        final String res = xingHuoAIClient.ConnectWithXingHuo(list);
//        System.out.println("here is AI answer");
//        System.out.println(res);
    }

    @Test
    public void  TestAiUtils() throws Exception {
        AIClientUtils aiClientUtils = new AIClientUtils(xingHuoAIClient);
        String str = "Analysis goal: {使用折线图分析人数增长趋势}\nRaw data:\n{日期,人数\n1号,82\n2号,171\n3号,258\n4号,338\n5号,393\n6号,449\n7号,469\n8号,559\n9号,650\n10号,672}";
        System.out.println(str);
        //        final ApiAnswer answer = aiClientUtils.Send(str);
//        System.out.println("*********************************");
//        System.out.println(answer.getMyOptions());
//        System.out.println("*********************************");
//        System.out.println(answer.getMyAnalysis());
//        System.out.println("*********************************");

    }

    @Test
    public void ResponseDataTest(){
        String str = "{\n" +
                "  \"tooltip\": {\n" +
                "    \"trigger\": \"axis\"\n" +
                "  },\n" +
                "  \"legend\": {\n" +
                "    \"data\": [\"网站人数\"]\n" +
                "  },\n" +
                "  \"xAxis\": {\n" +
                "    \"data\": [\"1号\", \"2号\", \"3号\", \"4号\", \"5号\", \"6号\", \"7号\", \"8号\", \"9号\", \"10号\"]\n" +
                "  },\n" +
                "  \"yAxis\": {},\n" +
                "  \"series\": [\n" +
                "    {\n" +
                "      \"name\": \"网站人数\",\n" +
                "      \"type\": \"line\",\n" +
                "      \"data\": [74, 114, 203, 246, 257, 213, 186, 270, 214, 160]\n" +
                "    }\n" +
                "  ]\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "根据提供的数据，从1号到10号，网站人数整体呈现波动增长的趋势。在3号和4号时，人数达到峰值，分别为203和246。之后在5号达到最高点257，然后开始逐渐下降，直到10号时人数降至160。这可能表明网站在初期吸引了较多用户，但随后出现了用户流失的情况。";

        ApiAnswer answer = AIClientUtils.SetAnswer(str);
        System.out.println("*************************************");
        System.out.println(answer.getMyOptions());
        System.out.println("*************************************");
        System.out.println(answer.getMyAnalysis());
        System.out.println("*************************************");
    }

    @Resource
    UserService userService;

    /**
     * 检查用户是否存在
     */
    @Test
    public void TestUserServiceCheckExist(){
        User user = new User();
        user.setUserAccount("admin");
        user.setUserPassword("123456789");
        System.out.println(userService.CheckUserExist(user).toString());
    }

    /**
     * 检查 ChartsInformation_UserId 是否存在
     */
    @Test
    public void TestChartsInformationIsExist(){
        System.out.println(chartsInformationMapper.CheckTablesIsExist("100000010"));

    }

    /**
     * 检测 ChartsInformation_UserId 插入功能是否正常
     */
//    @Test
//    public void TestChartsInformationInsert(){
//        for (int i = 0; i < 7; i++) {
//            // 以下是插入数据
//            {
//                System.out.println(chartsInformationMapper.InsertChartInformation(new ChartsInformation(10000002L, "周考次数,高等数学2\n" +
//                        "1,5.6\n" +
//                        "2,49.1\n" +
//                        "3,27.3\n" +
//                        "4,81.5\n" +
//                        "5,80.2\n" +
//                        "6,19.2\n" +
//                        "7,93.0\n" +
//                        "8,9.5\n" +
//                        "9,73.7\n" +
//                        "10,57.4\n" +
//                        "11,86.0\n" +
//                        "12,4.8\n" +
//                        "13,85.7\n" +
//                        "14,29.8\n" +
//                        "15,93.6\n" +
//                        "16,45.5\n" +
//                        "17,11.6\n" +
//                        "18,70.2\n" +
//                        "19,38.8\n" +
//                        "20,80.2\n" +
//                        "21,8.6\n" +
//                        "22,39.6\n" +
//                        "23,28.7\n" +
//                        "24,22.4\n" +
//                        "25,99.6\n" +
//                        "26,15.6\n" +
//                        "27,94.3\n" +
//                        "28,53.7\n" +
//                        "29,76.2\n" +
//                        "30,71.9\n" +
//                        "31,7.6\n" +
//                        "32,17.0\n" +
//                        "33,5.7\n" +
//                        "34,15.9\n" +
//                        "35,93.6\n" +
//                        "36,52.2\n" +
//                        "37,80.9\n" +
//                        "38,93.6\n" +
//                        "39,24.9\n" +
//                        "40,93.2\n" +
//                        "41,94.8\n" +
//                        "42,82.4\n" +
//                        "43,32.4\n" +
//                        "44,82.9\n" +
//                        "45,90.6\n" +
//                        "46,26.1\n" +
//                        "47,62.9\n" +
//                        "48,94.6\n" +
//                        "49,35.7\n" +
//                        "50,94.2\n" +
//                        "51,2.0\n" +
//                        "52,39.9\n" +
//                        "53,87.2\n" +
//                        "54,76.9\n" +
//                        "55,81.4\n" +
//                        "56,7.2\n" +
//                        "57,25.1\n" +
//                        "58,45.4\n" +
//                        "59,96.2\n" +
//                        "60,25.1\n", "{\n" +
//                        "    \"tooltip\": {\n" +
//                        "        \"trigger\": \"axis\"\n" +
//                        "    },\n" +
//                        "    \"legend\": {\n" +
//                        "        \"data\": [\"高数成绩\"]\n" +
//                        "    },\n" +
//                        "    \"xAxis\": {\n" +
//                        "        \"type\": \"category\",\n" +
//                        "        \"data\": [\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"10\",\"11\",\"12\",\"13\",\"14\",\"15\",\"16\",\"17\",\"18\",\"19\",\"20\",\"21\",\"22\",\"23\",\"24\",\"25\",\"26\",\"27\",\"28\",\"29\",\"30\",\"31\",\"32\",\"33\",\"34\",\"35\",\"36\",\"37\",\"38\",\"39\",\"40\",\"41\",\"42\",\"43\",\"44\",\"45\",\"46\",\"47\",\"48\",\"49\",\"50\",\"51\",\"52\",\"53\",\"54\",\"55\",\"56\",\"57\",\"58\",\"59\",\"60\"]\n" +
//                        "    },\n" +
//                        "    \"yAxis\": {\n" +
//                        "        \"type\": \"value\"\n" +
//                        "    },\n" +
//                        "    \"series\": [\n" +
//                        "        {\n" +
//                        "            \"name\": \"高数成绩\",\n" +
//                        "            \"type\": \"line\",\n" +
//                        "            \"data\": [5.6,49.1,27.3,81.5,80.2,19.2,93.0,9.5,73.7,57.4,86.0,4.8,85.7,29.8,93.6,45.5,11.6,70.2,38.8,80.2,8.6,39.6,28.7,22.4,99.6,15.6,94.3,53.7,76.2,71.9,7.6,17.0,5.7,15.9,93.6,52.2,80.9,93.6,24.9,93.2,94.8,82.4,32.4,82.9,90.6,26.1,62.9,94.6,35.7,94.2,2.0,39.9,87.2,76.9,81.4,7.2,25.1,45.4,96.2,25.1]\n" +
//                        "        }\n" +
//                        "    ]\n" +
//                        "}")));
//            }
//        }
//    }

    /**
     *  检测向 UserCharts 表中插入数据
     */
    @Resource
    UserchartsService userchartsService;
    @Test
    public void TestUserChartsInsert() throws InterruptedException {
        Usercharts usercharts = new Usercharts("OnlyTest","10000002","高数成绩表","分析成绩变化趋势","折线图");
            userchartsService.save(usercharts);
    }
    @Test
    public void PrintPrompt(){
        System.out.println("你是一个数据分析师和前端开发专家，接下来我会按照以下固定格式给你提供内容：\nAnalysis goal: {数据分析的需求或者目标}\nRaw data:\n{csv格式的原始数据，用,作为分隔符}\n\"\n请根据这两部分内容，按照以下指定格式用英文或中文生成内容，此外不要输出任何多余的开头、结尾、注释：\n\"\n【【【【【\n{前端Echarts V5的option配置对象的JSON代码，不能生成开头的 \" options = \"，而是生成options花括号里面的内容代码，不要生成options的\"title\"= \"chart title\"。以及不要生成任何多余的注释，解释，介\n绍，开头，结尾。生成的Echarts能够合理地将数据进行可视化}\n【【【【【\n{明确的数据分析结论、越详细越好，不要生成多余的注释}\n你明白你的职责么，如果明白请回复我 明白");
    }

}
