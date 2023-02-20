package com.blue.framework.aop.adaptor;

import com.blue.framework.aop.advisor.Advice;
import com.blue.framework.aop.advisor.Advisor;
import com.blue.framework.aop.advisor.AfterReturningAdvice;
import com.blue.framework.aop.interceptor.AfterReturningAdvisorInterceptor;
import com.blue.framework.aop.interceptor.AopMethodInterceptor;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.adaptor
 * @className: AfterReturningAdviceAdaptor
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 17:46
 */
public class AfterReturningAdviceAdaptor implements AdviceAdaptor {

    private AfterReturningAdviceAdaptor() {

    }

    private static final AfterReturningAdviceAdaptor INSTANCE = new AfterReturningAdviceAdaptor();

    public static AfterReturningAdviceAdaptor getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean supportsAdvice(Advice advice) {
        return (advice instanceof AfterReturningAdvice);
    }

    @Override
    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        AfterReturningAdvice advice = (AfterReturningAdvice) advisor.getAdvice();
        return new AfterReturningAdvisorInterceptor(advice);
    }
}
