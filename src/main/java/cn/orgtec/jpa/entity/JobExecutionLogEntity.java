package cn.orgtec.jpa.entity;

import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * <p>JobExecutionLogEntity.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/12 <p>
 * <p>@remark:</p>
 */
@Entity
@Table(name = "job_execution_log", schema = "hix-sys")
@ToString
public class JobExecutionLogEntity implements Serializable {
    private String id;
    private String jobName;
    private String taskId;
    private String hostname;
    private String ip;
    private int shardingItem;
    private String executionSource;
    private String failureCause;
    private int isSuccess;
    private LocalDateTime startTime;
    private LocalDateTime completeTime;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "task_id")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "hostname")
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "sharding_item")
    public int getShardingItem() {
        return shardingItem;
    }

    public void setShardingItem(int shardingItem) {
        this.shardingItem = shardingItem;
    }

    @Basic
    @Column(name = "execution_source")
    public String getExecutionSource() {
        return executionSource;
    }

    public void setExecutionSource(String executionSource) {
        this.executionSource = executionSource;
    }

    @Basic
    @Column(name = "failure_cause")
    public String getFailureCause() {
        return failureCause;
    }

    public void setFailureCause(String failureCause) {
        this.failureCause = failureCause;
    }

    @Basic
    @Column(name = "is_success")
    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Basic
    @Column(name = "start_time")
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "complete_time")
    public LocalDateTime getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(LocalDateTime completeTime) {
        this.completeTime = completeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobExecutionLogEntity that = (JobExecutionLogEntity) o;

        if (shardingItem != that.shardingItem) return false;
        if (isSuccess != that.isSuccess) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (jobName != null ? !jobName.equals(that.jobName) : that.jobName != null) return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (hostname != null ? !hostname.equals(that.hostname) : that.hostname != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (executionSource != null ? !executionSource.equals(that.executionSource) : that.executionSource != null)
            return false;
        if (failureCause != null ? !failureCause.equals(that.failureCause) : that.failureCause != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (completeTime != null ? !completeTime.equals(that.completeTime) : that.completeTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (jobName != null ? jobName.hashCode() : 0);
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        result = 31 * result + (hostname != null ? hostname.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + shardingItem;
        result = 31 * result + (executionSource != null ? executionSource.hashCode() : 0);
        result = 31 * result + (failureCause != null ? failureCause.hashCode() : 0);
        result = 31 * result + isSuccess;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (completeTime != null ? completeTime.hashCode() : 0);
        return result;
    }
}
