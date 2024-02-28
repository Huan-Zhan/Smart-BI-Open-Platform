package com.user.center.example.usercenter.mq;

import cn.hutool.socket.aio.AioClient;
import com.example.springboot_base_init.client.XingHuoAIClient;
import com.user.center.example.usercenter.model.ApiAnswer;
import com.user.center.example.usercenter.model.BIMessage;
import com.user.center.example.usercenter.model.domain.ChartsInformation;
import com.user.center.example.usercenter.service.ChartsInformationService;
import com.user.center.example.usercenter.utils.AIClientUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;


@Component
public class BIConsumer {

    @Resource
    ThreadPoolExecutor threadPoolExecutor ;
    @Resource
    RabbitTemplate rabbitTemplate ;
    @Resource
    XingHuoAIClient xingHuoAIClient ;
    @Resource
    ChartsInformationService chartsInformationService;

    @RabbitListener(queues = {MqUtils.MQ_QUEUE_NAME_FIRST_HOST})
    public void getMessage(BIMessage message){
        // 检测队列是否有空闲

        while(threadPoolExecutor.getQueue().remainingCapacity() == 0 && threadPoolExecutor.getPoolSize() == threadPoolExecutor.getMaximumPoolSize()){
            // 不空闲
            // 执行拒绝策略
            // 休眠 1s

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 空闲
        CompletableFuture.runAsync(()->{
            // todo
            /**
             * 异步化 主函数部分
             * 计划 调用一个 外部函数 解决一切
             */
            System.out.println("异步化操作已经接收到数据，正在全力分析");
                AIClientUtils aiClientUtils = new AIClientUtils(xingHuoAIClient);
                final ApiAnswer answer = aiClientUtils.Send(message.getRequestData());
                /**
                 * todo
                 * 将查询的数据 和 api 相应数据 存入数据库(修改)
                 */
//                final boolean isExist = chartsInformationService.CheckTablesIsExist(message.getUserId());


                    // 表格存在
            chartsInformationService.UpdateChartsInformationWhenOver(new ChartsInformation(message.getId(),Long.parseLong(message.getUserId()),answer.getMyAnalysis(),answer.getMyOptions(),1));
            System.out.println("分析完毕");
//                    chartsInformationService.InsertChartInformationOver(
//                            new ChartsInformation(message.getUserName(),Long.parseLong(message.getUserId()),message.getFileData(),message.getChartName(),message.getChartAnalysisAim(),message.getChartType(),answer.getMyOptions(),answer.getMyAnalysis(),1));


                    // 表格不存在
//                    chartsInformationService.CreateUserChartsInformation(message.getUserId());
//                    chartsInformationService.InsertChartInformationOver(
//                            new ChartsInformation(message.getUserName(),Long.parseLong(message.getUserId()),message.getFileData(),message.getChartName(),message.getChartAnalysisAim(),message.getChartType(),answer.getMyOptions(),answer.getMyAnalysis(),1));


        },threadPoolExecutor);


    }

}
