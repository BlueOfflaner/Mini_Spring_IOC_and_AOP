package com.blue.framework.aop.invocation;

import com.blue.framework.aop.interceptor.AopMethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.invocation
 * @className: CglibMethodInvocation
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 17:08
 */
public class CglibMethodInvocation extends ReflectionMethodInvocation {

    private MethodProxy methodProxy;

    public CglibMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, List<AopMethodInterceptor> interceptorList, MethodProxy methodProxy) {
        super(proxy, target, method, arguments, interceptorList);
        this.methodProxy = methodProxy;
    }

    @Override
    //使用代理类调用被增强方法
    protected Object invokeOriginalMethod() throws Throwable {
        //这个好像会实现自身调用增强自身
        //methodProxy.invokeSuper(proxy, arguments);
        return methodProxy.invoke(target, arguments);
    }
}
