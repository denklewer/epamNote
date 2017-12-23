package com.epam.note.profiling;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j
public class Profiling {
    @Around("within(com.epam.note.services..*)")
    public Object basicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        long before = System.nanoTime();
        Object retVal = pjp.proceed();
        long after = System.nanoTime();
        log.info(pjp.getTarget().getClass().getName() + " " + pjp.getSignature().getName() + " " + (after - before));
        return retVal;
    }
}
