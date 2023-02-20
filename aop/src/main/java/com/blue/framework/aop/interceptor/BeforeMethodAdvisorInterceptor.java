package com.blue.framework.aop.interceptor;

import com.blue.framework.aop.advisor.BeforeMethodAdvice;
import com.blue.framework.aop.invocation.MethodInvocation;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.interceptor
 * @className: BeforeMethodAdvisorInvocation
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 16:55
 */
public class BeforeMethodAdvisorInterceptor implements AopMethodInterceptor {

    private BeforeMethodAdvice advice;

    public BeforeMethodAdvisorInterceptor(BeforeMethodAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation m) throws Throwable {
        advice.before(m.getMethod(), m.getArguments(), m);
        return m.proceed();
    }
}
