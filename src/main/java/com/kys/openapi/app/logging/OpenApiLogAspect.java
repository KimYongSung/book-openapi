package com.kys.openapi.app.logging;

import com.kys.openapi.app.util.ExecuteTimer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class OpenApiLogAspect {

    private String findObjectName(ProceedingJoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        return String.format("%s.%s", signature.getDeclaringType().getSimpleName(), signature.getName());
    }

    /**
     * Repository 호출 로그
     * @param joinPoint Repository aop
     * @return
     * @throws Throwable
     */
    @Around("execution(public * com.kys.openapi.*.domain.repository..*(..))")
    public Object repositoryLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        return logging(joinPoint);
    }

    /**
     * Service 호출 로그
     * @param joinPoint Repository aop
     * @return
     * @throws Throwable
     */
    @Around("execution(public * com.kys.openapi.*.service..*(..))")
    public Object serviceLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        return logging(joinPoint);
    }

    /**
     * Controller 호출 로그
     * @param joinPoint Repository aop
     * @return
     * @throws Throwable
     */
    @Around("execution(public * com.kys.openapi.*.controller..*(..))")
    public Object controllerLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        return logging(joinPoint);
    }

    /**
     * ExceptionHandler 호출 로그
     * @param joinPoint Repository aop
     * @return
     * @throws Throwable
     */
    @Around("@annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
    public Object exceptionHandlerLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        return exceptionLogging(joinPoint);
    }

    /**
     * logging 처리
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    private Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        ExecuteTimer timer = ExecuteTimer.newTimer();

        String name = findObjectName(joinPoint);

        log.info("<== {}", name);

        timer.start();

        Object response = joinPoint.proceed();

        timer.end();

        if(log.isDebugEnabled()){
            log.info("==> {} [{} ms ] - {}", name, timer.calc(), response);
        }else{
            log.info("==> {} [{} ms ]", name, timer.calc());
        }

        return response;
    }

    private Object exceptionLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = findObjectName(joinPoint);

        Object response = joinPoint.proceed();

        log.info("==> {} - {}", name, response);

        return response;
    }
}
