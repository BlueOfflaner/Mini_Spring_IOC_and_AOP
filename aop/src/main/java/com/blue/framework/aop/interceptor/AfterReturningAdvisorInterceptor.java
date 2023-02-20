package com.blue.framework.aop.interceptor;

import com.blue.framework.aop.advisor.Advisor;
import com.blue.framework.aop.advisor.AfterReturningAdvice;
import com.blue.framework.aop.invocation.MethodInvocation;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.interceptor
 * @className: AfterMethodAdvisorInterceptor
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 16:57
 */
public class AfterReturningAdvisorInterceptor implements AopMethodInterceptor {

    private AfterReturningAdvice advice;

    public AfterReturningAdvisorInterceptor(AfterReturningAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation m) throws Throwable {
        Object returnVal = m.proceed();
        advice.after(returnVal, m.getMethod(), m.getArguments(), m);
        return returnVal;
    }
}
