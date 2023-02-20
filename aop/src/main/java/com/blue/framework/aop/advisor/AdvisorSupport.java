package com.blue.framework.aop.advisor;

import com.blue.framework.aop.interceptor.AopMethodInterceptor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.advisor
 * @className: AdvicedSupport
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 17:37
 */
@Data
public class AdvisorSupport extends Advisor {

    private TargetSource targetSource;

    private List<AopMethodInterceptor> interceptorList = new ArrayList<>();

    public void addAopMethodInterceptor(AopMethodInterceptor interceptor) {
        interceptorList.add(interceptor);
    }

    public void addAopMethodInterceptors(List<AopMethodInterceptor> interceptors) {
        interceptorList.addAll(interceptors);
    }
}
