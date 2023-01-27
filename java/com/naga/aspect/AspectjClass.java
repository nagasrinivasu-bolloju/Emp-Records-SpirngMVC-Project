package com.naga.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectjClass
{
	private static Logger logger=Logger.getLogger(AspectjClass.class);
	
	//creating pointcuts for all layers
	@Pointcut("execution(* com.naga.controller.MyController.*(..))")
	public void allControllerLayerMethods() {};
	
	@Pointcut("execution(* com.naga.dao.DaoClass.*(..))")
	public void allDaoLayerMethods() {};
	
	@Pointcut("execution(* com.naga.model.Emp.*(..))")
	public void allModelLayerMethods() {};
	
	@Pointcut("within(com.naga.service.ServiceClass)")
	public void allServiceLayerMethods() {};
	
	
	@Around("@annotation(com.naga.aspect.LoggingService) || allDaoLayerMethods()")
	public Object serviceAspectMethod(ProceedingJoinPoint proceedingJoinPoint)
	{
		Object returnType=null;
		String packageName=null;
		String methodName=null;
		try
		{
			packageName=proceedingJoinPoint.getSignature().getDeclaringTypeName();
			methodName=proceedingJoinPoint.getSignature().getName();
			logger.info("Ready to execute ["+packageName+"] ["+methodName+"]");
			returnType=proceedingJoinPoint.proceed();
			logger.info("Executed successfully ["+packageName+"] ["+methodName+"]");
		}
		catch (Throwable e)
		{
			logger.error("Exception Occured at ["+packageName+"] ["+methodName+"]");
		}
		return returnType;
	}
	
	
//	@Around("allDaoLayerMethods()")
//	public Object daoAspectMethod(ProceedingJoinPoint proceedingJoinPoint)
//	{
//		Object returnType=null;
//		String packageName=null;
//		String methodName=null;
//		try
//		{
//			//Before()
//			packageName=proceedingJoinPoint.getSignature().getDeclaringTypeName();
//			methodName=proceedingJoinPoint.getSignature().getName();
//			logger.info("Ready to execute ["+packageName+"] ["+methodName+"]");
//			//System.out.println("ready to execute...."+proceedingJoinPoint.getSignature().getName());
//			returnType=proceedingJoinPoint.proceed(); //to execute setName() of Car.
//			//AfterReturning()
//			logger.info("Executed successfully ["+packageName+"] ["+methodName+"]");
//			//System.out.println("Executed successfully.."+proceedingJoinPoint.getSignature().getName());
//		}
//		catch (Throwable e)
//		{
//			// TODO Auto-generated catch block
//			logger.error("Exception Occured at ["+packageName+"] ["+methodName+"]");
//			//System.out.println("exception occured "+e);
//		}
//		return returnType;
//	}
}
