package com.blue.framework.aop.invocation;

import java.lang.reflect.Method;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.invocation
 * @className: MethodInvocation
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 16:52
 */
public interface MethodInvocation {
    //获取方法
    Method getMethod();
    //获取方法参数
    Object[] getArguments();
    //执行方法
    Object proceed() throws Throwable;
}
