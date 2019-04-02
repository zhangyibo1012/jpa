package cn.orgtec.jpa.service.impl;

import cn.orgtec.jpa.dto.JobDto;
import cn.orgtec.jpa.entity.HostNameAndSuccess;
import cn.orgtec.jpa.entity.JobExecutionLogEntity;
import cn.orgtec.jpa.repository.JobExecutionLogRepository;
import cn.orgtec.jpa.service.JobExecutionLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MultiMap;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.*;

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

    @Override
    public long count(String hostname) {
        JobExecutionLogEntity entity = new JobExecutionLogEntity();

        entity.setHostname("ADMIN");

    //  将匹配对象封装成Example对象
        final Example<JobExecutionLogEntity> example = Example.of(entity);
        long count = jobExecutionLogRepository.count(example);
        System.out.println("==========count==========" + count);
        return count;
    }

    /**
     * unless = "#result == null" 当方法返回空值时，就不会被缓存起来
     * value 缓存的名称
     * key 缓存的key 可以为空 如果指定按照Spel表达式编写 如果不指定 会有一个默认 按照方法参数
     * @param jobName
     * @param hostName
     * @return
     */
    @Override
    @Cacheable(value = "by_JobNameAndHostname", key = "#root.method.name", unless = "#result == null")
    public JobExecutionLogEntity findByJobNameAndHostname(String jobName, String hostName) {
        System.out.println("JobExecutionLogServiceImpl.findByJobNameAndHostname");
        return jobExecutionLogRepository.findByJobNameAndHostname(jobName, hostName);
    }

    @Override
    public List<JobExecutionLogEntity> findByHostNameInPage(Pageable pageable, String hostname) {
        JobExecutionLogEntity entity = new JobExecutionLogEntity();
        entity.setHostname("ADMIN");
        Example<JobExecutionLogEntity> example =Example.of(entity);
        Page<JobExecutionLogEntity> all = jobExecutionLogRepository.findAll(example, new PageRequest(1, 3));
        List<JobExecutionLogEntity> content = all.getContent();
        return all.getContent();
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

     public Specification<JobExecutionLogEntity> buildSpecification(Map<String, String> conditions, boolean isCounting) {

        Specification<JobExecutionLogEntity> specification =     (root , query , builder) ->{

            if (!isCounting){
                root.fetch("jobName", JoinType.LEFT).fetch("taskId" ,JoinType.LEFT);
//                root.fetch(MemberEntity::getUser, JoinType.LEFT).fetch(UserEntity.dynamic, JoinType.LEFT);
            }

            List<Predicate> predicates = new ArrayList<>();

//            if (conditions.containsKey(UserEntity.nickname)) {
//                predicates.add(builder.like(root.get(MemberEntity.user).get(UserEntity.nickname), "%" + conditions.getFirst(UserEntity.nickname) + "%"));
//            }


//            // 创建时间
//            if (conditions.containsKey(MemberEntity.expiry)) {
//
//                List<String> times = conditions.get(MemberEntity.expiry);
//                int size = times.size();
//                if (size > 0) {
//                    predicates.add(builder.greaterThan(root.get(MemberEntity.expiry).as(String.class), times.get(0)));
//                }
//                if (size > 1) {
//                    predicates.add(builder.lessThan(root.get(MemberEntity.expiry).as(String.class),  times.get(1)));
//                }
//            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return null;
    }


}
