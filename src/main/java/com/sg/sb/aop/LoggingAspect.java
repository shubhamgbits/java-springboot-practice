package com.sg.sb.aop;

import com.sg.sb.util.ServiceConstants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.sg.sb.controller..*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature().getName(), ServiceConstants.marshalJson(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.sg.sb.controller..*(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result: {}", joinPoint.getSignature().getName(), ServiceConstants.marshalJson(result));
    }
}
