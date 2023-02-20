package com.blue.framework.aop.adaptor;

import com.blue.framework.aop.advisor.Advisor;
import com.blue.framework.aop.interceptor.AopMethodInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.adaptor
 * @className: AdvisorAdapterRegistry
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 0:31
 */
public interface AdvisorAdapterRegistry {

    void registerAdvisorAdapter(AdviceAdaptor adaptor);

    List<AopMethodInterceptor> getInterceptors(Advisor advisor);
}
