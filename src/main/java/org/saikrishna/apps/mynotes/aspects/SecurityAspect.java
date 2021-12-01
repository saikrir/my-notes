package org.saikrishna.apps.mynotes.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class SecurityAspect {

    @Around(
            "execution (* org.saikrishna..CategoryService.toCategoryResources(..))"
    )
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.debug("Executing method {} ", proceedingJoinPoint.getStaticPart().getSignature() );
        Object proceed = proceedingJoinPoint.proceed();
        log.debug("Exiting method {}", proceedingJoinPoint.getStaticPart().getSignature());
        return proceed;
    }
}
