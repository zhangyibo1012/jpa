package cn.orgtec.jpa.entity;

import java.time.LocalDateTime;

/**
 * <p>Test.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/22 <p>
 * <p>@remark:</p>
 */
public class Test {

    public Jon cc(){
        return  builder -> {
            builder.setJobName("xx");
            builder.setCompleteTime(LocalDateTime.now());
            builder.setHostname("192");
        };
    }
}
