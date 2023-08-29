package org.example.util.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
public class LoggingAspect {

    private Logger logger;

    public LoggingAspect() {
        FileLoggingConfigurer.configure();
        logger = Logger.getLogger(LoggingAspect.class.getName());
    }

    @Pointcut("@annotation(org.example.util.log.Loggable)")
    public void loggableMethod() {}

    @Before("loggableMethod()")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Method " + methodName + " called with arguments: " + args);
    }

    @AfterReturning(pointcut = "loggableMethod()", returning = "result")
    public void logMethodExecution(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method " + methodName + " returned: " + result);
    }
}






