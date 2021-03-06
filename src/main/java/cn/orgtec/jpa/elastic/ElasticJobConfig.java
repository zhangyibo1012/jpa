//package cn.orgtec.jpa.elastic;
//
//import com.dangdang.ddframe.job.api.simple.SimpleJob;
//import com.dangdang.ddframe.job.config.JobCoreConfiguration;
//import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
//import com.dangdang.ddframe.job.lite.api.JobScheduler;
//import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
//import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
//import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * <p>ElasticJobConfig.java此类用于</p>
// * <p>@author zyb <p>
// * <p>@date 2019/03/19 <p>
// * <p>@remark:</p>
// */
//@Configuration
//@AllArgsConstructor
//public class ElasticJobConfig {
//
//    private ZookeeperRegistryCenter regCenter;
//
//    @Bean(initMethod = "init")
//    public ZookeeperRegistryCenter regCenter(@Value("${elaticjob.zookeeper.server-lists}") final String serverList, @Value("${elaticjob.zookeeper.namespace}") final String namespace) {
//        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
//    }
//
//    /**
//     * 动态添加
//     *
//     * @param jobClass
//     * @param cron
//     * @param shardingTotalCount
//     * @param shardingItemParameters
//     */
//    public void addSimpleJobScheduler(final Class<? extends SimpleJob> jobClass,
//                                      final String cron,
//                                      final int shardingTotalCount,
//                                      final String shardingItemParameters) {
//        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).jobParameter("job参数").build();
//        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig, jobClass.getCanonicalName());
//        JobScheduler jobScheduler = new JobScheduler(regCenter, LiteJobConfiguration.newBuilder(simpleJobConfig).build());
//        jobScheduler.init();
//    }
//}
