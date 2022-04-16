package teach.iteco.ru.externalservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Slf4j
@Component
@Aspect
public class LoggingAspect {


    @Before("allMethodInService()")
    public void beforeAllMethodInServiceAdvice(JoinPoint joinPoint)
    { log.info("beforeAllMethodInServiceAdvice:: START {} ",joinPoint.getSignature().toShortString());}


    @After("allMethodInService()")
    public void afterAllMethodInServiceAdvice(JoinPoint joinPoint)
    { log.info("afterAllMethodInServiceAdvice:: END {} ",joinPoint.getSignature().toShortString());}

    @AfterThrowing(value="allMethodInService()",throwing = "exception")
    public void afterAllMethodInServiceThrowAdvice(JoinPoint joinPoint,Exception exception)
    { log.info("afterAllMethodInServiceThrowAdvice:: END {} with THROW:{} ",joinPoint.getSignature().toShortString(),exception.toString());}

    @Pointcut("within(teach.iteco.ru.externalservice.service.*)")
    public void allMethodInService() {};



}
