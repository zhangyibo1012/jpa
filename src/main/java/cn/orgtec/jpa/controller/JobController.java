package cn.orgtec.jpa.controller;

import cn.orgtec.jpa.entity.JobExecutionLogEntity;
import cn.orgtec.jpa.service.JobExecutionLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * <p>JobController.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/19 <p>
 * <p>@remark:</p>
 */
@RestController
@AllArgsConstructor
public class JobController {

    private final JobExecutionLogService jobService;
//    private final ElasticJobConfig elasticJobConfig;

    @GetMapping(value = "/findByJobNameAndHostname")
    public String findByJobNameAndHostname() {
        JobExecutionLogEntity administrator = jobService.findByJobNameAndHostname("0oo6ef", "Administrator1");
        return Optional.ofNullable(administrator).orElse(new JobExecutionLogEntity()).toString();
    }


    @PostMapping(value = "/post")
    public String test(@RequestParam  String parm){
        // @RequestParam 不可以post接收参数 @Requestbody可以
        System.out.println(parm);
        return  parm;
    }
//    @RequestMapping(value = "/addJob")
//    public void addJob() {
//        int shardingTotalCount = 2;
//        elasticJobConfig.addSimpleJobScheduler(new MySimpleJob().getClass(), "* * * * * ?", shardingTotalCount, "0=Z,1=H");
//    }

}
