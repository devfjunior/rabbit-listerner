package br.com.fjunior.rabbitmq.config.rabbit;

import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;

import java.util.Map;

public class RetryPolicy extends SimpleRetryPolicy {
	
	private final BinaryExceptionClassifier retryableClassifier;
	private final int maxAttempts;
	
	public RetryPolicy(int maxAttempts, Map<Class<? extends Throwable>, Boolean> retryableExceptions) {
		this.retryableClassifier = new BinaryExceptionClassifier( retryableExceptions, false );
		this.maxAttempts = maxAttempts;
	}
	
	@Override
	public boolean canRetry(RetryContext context) {
		Throwable t = context.getLastThrowable();
		return (t == null || customRetryForException( t.getCause() )) && context.getRetryCount() < maxAttempts;
	}
	
	private boolean customRetryForException(Throwable ex) {
		return this.retryableClassifier.classify( ex );
	}
}
