package com.fct.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class ControllerAspect 
{
    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controllerBean() 
    {
    	System.out.println(1);
    }

    @Pointcut("execution(* *(..))")
    public void methodPointcut() 
    {
    	System.out.println(2);
    }

    @AfterReturning("controllerBean() && methodPointcut() ")
    public void afterMethodInControllerClass() 
    {
        System.out.println(3);
    }
}