package com.blue.framework.aop.invocation;

import com.blue.framework.aop.interceptor.AopMethodInterceptor;
import com.blue.framework.aop.utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.invocation
 * @className: ReflectionMethodInvocation
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 17:02
 */
public class ReflectionMethodInvocation implements ProxyMethodInvocation {

    protected final Object proxy;

    //被代理对象
    protected final Object target;

    protected final Method method;

    protected Object[] arguments = new Object[0];

    //存储所有的拦截器
    protected final List<AopMethodInterceptor> interceptorList;

    private int currentInterceptorIndex = -1;

    public ReflectionMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, List<AopMethodInterceptor> interceptorList) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.interceptorList = interceptorList;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object getProxy() {
        return proxy;
    }

    @Override
    public Object proceed() throws Throwable {
        if(currentInterceptorIndex+1 == this.interceptorList.size()) {
            return invokeOriginalMethod();
        }

        AopMethodInterceptor interceptor = interceptorList.get(++currentInterceptorIndex);
        return interceptor.invoke(this);
    }

    protected Object invokeOriginalMethod() throws Throwable {
        return ReflectionUtils.invokeMethodUseReflection(target, method, arguments);
    }
}
