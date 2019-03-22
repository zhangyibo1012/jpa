package cn.orgtec.jpa.web;

import cn.orgtec.jpa.entity.JobExecutionLogEntity;
import cn.orgtec.jpa.repository.JobExecutionLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>TestController.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/15 <p>
 * <p>@remark:</p>
 */
@RestController
@AllArgsConstructor
public class TestController {

    private final JobExecutionLogRepository jobLogRepository;

    @GetMapping(value = "/page")
    @Cacheable(value = "job")
    public Page<JobExecutionLogEntity> findAllByPage(@PageableDefault(page = 2, size = 1) Pageable pageable) {
        System.out.println("进入方法");
        return jobLogRepository.findAll(pageable);
    }

    @GetMapping(value = "/sort")
    public List<JobExecutionLogEntity> findAllBySort(Sort sort) {
        return jobLogRepository.findAll(sort);
    }

}
