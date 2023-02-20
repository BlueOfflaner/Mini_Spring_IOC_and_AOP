package com.blue.framework.aop.core;

import com.blue.framework.aop.advisor.TargetSource;
import com.blue.framework.aop.interceptor.AopMethodInterceptor;
import com.blue.framework.aop.invocation.CglibMethodInvocation;
import com.blue.framework.aop.invocation.MethodInvocation;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.core
 * @className: DynamicAdvisedInterceptor
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 14:33
 */
public class DynamicAdvisedInterceptor implements MethodInterceptor  {

    protected final List<AopMethodInterceptor> interceptorList;
    protected final TargetSource targetSource;

    public DynamicAdvisedInterceptor(List<AopMethodInterceptor> interceptorList, TargetSource targetSource) {
        this.interceptorList = interceptorList;
        this.targetSource = targetSource;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        MethodInvocation invocation = new CglibMethodInvocation(o, targetSource.getTargetObject(), method, args, interceptorList, methodProxy);
        return invocation.proceed();
    }
}
