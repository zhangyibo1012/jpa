package cn.orgtec.jpa.util;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <p>LogAspectService.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/04/01 <p>
 * <p>@remark: </p>
 */
@Aspect
@Component
@Slf4j
public class LogAspectService {

    /**
     *  申明一个切点 里面是 execution表达式
     */
    @Pointcut("execution(public * cn.orgtec.hix.broadcast.service.*.*(..))")
    private void controllerAspect() {
    }

    /**
     *  请求method前打印内容
     * @param joinPoint
     */
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("===============请求内容===============");
        try {
            log.info("请求地址:" + request.getRequestURL().toString());
            log.info("请求方式:" + request.getMethod());
            log.info("请求类方法:" + joinPoint.getSignature());
            log.info("请求类方法参数:" + Arrays.toString(joinPoint.getArgs()));
        } catch (Exception e) {
            log.error("###LogAspectService.class methodBefore() ### ERROR:", e);
        }
        log.info("===============请求内容===============");
    }

    /**
     *  在方法执行完结后打印返回内容
     * @param o
     */
    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturn(Object o) {
        log.info("--------------返回内容----------------");
        try {
            log.info("Response内容:" + JSONUtil.toJsonStr(o));
        } catch (Exception e) {
            log.error("###LogAspectService.class methodAfterReturn() ### ERROR:", e);
        }
        log.info("--------------返回内容----------------");
    }
}
