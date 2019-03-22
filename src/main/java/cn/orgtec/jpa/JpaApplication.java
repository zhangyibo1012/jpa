package cn.orgtec.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * <p>JpaApplication.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/12 <p>
 * <p>@remark:</p>
 * Spring Caching
 */
@SpringBootApplication
@EnableCaching
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}
