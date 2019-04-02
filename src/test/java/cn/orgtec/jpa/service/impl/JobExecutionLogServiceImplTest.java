package cn.orgtec.jpa.service.impl;

import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.system.SystemUtil;
import cn.orgtec.jpa.JpaApplication;
import cn.orgtec.jpa.dto.JobDto;
import cn.orgtec.jpa.entity.Account;
import cn.orgtec.jpa.entity.HostNameAndSuccess;
import cn.orgtec.jpa.entity.JobExecutionLogEntity;
import cn.orgtec.jpa.repository.AccountRepository;
import cn.orgtec.jpa.repository.JobExecutionLogRepository;
import cn.orgtec.jpa.service.JobExecutionLogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>JobExecutionLogServiceImplTest.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/12 <p>
 * <p>@remark:</p>
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JpaApplication.class, JobExecutionLogServiceImplTest.class})
public class JobExecutionLogServiceImplTest {

    @Autowired
    private JobExecutionLogService jobExecutionLogService;

    @Autowired
    private JobExecutionLogRepository jobExecutionLogRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findByIdAndUpdate(){
        Optional<JobExecutionLogEntity> oahp9e = jobExecutionLogRepository.findById("oahp9e");
        JobExecutionLogEntity entity = oahp9e.get();
        entity.setHostname("hix2");
        jobExecutionLogRepository.save(entity);
    }

    @Test
    public void findByHostNameInPage(){
        List<JobExecutionLogEntity> byHostNameInPage = jobExecutionLogService.findByHostNameInPage(null, null);
        System.out.println(byHostNameInPage);
    }

    @Test
    public void saveJobLog() throws Exception {
        JobExecutionLogEntity jobEntity = new JobExecutionLogEntity();
        jobEntity.setId(RandomUtil.randomString(6));
        jobEntity.setJobName(RandomUtil.randomString(6));
        jobEntity.setTaskId(RandomUtil.randomString(4));
        jobEntity.setHostname(SystemUtil.getUserInfo().getName());
        jobEntity.setIp(NetUtil.getLocalhostStr());
        jobEntity.setShardingItem(RandomUtil.randomNumber());
        jobEntity.setExecutionSource(RandomUtil.randomString(2));
        jobEntity.setFailureCause("OK");
        jobEntity.setIsSuccess(99);
        jobEntity.setStartTime(LocalDateTime.now());
        jobEntity.setCompleteTime(LocalDateTime.now());
        jobExecutionLogService.saveJobLog(jobEntity);
        log.info("*****************");
    }

    @Test
    public void removeJobLog() throws Exception {
        jobExecutionLogService.removeJobLog("zxphhn");
        log.info("*****************");
    }

    @Test
    public void updateJobLog() throws Exception {
        JobExecutionLogEntity jobEntity = new JobExecutionLogEntity();
        jobEntity.setId("tv7plb");
        jobEntity.setJobName(RandomUtil.randomString(6));
        jobEntity.setTaskId(RandomUtil.randomString(4));
        jobEntity.setHostname(SystemUtil.getUserInfo().getName());
        jobEntity.setIp(NetUtil.getLocalhostStr());
        jobEntity.setShardingItem(RandomUtil.randomNumber());
        jobEntity.setExecutionSource(RandomUtil.randomString(2));
        jobEntity.setFailureCause("OK");
        jobEntity.setIsSuccess(99);
        jobEntity.setStartTime(LocalDateTime.now());
        jobEntity.setCompleteTime(LocalDateTime.now());
        jobExecutionLogService.updateJobLog(jobEntity);
    }

    @Test
    public void getByJobName() throws Exception {
        System.out.println(jobExecutionLogService.getByJobName("0oo6ef"));
    }

    @Test
    public void listJobLog() {
        List<JobExecutionLogEntity> jobLogs = jobExecutionLogService.listJobLog();
        jobLogs.stream().forEach(System.out::println);
    }

    @Test
    public void listLogsByJobNameLike() {
        // 不走 count 总条数
        Pageable pageable = new PageRequest(0, 10);
        Page<JobExecutionLogEntity> pageJobLogs = jobExecutionLogService.listLogsByJobNameLike("a", pageable);
        System.out.println(pageJobLogs.getTotalElements());
        Stream<JobExecutionLogEntity> jobExecutionLogEntityStream = Optional.ofNullable(pageJobLogs).orElse(null).get();
        List<JobExecutionLogEntity> collect = jobExecutionLogEntityStream.collect(Collectors.toList());
        System.out.println(collect);
//        pageJobLogs.get().forEach(System.out::println);
    }


    @Test
    public void count() {
        Account entity = new Account();
        entity.setBankNumber("999");
        //  将匹配对象封装成Example对象
        Example<Account> of = Example.of(entity);
        long count = accountRepository.count(of);
        System.out.println("==========count==========" + count);
    }

    @Test
    public void findByJobNameAndHostname() {
        JobExecutionLogEntity administrator = jobExecutionLogService.findByJobNameAndHostname("0oo6ef", "Administrator");

//      Optional 类主要解决空指针异常(NullPointException) 可以使用 of() 或 ofNullable() 方法创建包含值 Optional ，两个方法的不同之处在于如果你把 null 值作为参数传递进去，of() 方法会抛出空指针；如果对象即可能是 null 也可能非 null，应该使用 ofNullable() 方法。
        //  错误用法
//        boolean present = Optional.ofNullable(administrator).isPresent();
//        if (present){
//            System.out.println(administrator.toString());
//        }
        String s = Optional.ofNullable(administrator).orElse(new JobExecutionLogEntity()).toString();
        System.out.println("Optional.ofNullable(administrator).toString() " + s);
        System.out.println("============================================");

        Optional.ofNullable(administrator).ifPresent(System.out::print);
        System.out.println("============================================");

        JobExecutionLogEntity jobExecutionLogEntity = Optional.ofNullable(administrator).orElse(new JobExecutionLogEntity());
        System.out.println("xxx: " + jobExecutionLogEntity.toString());
        System.out.println("============================================");

    }

    @Test
    public void getAllJobLogsByPage() {
        Page<JobExecutionLogEntity> allJobLogsByPage = jobExecutionLogService.getAllJobLogsByPage();
        Page<JobExecutionLogEntity> jobExecutionLogEntities = Optional.ofNullable(allJobLogsByPage).get();
        System.out.println("jobExecutionLogEntities.getTotalElements():" + jobExecutionLogEntities.getTotalElements());
        System.out.println("jobExecutionLogEntities.getTotalPages():" + jobExecutionLogEntities.getTotalPages());
        List<JobExecutionLogEntity> content = jobExecutionLogEntities.getContent();
        System.out.println("=====================================" +
                "");
        content.forEach(job -> System.out.println(job.toString()));
    }

    @Test
    public void getAllJobLogsWithSort() {
        Iterable<JobExecutionLogEntity> allJobLogsWithSort = jobExecutionLogService.getAllJobLogsWithSort();
        Optional<Iterable<JobExecutionLogEntity>> allJobLogsWithSort1 = Optional.ofNullable(allJobLogsWithSort);
        boolean present = allJobLogsWithSort1.isPresent();
        if (present) {
            Iterable<JobExecutionLogEntity> jobExecutionLogEntities = allJobLogsWithSort1.get();
            jobExecutionLogEntities.forEach(System.out::println);
        }
    }

    @Test
    public void findJobById(){
        JobDto jobById = jobExecutionLogService.findJobById("47d35m");
        System.out.println(jobById.toString());
    }

    @Test
    public void findBuFen(){
        JobDto byBufen = jobExecutionLogService.findByBufen("47d35m");
        System.out.println(byBufen.toString());
    }

    @Test
    public void findHostNameById(){
        jobExecutionLogService.findHostNameById("47d35m");
    }

    @Test
    public void fin(){
        Collection<HostNameAndSuccess> byJobName = jobExecutionLogService.findByJobName(99);
        System.out.println(byJobName.toString());
    }

    @Test
    public void buildSpecification(){
        Map map = new HashMap();
        map.put("jobName", "xw48ly");
        map.put("taskId", "ltt4");
        Specification specification = jobExecutionLogService.buildSpecification(map, false);
        System.out.println(specification.toString());
    }
}