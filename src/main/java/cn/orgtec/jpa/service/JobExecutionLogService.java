package cn.orgtec.jpa.service;

import cn.orgtec.jpa.dto.JobDto;
import cn.orgtec.jpa.entity.HostNameAndSuccess;
import cn.orgtec.jpa.entity.JobExecutionLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>JobExecutionLogService.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/12 <p>
 * <p>@remark:</p>
 */
public interface JobExecutionLogService {

    /**
     * 保存定时任务日志
     *
     * @param form 入参
     */
    void saveJobLog(JobExecutionLogEntity form);

    /**
     * 根据任务id删除定时任务日志
     *
     * @param id 任务主键
     */
    void removeJobLog(String id);

    /**
     * 修改定时任务日志
     *
     * @param jobEntity 任务实体
     */
    JobExecutionLogEntity updateJobLog(JobExecutionLogEntity jobEntity);

    /**
     * 根据jobName查询具体信息
     *
     * @param jobName 任务名称
     */
    JobExecutionLogEntity getByJobName(String jobName);

    /**
     * @return 获取任务列表
     */
    List<JobExecutionLogEntity> listJobLog();

    /**
     * 根据任务名称进行分页模糊查询
     *
     * @param jobName  任务名称
     * @param pageable 分页
     * @return 分页数据
     */
    Page<JobExecutionLogEntity> listLogsByJobNameLike(String jobName, Pageable pageable);

    long count(String hostname);
    //======================================================================================

    /**
     * 根据任务名称和主机hostname查询任务信息
     *
     * @param jobName
     * @param hostName
     */
    JobExecutionLogEntity findByJobNameAndHostname(String jobName, String hostName);

    List<JobExecutionLogEntity> findByHostNameInPage(Pageable pageable , String hostname);

    /**
     * 验证排序和分页查询
     *
     * @return
     */
    Page<JobExecutionLogEntity> getAllJobLogsByPage();

    /**
     * 排序查询方法
     *
     * @return
     */
    Iterable<JobExecutionLogEntity> getAllJobLogsWithSort();

    /**
     *  返回 dto
     */
    JobDto findJobById(String id);

    JobDto findByBufen( String id);

    JobExecutionLogEntity findHostNameById(String id);

    Collection<HostNameAndSuccess> findByJobName(int jobName);

     Specification<JobExecutionLogEntity> buildSpecification(Map<String, String> conditions, boolean isCounting);
}
