package com.blue.framework.aop.interceptor;

import com.blue.framework.aop.invocation.MethodInvocation;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.interceptor
 * @className: AopMethodInterceptor
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 16:54
 */
public interface AopMethodInterceptor {
    Object invoke(MethodInvocation m) throws Throwable;
}
