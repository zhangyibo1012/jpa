package cn.orgtec.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>JobDto.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/21 <p>
 * <p>@remark:</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private String id;
    private String jobName;
    private String taskId;
    private String hostname;
    private String ip;
}
