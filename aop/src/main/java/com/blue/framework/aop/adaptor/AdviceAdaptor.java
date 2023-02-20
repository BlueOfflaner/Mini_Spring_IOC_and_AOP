package com.blue.framework.aop.adaptor;

import com.blue.framework.aop.advisor.Advice;
import com.blue.framework.aop.advisor.Advisor;
import com.blue.framework.aop.interceptor.AopMethodInterceptor;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.adaptor
 * @className: AdviceAdaptor
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 17:44
 */
public interface AdviceAdaptor {

    public boolean supportsAdvice(Advice advice);

    AopMethodInterceptor getInterceptor(Advisor advisor);
}
