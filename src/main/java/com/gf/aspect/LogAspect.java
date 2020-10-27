package com.gf.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 日志aop简单实现</p> 
 * @author ganF
 * @date 2020-10-27
 */
@Component
@Aspect
public class LogAspect {
	private Logger log = LoggerFactory.getLogger(LogAspect.class);
	/*@Pointcut("execution(* com.gf.service.impl.*.*(..))")
	public void pointCut(){
	}*/
	
	@Pointcut("@within(com.gf.annotation.Log)")
	public void pointCut(){}
	
	/*@Before("pointCut()")
	public void before(JoinPoint joinPoint){
		log.info("************before****************");
	}
	
	@After("pointCut()")
	public void after(JoinPoint joinPoint){
		log.info("************after************");
	}
	
	@AfterReturning(pointcut="pointCut()", returning="result")
	public void returning(JoinPoint joinPoint, Object result){
		log.info("************returning************"+result);
	}
	
	@AfterThrowing("pointCut()")
	public void exception(JoinPoint joinPoint){
		log.info("************exception************");
	}*/
	
	@Around("pointCut()")
	public Object arround(ProceedingJoinPoint joinPoint) throws Throwable{
		Object[] args = joinPoint.getArgs();
		String methodSignature = joinPoint.getSignature().toShortString();
		Object proceed = null;
		try {
			System.out.println("*****before*********");
			proceed = joinPoint.proceed();
			System.out.println("*****after*********");
		} catch (Throwable e) {
			log.error(methodSignature+"执行异常,参数"+Arrays.toString(args),e);
			throw e;
		}
		return proceed;
	}
}
