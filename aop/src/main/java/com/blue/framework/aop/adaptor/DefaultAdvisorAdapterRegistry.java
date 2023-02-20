package com.blue.framework.aop.adaptor;

import com.blue.framework.aop.advisor.Advisor;
import com.blue.framework.aop.interceptor.AopMethodInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.adaptor
 * @className: GlobalAdvisorAdapterRegistry
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 0:34
 */
public class DefaultAdvisorAdapterRegistry implements AdvisorAdapterRegistry {

    public List<AdviceAdaptor> adviceAdaptorList = new ArrayList<>();

    private DefaultAdvisorAdapterRegistry() {
        registerAdvisorAdapter(BeforeMethodAdviceAdaptor.getInstance());
        registerAdvisorAdapter(AfterReturningAdviceAdaptor.getInstance());
    }

    private static final DefaultAdvisorAdapterRegistry INSTANCE = new DefaultAdvisorAdapterRegistry();

    public static DefaultAdvisorAdapterRegistry getInstance() {
        return INSTANCE;
    }

    @Override
    public void registerAdvisorAdapter(AdviceAdaptor adaptor) {
        adviceAdaptorList.add(adaptor);
    }

    public List<AopMethodInterceptor> getInterceptors(Advisor advisor) {
        List<AopMethodInterceptor> interceptors = new ArrayList<>();
        for (AdviceAdaptor adviceAdaptor : adviceAdaptorList) {
            if(!adviceAdaptor.supportsAdvice(advisor.getAdvice())) continue;
            AopMethodInterceptor interceptor = adviceAdaptor.getInterceptor(advisor);
            interceptors.add(interceptor);
        }
        return interceptors;
    }
}
