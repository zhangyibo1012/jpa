package cn.orgtec.jpa.repository;

import cn.orgtec.jpa.dto.JobDto;
import cn.orgtec.jpa.entity.HostNameAndSuccess;
import cn.orgtec.jpa.entity.JobExecutionLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * <p>JobExecutionLogRepository.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/12 <p>
 * <p>@remark:</p>
 */
@Repository
public interface JobExecutionLogRepository extends JpaRepository<JobExecutionLogEntity, String>, JpaSpecificationExecutor<JobExecutionLogEntity> {

    /**
     * 根据任务名称查询信息
     *
     * @param jobName 任务名称
     * @return 实体
     */
    JobExecutionLogEntity findByJobName(String jobName);

    /**
     * 根据用户名分页查询用户列表
     *
     * @param name
     * @param pageable
     */
    Page<JobExecutionLogEntity> findByJobNameLike(String name, Pageable pageable);

    // =================================================================================

    /**
     * 根据任务名称和主机hostname查询任务信息
     *
     * @param jobName
     * @param hostName
     */
    JobExecutionLogEntity findByJobNameAndHostname(String jobName, String hostName);


    /**
     * @param jobName
     * @return
     */
    Slice<JobExecutionLogEntity> getByJobNameLike(String jobName, Pageable pageable);
//    @Query(value = "select id , job_name ,task_id ,hostname , ip from job_execution_log where id = :id" , nativeQuery = true)
//@Query(value = "select u.id , u.job_name ,u.task_id , u.hostname , u.ip  from job_execution_log u where u.id=:id", nativeQuery = true)
//@Query("select a.id , a.jobName , a.taskId, a.hostname from JobExecutionLogEntity a where a.id = ?1")
//@Query("select NEW cn.labsys.pmsys.entity.StudentResult(t1.id,t1.studentname,t2.gradename) from Student t1 join t1.grade t2")
@Query("select NEW cn.orgtec.jpa.dto.JobDto(id,jobName,taskId,hostname,ip) from JobExecutionLogEntity where id = :id")
//@Query("select jobName from JobExecutionLogEntity where id = ?1")
JobDto findByBufen(@Param("id") String id);


    @Query("select u.hostname , u.isSuccess from JobExecutionLogEntity u where u.id = ?1")
    JobExecutionLogEntity findByBuFen(String id);


    Collection<HostNameAndSuccess> findByJobName(int success);

}
