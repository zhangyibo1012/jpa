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
        Favor favor = new Favor();
        favor.setCount(11);
        return  builder -> {
            builder.setJobName("xx");
            builder.setCompleteTime(LocalDateTime.now());
            builder.setHostname("192");
        };
    }

    public static void main(String[] args) {

    }
}
