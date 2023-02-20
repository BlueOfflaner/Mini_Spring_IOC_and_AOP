package com.blue.framework.aop.adaptor;

import com.blue.framework.aop.advisor.Advice;
import com.blue.framework.aop.advisor.Advisor;
import com.blue.framework.aop.advisor.BeforeMethodAdvice;
import com.blue.framework.aop.interceptor.BeforeMethodAdvisorInterceptor;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.adaptor
 * @className: BeforeMethodAdviceAdaptor
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 17:57
 */
public class BeforeMethodAdviceAdaptor implements AdviceAdaptor{

    private BeforeMethodAdviceAdaptor() {

    }

    private static final BeforeMethodAdviceAdaptor INSTANCE = new BeforeMethodAdviceAdaptor();

    public static BeforeMethodAdviceAdaptor getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean supportsAdvice(Advice advice) {
        return (advice instanceof BeforeMethodAdvice);
    }

    public BeforeMethodAdvisorInterceptor getInterceptor(Advisor advisor) {
        BeforeMethodAdvice advice = (BeforeMethodAdvice) advisor.getAdvice();
        return new BeforeMethodAdvisorInterceptor(advice);
    }
}
