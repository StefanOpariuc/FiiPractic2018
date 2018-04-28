package com.fiipractic.health.aspects;

import com.fiipractic.health.entity.model.Patient;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rares on 4/21/2018.
 *
 */

@Aspect
@Configuration
public class ControllersAspect {

    @Pointcut("execution(* com.fiipractic.health.boundry.controller.*.*(..))")
    public void controllerMethods(){

    }

    @Pointcut("within(com.fiipractic.health.boundry.controller.*)")
    public void controllerClasses(){

    }

    @Pointcut("@annotation(CustomLogAnnotation)")
    public void customLogAnnotation(){

    }

    @Before("controllerMethods()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Patient) {
                if (((Patient) arg).getEmail().getEmailAddress().contains("@gmail.com")) {
                    throw new IllegalArgumentException("Gmail domain not supported");
                }
            }
        }
        System.out.println("Request is now in controller package");
    }

    @After("controllerClasses()")
    public void after(JoinPoint joinPoint){
        System.out.println("Finished execution of method : " + joinPoint.getSignature().toString());
    }

    @Around("customLogAnnotation()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        System.out.println(proceedingJoinPoint.getSignature() + " - " + duration);
        return proceed;
    }

}
