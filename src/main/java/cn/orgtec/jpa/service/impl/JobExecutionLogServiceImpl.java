package cn.orgtec.jpa.service.impl;

import cn.orgtec.jpa.dto.JobDto;
import cn.orgtec.jpa.entity.HostNameAndSuccess;
import cn.orgtec.jpa.entity.JobExecutionLogEntity;
import cn.orgtec.jpa.repository.JobExecutionLogRepository;
import cn.orgtec.jpa.service.JobExecutionLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * <p>JobExecutionLogServiceImpl.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/12 <p>
 * <p>@remark:</p>
 */
@Slf4j
@Service
@AllArgsConstructor
@Transactional(rollbackFor = RuntimeException.class)
public class JobExecutionLogServiceImpl implements JobExecutionLogService {

    private final JobExecutionLogRepository jobExecutionLogRepository;

    @Override
    public void saveJobLog(JobExecutionLogEntity form) {
        jobExecutionLogRepository.save(form);
    }

    @Override
    public void removeJobLog(String id) {
        jobExecutionLogRepository.deleteById(id);
    }

    @Override
    public JobExecutionLogEntity updateJobLog(JobExecutionLogEntity jobEntity) {
        return jobExecutionLogRepository.save(jobEntity);
    }

    @Override
    public JobExecutionLogEntity getByJobName(String jobName) {
        return jobExecutionLogRepository.findByJobName(jobName);
    }

    @Override
    public List<JobExecutionLogEntity> listJobLog() {
        return jobExecutionLogRepository.findAll();
    }

    @Override
    public Page<JobExecutionLogEntity> listLogsByJobNameLike(String jobName, Pageable pageable) {
//        模糊查询
        jobName = "%" + jobName + "%";
        Page<JobExecutionLogEntity> jobLogsPage = jobExecutionLogRepository.findByJobNameLike(jobName, pageable);
        return jobLogsPage;
    }

    /**
     * unless = "#result == null" 当方法返回空值时，就不会被缓存起来
     *
     * @param jobName
     * @param hostName
     * @return
     */
    @Override
    @Cacheable(value = "by_JobNameAndHostname", key = "#jobName", unless = "#result == null")
    public JobExecutionLogEntity findByJobNameAndHostname(String jobName, String hostName) {
        System.out.println("JobExecutionLogServiceImpl.findByJobNameAndHostname");
        return jobExecutionLogRepository.findByJobNameAndHostname(jobName, hostName);
    }

    @Override
    public Page<JobExecutionLogEntity> getAllJobLogsByPage() {
        return jobExecutionLogRepository.findAll(new PageRequest(1, 3, new Sort(new Sort.Order(Sort.Direction.ASC, "shardingItem"))));
    }

    @Override
    public Iterable<JobExecutionLogEntity> getAllJobLogsWithSort() {
        return jobExecutionLogRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "shardingItem")));
    }

    @Override
    public JobDto findJobById(String id) {
        Optional<JobExecutionLogEntity> byId = jobExecutionLogRepository.findById(id);
        System.err.println(byId.get().toString());
        return  byId.map(entity -> mapJobDto(entity)).orElse(null);
    }


    @Override
    public JobDto findByBufen( String id) {
        return jobExecutionLogRepository.findByBufen(id);
    }

    @Override
    public JobExecutionLogEntity findHostNameById(String id) {
        return jobExecutionLogRepository.findByBuFen(id);
    }

    @Override
    public Collection<HostNameAndSuccess> findByJobName(int jobName) {
        return jobExecutionLogRepository.findByJobName(jobName);
    }

    /**
     *  实体转换为dto
     * @return
     */
    private JobDto mapJobDto(JobExecutionLogEntity entity){
        JobDto jobDto = new JobDto();
        BeanUtils.copyProperties(entity , jobDto);
        return jobDto;
    }


}
