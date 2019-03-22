//package cn.orgtec.jpa.elastic;
//
//import com.dangdang.ddframe.job.api.ShardingContext;
//import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * <p>MySimpleJob.java此类用于</p>
// * <p>@author zyb <p>
// * <p>@date 2019/03/19 <p>
// * <p>@remark:</p>
// */
//@ElasticSimpleJob(cron = "* * * * * ?", jobName = "test123", shardingTotalCount = 2, jobParameter = "测试参数", shardingItemParameters = "0=A,1=B")
//@Component
//public class MySimpleJob implements com.dangdang.ddframe.job.api.simple.SimpleJob {
//
//    @Value("${server.port}")
//    private String port;
//
//    @Override
//    public void execute(ShardingContext shardingContext) {
//
//
//        System.out.println(String.format(port + "------Thread ID: %s, 任务总片数: %s, " +
//                        "当前分片项: %s.当前参数: %s," +
//                        "当前任务名称: %s.当前任务参数: %s"
//                ,
//                Thread.currentThread().getId(),
//                shardingContext.getShardingTotalCount(),
//                shardingContext.getShardingItem(),
//                shardingContext.getShardingParameter(),
//                shardingContext.getJobName(),
//                shardingContext.getJobParameter()
//        ));
//
//    }
//}
