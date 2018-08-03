package au.com.crm.customer.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class CustomerAspect {

	private static final String DELIMITER = ", ";

	/**
	 * This aspect is to monitor the performance of each service call.
	 */
	@Pointcut("execution(* au.com.crm.customer.service.*.*(..))")
	private void customerService() {
		// empty
	}

	@Around("customerService()")
	public Object aroundCustomerService(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		Logger loggerForJointPoint = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

		if (loggerForJointPoint.isDebugEnabled()) {
			StringBuilder logMessage = new StringBuilder();
			// append args this is null safe
			Object[] methodArguments = joinPoint.getArgs();

			for (Object obj : methodArguments) {
				logMessage.append(obj).append(DELIMITER);
			}

			loggerForJointPoint.debug("********Entering {}() with aruguments [{}]", joinPoint.getSignature().getName(),
					logMessage.toString());
			stopWatch.start();
		}

		// Actual method call
		Object retVal = joinPoint.proceed();

		if (loggerForJointPoint.isDebugEnabled()) {
			stopWatch.stop();

			StringBuilder formatterLogMessage = new StringBuilder();
			formatterLogMessage.append("*********Exiting {}() with execution time :: ")
					.append(stopWatch.getTotalTimeMillis()).append(" , with return Value [{}]");
			loggerForJointPoint.debug(formatterLogMessage.toString(), joinPoint.getSignature().getName(), retVal);
		}
		return retVal;

	}
}
